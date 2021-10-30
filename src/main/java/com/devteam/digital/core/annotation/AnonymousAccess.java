package com.devteam.digital.core.annotation;

import java.lang.annotation.*;

/**
 *  Used to mark anonymous access methods
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
