package com.huston.springboot.crudgeneric;


import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Alliance {
    String foreignKey() default "";
    String localKey() default "id";
    Class repositoryType() default JpaRepository.class;
    String[] alliances() default {};

    /**
     * will attache only isActive true entities
     */
    boolean activeOnly() default false;
}
