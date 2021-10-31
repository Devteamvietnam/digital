package com.devteam.core.annotation;

import com.devteam.core.enums.DataSourceType;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    public DataSourceType value() default DataSourceType.MASTER;
}
