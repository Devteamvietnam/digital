package com.devteam.digital.core.annotation;

import com.vion.module.enums.BusinessType;
import com.vion.module.enums.OperatorType;

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
