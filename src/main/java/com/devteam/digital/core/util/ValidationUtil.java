package com.devteam.digital.core.util;


import com.devteam.digital.core.util.exception.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

public class ValidationUtil{

    /**
     * Verify empty
     */
    public static void isNull(Object obj, String entity, String parameter , Object value){
        if(ObjectUtil.isNull(obj)){
            String msg = entity + " does not exist: "+ parameter +" is "+ value;
            throw new BadRequestException(msg);
        }
    }

    /**
     * Verify whether it is a mailbox
     */
    public static boolean isEmail(String email) {
        return new EmailValidator().isValid(email, null);
    }
}
