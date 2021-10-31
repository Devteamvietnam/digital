package com.devteam.core.annotation;

import com.devteam.core.enums.BusinessType;
import com.devteam.core.enums.OperatorType;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    public String title() default "";

    public BusinessType businessType() default BusinessType.OTHER;

    public OperatorType operatorType() default OperatorType.MANAGE;

    public boolean isSaveRequestData() default true;
}
