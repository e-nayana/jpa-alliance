package com.java.brand.service;

import com.auth.service.UploadService;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.exception.CustomCheckException;
import com.java.brand.model.Brand;
import com.java.brand.repository.BrandRepository;
import com.java.d_ocean_storage.services.FileStorageService;
import com.java.product.model.Product;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BrandServiceImpl extends GenericCrudServiceImpl<BrandRepository, Brand> implements BrandService {

    public static String BRAND_IMAGE_DIR = "brand";

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public Brand show(Integer id) throws CrudGenericException {
        return super.show(id);
    }

    @Override
    public Pagenator.PagenatedObject list(int page, int perPage, HashMap<String, Object> filters) {
        return list(page,perPage,searchCriteria(filters));
    }

    private Specification<Brand> searchCriteria(HashMap<String,Object> filters) {
        return new Specification<Brand>() {
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> pd = new ArrayList<>();

                String name = (String) filters.putIfAbsent("name", null);

                if (name !=null && !"".equals(name)) {
                    pd.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
                return criteriaBuilder.and(pd.toArray(new Predicate[0]));
            }
        };
    }

    @Override
    public Brand create(Brand brand) throws CrudGenericException {

        if (brand.getNewImage() != null) {
            brand.setImage(brand.getNewImage());
        }

        return super.create(brand);
    }

    @Override
    public Brand update(Brand brand) throws CrudGenericException {

        if ((brand.getImage() != null && brand.getImageStatus() != null && brand.getImageStatus().equals("to_be_deleted")) ||
                (brand.getImage() != null && brand.getNewImage() != null)) {
            try {
                String[] bits = brand.getImage().split("/");
                String imageName = bits[bits.length-1];
                fileStorageService.deleteFile(imageName);
            } catch (Exception e) {
                throw new CustomCheckException("Image Storage error");
            }
            brand.setImage(null);
        }

        if (brand.getNewImage() != null) {
            brand.setImage(brand.getNewImage());
        }

        return super.update(brand);
    }

    //    private BrandRepository brandRepository;
//
//    @Autowired
//    BrandServiceImpl(BrandRepository brandRepository){
//        this.brandRepository = brandRepository;
//    }
//
//    @Override
//    public List<Brand> getActiveBrandList() throws CrudGenericException{
//        return brandRepository.findAll();
//    }
}
