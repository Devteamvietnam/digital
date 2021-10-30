package com.devteam.digital.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *   Used to determine whether to filter data permissions
 *   1. If not used @OneToOne This kind of relationship, only need to fill in fieldName [reference：DeptQueryCriteria.class]
 *   2. If used @OneToOne ，fieldName with joinName All need to be filled in，takeUserQueryCriteria.class take
 *   should be @DataPermission(joinName = "dept", fieldName = "id")
 * </p>
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

    /**
     * Entity Field name in
     */
    String fieldName() default "";

    /**
     * Entity Field name associated with the department in
     */
    String joinName() default "";
}
