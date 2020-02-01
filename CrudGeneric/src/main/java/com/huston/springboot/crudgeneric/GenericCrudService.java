package com.huston.springboot.crudgeneric;

import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.service.Pagenator;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface GenericCrudService<T1> {

    /**
     * This need to be removed
     * get one of T1 resource by primary key
     * @return
     */
    <ID extends Serializable> T1 get(ID primaryKey);

    /**
     * get the reference to relevant entity not the result
     * NOTE Same as get(primaryKey)
     * @param primaryKey
     * @param <ID>
     * @return
     */
    <ID extends Serializable> T1 getReference(ID primaryKey);

    /**
     * will give the result by the primary key
     * @param primaryKey
     * @param <ID>
     * @return
     */
    <ID extends Serializable> T1 find(ID primaryKey);

     /**
     * get list of T1 resource
     * @return
     */
     List<T1> get();

     /**
     * get list of resource with alliances
     * @return
     */
     List<T1> get(String... alliancesFields);

    /**
     * get list of resource type with specification
     * @return
     */
    List<T1> get(Specification<T1> specification);

    /**
     * get list of resource type with specification and alliances
     * @return
     */
    List<T1> get(Specification<T1> specification, String... alliancesFields);

    /**
     * get paginated list with filters
     * @return
     */
    @Deprecated
    Pagenator.PagenatedObject list(int page, int perPage, HashMap<String, Object> filters);

    /**
     * get paginated list
     * @return
     */
    @Deprecated
     Pagenator.PagenatedObject list(int page, int perPage);

    /**
     * get paginated list with specification
     * @return
     */
    @Deprecated
     Pagenator.PagenatedObject list(int page, int perPage, Specification<T1> specification);

    /**
     * get paginated list with alliances
     * @return
     */
    @Deprecated
     Pagenator.PagenatedObject list(int page, int perPage, String... alliancesFields);

    /**
     * get paginated list with alliances and specification
     * @return
     */
    @Deprecated
     Pagenator.PagenatedObject list(int page, int perPage, Specification<T1> specification, String... alliancesFields);

    /**
     * get paginated list new methods
     * @return
     */
    ResultPage page(int page, int perPage);

    /**
     * get paginated list with specification new methods
     * @return
     */
    ResultPage page(int page, int perPage, Specification<T1> specification);

    /**
     * get paginated list with alliances new methods
     * @return
     */
    ResultPage page(int page, int perPage, String... alliancesFields);

    /**
     * get paginated list with alliances and specification new methods
     * @return
     */
    ResultPage page(int page, int perPage, Specification<T1> specification, String... alliancesFields);

    /**
     * get list of resource in active state
     * @return
     */
     List<T1> activeList();

  List<T1> list();

  /**
     * get list of resource in active state with alliances
     * @return
     */
    List<T1> activeList(String... alliancesFields);

    /**
     * returns requested resource Or throws CrudGenericException with resource not found message
     * @param primaryKey
     * @param <ID>
     * @return T1
     * @throws CrudGenericException
     */
     <ID extends Serializable> T1 show(ID primaryKey) throws CrudGenericException;

    /**
     * simply save or udpate resource even if existing resource found from primary key
     * @param entity
     * @return
     */
     T1 save(T1 entity) throws CrudGenericException;


    /**
     * same as save. should not override this method. implemented for force save for a entity.
     * @param entity
     * @return
     */
     T1 directSave(T1 entity);

    /**
     * save the resource as new one if it donst have primary key
     * @param entity
     * @return
     * @throws CrudGenericException
     */
     T1 create(T1 entity) throws CrudGenericException;

    /**
     * update a existing resource using primary key
     * @param entity
     * @return
     * @throws CrudGenericException
     */
     T1 update(T1 entity)throws CrudGenericException;

    /**
     * completely delete the resource
     * @param primaryKey
     * @param <ID>
     */
    <ID extends Serializable> void delete(ID primaryKey) throws CrudGenericException;

    /**
     * set is active flag to false
     * @param primaryKey
     * @param <ID>
     * @throws CrudGenericException
     */
    <ID extends Serializable> void remove(ID primaryKey) throws CrudGenericException;

    /**
     * returns the resource with related resources which are annotated from Alliance with key field using given primary key
     * @param primaryKey
     * @param alliancesFields
     * @param <ID>
     * @return
     * @throws AllianceException
     */
    <ID extends Serializable> T1 show(ID primaryKey, String... alliancesFields) throws AllianceException, CrudGenericException;

}
