package com.huston.springboot.crudgeneric.resultadapter;

import com.huston.springboot.crudgeneric.*;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUtil<T1> {
    @Autowired
    ApplicationContext context;
    protected <T2> Field getAllianceFieldFromResult(T2 initResult, String alliancesFieldString)throws CrudGenericException {
        try{

            return initResult.getClass().getDeclaredField(alliancesFieldString);
        }
        catch (NoSuchFieldException e){
            throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_ALLIANCE_FIELD, Constants.MSG_INVALID_ALLIANCE_CANNOT_FIND_FIELD);
        }
    }

    protected Alliance getAllianceAnnotation(Field allianceField) throws CrudGenericException{
        Alliance alliance = allianceField.getAnnotation(Alliance.class);
        if(null != alliance){
            if(null != alliance.foreignKey() && !"".equals(alliance.foreignKey())){
                if(null != alliance.repositoryType()){
                    return alliance;
                }
                throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_ALLIANCE_REPOSITORY, Constants.MSG_INVALID_ALLIANCE_REPOSITORY_TYPE);
            }
            throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_ALLIANCE_FOREIGN_KEY, Constants.MSG_INVALID_ALLIANCE_KEY);
        }
        throw new CrudGenericException(CrudGenericException.ExceptionType.ALLIANCE_FIELD_CANNOT_BE_FOUND, Constants.MSG_ALLIANCE_FIELD_CANNOT_BE_FOUDN);
    }

    protected WhereAlliance getWhereAllianceAnnotation(Field allianceField) throws CrudGenericException{
        WhereAlliance alliance = allianceField.getAnnotation(WhereAlliance.class);
        if(null != alliance){
            if(null != alliance.filters() && alliance.filters().length > 0){
                if(null != alliance.allianceEntity()){
                    return alliance;
                }
                throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_ALLIANCE_ENTITY, Constants.MSG_INVALID_ALLIANCE_REPOSITORY_TYPE);
            }
            throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_WHERE_ALLIANCE_FILTERS, Constants.MSG_INVALID_ALLIANCE_KEY);
        }
        throw new CrudGenericException(CrudGenericException.ExceptionType.WHERE_ALLIANCE_FIELD_CANNOT_BE_FOUND, Constants.MSG_ALLIANCE_FIELD_CANNOT_BE_FOUDN);
    }

    protected GenericCrudRepository getAllianceRepositoryBean(Class<?> allianceRepositoryType) throws CrudGenericException{
        GenericCrudRepository repository = (GenericCrudRepository)context.getBean(allianceRepositoryType);
        if(null != repository){
            return repository;
        }
        throw new CrudGenericException(CrudGenericException.ExceptionType.INVALID_ALLIANCE_REPOSITORY, Constants.MSG_INVALID_ALLIANCE_REPOSITORY_TYPE);
    }

    protected <T> T getRepositoryBean(Class<T> allianceRepositoryType) throws CrudGenericException{
        return context.getBean(allianceRepositoryType);
    }

    protected Method getAllianceFieldSetMethod(String alliancesFieldString, Object initResult) throws CrudGenericException,NoSuchMethodException{
        return initResult.getClass().getDeclaredMethod(setMethod(alliancesFieldString),getAllianceFieldFromResult(initResult,alliancesFieldString).getType());
    }

    protected String getMethod(String fieldString){
        return method("get",fieldString);
    }

    protected String setMethod(String fieldString){
        return method("set",fieldString);
    }

    protected String method(String type, String fieldString){
        Assert.notNull(type);
        Assert.notNull(fieldString);
        String capedFieldString = fieldString.substring(0, 1).toUpperCase() + fieldString.substring(1);
        return type + capedFieldString;
    }

    protected Serializable getAllianceFieldValue(Object object, String fieldName) throws NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method allianceIdFieldGetMethod = object.getClass().getMethod(getMethod(fieldName));
        return (Serializable) allianceIdFieldGetMethod.invoke(object);// A id
    }

    protected Field getFieldFromEntity(Class entity, String fieldName){
        Field field = null;
        try {
            field = entity.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            try {
                field = entity.getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
//        if(field.getType().getTypeName())
        return field;
    }

    protected Class<?> getFieldTypeFromClass(Class<?> entity, String fieldName){
        Field field = getFieldFromEntity(entity,fieldName);
        if(field.getDeclaredAnnotation(Id.class) != null){
            return (Class<?>)((ParameterizedType)entity.getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return field.getType();
    }

    protected <T> T castValueToFieldType(Class<T> entity, Object fieldValue){
        return entity.cast(fieldValue);
    }

    protected <T> T castValueToWrapperClass(Class<T> clazz, String fieldValue){

        if (clazz == Integer.TYPE || clazz == Integer.class)
            return (T) Integer.valueOf(fieldValue);
        if (clazz == Long.TYPE || clazz == Long.class)
            return (T) Long.valueOf(fieldValue);
        if (clazz == Boolean.TYPE || clazz == Boolean.class)
            return (T) Boolean.valueOf(fieldValue);
        if (clazz == Byte.TYPE || clazz == Byte.class)
            return (T) Byte.valueOf(fieldValue);
//        if (clazz == Character.TYPE || clazz == Character.class)
//            return (T) Character.valueOf(fieldValue);
        if (clazz == Float.TYPE || clazz == Float.class)
            return (T) Float.valueOf(fieldValue);
        if (clazz == Double.TYPE || clazz == Double.class)
            return (T) Double.valueOf(fieldValue);
        if (clazz == Short.TYPE || clazz == Short.class)
            return (T) Short.valueOf(fieldValue);
        if (clazz.isEnum())
            return (T) Enum.valueOf((Class<Enum>)clazz, fieldValue);

        return (T) fieldValue;
    }

    protected Object castValueToEntityField(Class<?> entity, String fieldName, Object fieldValue){
        Class<?> fieldType = getFieldTypeFromClass(entity,fieldName);
        return castValueToWrapperClass(fieldType,fieldValue.toString());
    }

    /**
     * Simple get search criteria
     * @param filters
     * @return
     */
    protected <T extends GenericCrudEntity> Specification<T> searchingCriteria(Map<String, String> filters) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> pd = new ArrayList<>();

                for (Map.Entry<String, String> filter: filters.entrySet()) {
                    if(filter.getValue() != null && filter.getValue() !=""){
                      String value = String.valueOf(filter.getValue());
                      if(isNumber(value)){
                            pd.add(criteriaBuilder.equal(root.get(filter.getKey()), Integer.parseInt(value)));
                        }
                        else{
                            pd.add(criteriaBuilder.like(root.get(filter.getKey()), "%"+ value + "%"));
                        }

                    }
                }
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
                return criteriaBuilder.and(pd.toArray(new Predicate[0]));
            }
        };
    }

    /**
     * @param input
     * @return
     */
    private boolean isNumber(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
