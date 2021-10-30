package com.devteam.digital.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    //  The attribute name of the basic object
    String propName() default "";
    //  inquiry mode
    Type type() default Type.EQUAL;

    /**
     * The attribute name of the connection query, such as dept in the User class
     */
    String joinName() default "";

    /**
     *Default left connection
     */
    Join join() default Join.LEFT;

    /**
     * Multi-field fuzzy search, only supports String type fields, multiple separated by commas, such as @Query(blurry = "email,username")
     */
    String blurry() default "";

    enum Type {
        //  equa
        EQUAL
        //  greater or equal to
        ,GREATER_THAN
        // Less than or equal to
        ,LESS_THAN
        //  Fuzzy query
        ,INNER_LIKE
        // Left fuzzy query
        ,LEFT_LIKE
        // Right fuzzy query
        ,RIGHT_LIKE
        // less than
        ,LESS_THAN_NQ
        // contains
        ,IN
        // not equal to
        ,NOT_EQUAL
        // between
        ,BETWEEN
        // not null
        ,NOT_NULL
        // Is empty
        ,IS_NULL
    }

    /**
     *Suitable for simple connection query, please customize the annotation for complex ones, or use sql query
     */
    enum Join {
        LEFT, RIGHT, INNER
    }

}