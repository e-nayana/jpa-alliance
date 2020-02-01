package com.java.product.service;

import com.auth.service.UploadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.exception.CustomCheckException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.area.model.Area;
import com.java.brand.service.BrandService;
import com.java.d_ocean_storage.services.FileStorageService;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrderView;
import com.java.product.model.Product;
import com.java.product.model.ProductImage;
import com.java.product.model.ProductVariation;
import com.java.product.repository.ProductImageRepository;
import com.java.product.repository.ProductRepository;
import com.java.product.repository.ProductVariationRepository;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends GenericCrudServiceImpl<ProductRepository, Product> implements ProductService {

    private static String PRODUCT_IMAGE_DIR = "product";

    private ProductImageRepository productImageRepository;
    private ProductVariationRepository productVariationRepository;
    private ComboChildProductService comboChildProductService;
    private FileStorageService fileStorageService;

    @Autowired
    public ProductServiceImpl(ProductImageRepository productImageRepository, ProductVariationRepository productVariationRepository,
                              ComboChildProductService comboChildProductService, FileStorageService fileStorageService) {
      this.productImageRepository = productImageRepository;
      this.productVariationRepository = productVariationRepository;
      this.comboChildProductService = comboChildProductService;
      this.fileStorageService = fileStorageService;
    }

    @Override
    public Pagenator.PagenatedObject list(int page, int perPage, HashMap<String, Object> filters) {
        return list(page,perPage,searchCriteria(filters),"brand", "mainProductImages");
    }

    private Specification<Product> searchCriteria(HashMap<String,Object> filters) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> pd = new ArrayList<>();

                String name = (String) filters.getOrDefault("productName", null);
                String code = (String) filters.getOrDefault("productCode", null);
                Integer id = (Integer) filters.getOrDefault("name", null);
                List<Integer> brandIds = (List<Integer>) filters.getOrDefault("brands", null);
                Object brandIdObj = filters.getOrDefault("brandId", null);
                Integer brandId = (brandIdObj != null && (!"".equals(brandIdObj.toString().trim()))) ? Integer.valueOf(brandIdObj.toString()) : null;

                if (name !=null && !"".equals(name)) {
                    pd.add(criteriaBuilder.like(root.get("productName"), "%" + name + "%"));
                }

                if (code !=null && !"".equals(code)) {
                    pd.add(criteriaBuilder.like(root.get("productCode"), "%" + code + "%"));
                }

                if (id != null && id > 0) {
                    pd.add(criteriaBuilder.equal(root.get("id"), id));
                }

                if (brandId != null && brandId > 0) {
                    pd.add(criteriaBuilder.equal(root.get("brandId"), brandId));
                }

                if (brandIds != null && brandIds.size() > 0) {
                    pd.add(root.get("brandId").in(brandIds));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
                return criteriaBuilder.and(pd.toArray(new Predicate[0]));
            }
        };
    }

    @Override
    public ResultPage search(int page, int perPage, Map<String, String> filters) {
        return super.page(page, perPage,searchingCriteria(filters),"brand", "mainProductImages");
    }

    @Override
    public Product show(Integer id) throws CrudGenericException {
        Product product = null;
        try {
            product = super.show(id, "brand", "productImages");
        } catch (AllianceException e) {
            product = super.show(id);
        }
        return product;
    }

    @Override
    public Product showProductFullDetails(Integer id) throws CrudGenericException {
        Product product = null;
        try {
            product = super.show(id, "category", "brand", "productImages", "productVariations", "productFeedbacks" , "comboChildProducts", "mainProductImages");
        } catch (AllianceException e) {
            product = super.show(id);
        }
        return product;
    }

    @Override
    public Product saveProductImages(List<ProductImage> productImages) throws AllianceException, CrudGenericException {
        int count = 2;
        int productId = 0;
        for (ProductImage productImage : productImages) {
            if (productImage.getStatus() != null && productImage.getStatus().equals("to_be_deleted")) {
                //delete the file in digital ocean space
                try {
                    String[] bits = productImage.getImage().split("/");
                    String imageName = bits[bits.length-1];
                    fileStorageService.deleteFile(imageName);
                } catch (Exception e) {
                    throw new CustomCheckException("Image Storage error");
                }

                productImageRepository.deleteById(productImage.getId());
            } else {
                if (productImage.getType().equals("mainImage")) {
                    productImage.setOrderNumber(1);
                    productId = productImage.getProductId();
                } else {
                    productImage.setOrderNumber(count);
                    count++;
                }

                productImage.setImage(productImage.getImage());
                productImageRepository.save(productImage);
            }
        }

        return super.show(productId, "category", "brand", "productImages", "productVariations", "productFeedbacks");
    }

    @Transactional
    @Override
    public Product saveProductVariation(List<ProductVariation> productVariations) throws AllianceException, CrudGenericException {
        int productId = 0;

        List<Integer> addIds = new ArrayList<>();

        for (ProductVariation productVariation: productVariations) {
            productId = productVariation.getProductId();
            addIds.add(productVariationRepository.save(productVariation).getId());
        }

        productVariationRepository.removeProductVariationByProductIdAndIdNotIn(productId, addIds);

        return super.show(productId, "category", "brand", "productImages", "productVariations", "productFeedbacks");
    }

    @Override
    public Pagenator.PagenatedObject similarProduct(int id) throws CrudGenericException {
        Product product = repository.findById(id).get();
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("brandId", product.getBrandId());
        return list(1,5,searchCriteria(filters),"brand", "mainProductImages");
    }

    /**
     * to create both single and combo products
     */
    @Transactional
    @Override
    public Product createNewProduct(Product product) throws Exception{

      product = save(product);

      if(product.getType().equals("combo")){
        this.comboChildProductService.createNewComboChildProductList(product);
      }

      return product;
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) throws Exception{

      update(product);

      this.comboChildProductService.deleteChildProductsFromComboProductId(product.getId());

      if(product.getType().equals("combo")){
        this.comboChildProductService.createNewComboChildProductList(product);
      }

      return product;
    }

    @Override
    public ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException {
      return page(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), searchingCriteria(controllerRequestCriteria.getSearchCriteria()),alliancesFields);
    }

    @Override
    public List<Product> singleProductList() {
        return repository.findAllByTypeAndIsActive("single", true);
    }
}
