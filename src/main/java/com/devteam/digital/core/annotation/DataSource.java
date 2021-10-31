package com.devteam.digital.core.annotation;

import com.vion.module.enums.DataSourceType;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    public DataSourceType value() default DataSourceType.MASTER;
}
