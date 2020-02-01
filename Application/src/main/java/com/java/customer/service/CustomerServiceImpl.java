package com.java.customer.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.customer.model.EcomCustomer;
import com.java.customer.repository.EcomCustomerRepository;
import com.java.service.Pagenator;
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
public class CustomerServiceImpl extends GenericCrudServiceImpl<EcomCustomerRepository, EcomCustomer> implements CustomerService{


    @Override
    public Pagenator.PagenatedObject list(int page, int perPage, HashMap<String, Object> filters) {
        return list(page,perPage,searchCriteria(filters));
    }


    private Specification<EcomCustomer> searchCriteria(HashMap<String,Object> filters) {
        return new Specification<EcomCustomer>() {
            @Override
            public Predicate toPredicate(Root<EcomCustomer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    public EcomCustomer customerByUserId(int userId) {
        return repository.findFirstByUserId(userId);
    }
}
