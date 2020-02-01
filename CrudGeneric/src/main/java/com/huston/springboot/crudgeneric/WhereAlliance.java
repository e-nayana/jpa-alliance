package com.huston.springboot.crudgeneric;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WhereAlliance {

    /**
    * ex : field = value, field in (values)
    **/
    String[] filters();
    Class<?> allianceEntity();

}
