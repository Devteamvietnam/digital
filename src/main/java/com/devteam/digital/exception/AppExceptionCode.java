package com.devteam.digital.exception;

import org.springframework.http.HttpStatus;

public class AppExceptionCode {

    //USER EXCEPTIONS
    public static AppException USER_ALREADY_REGISTERED_400_4000 = new AppException(HttpStatus.BAD_REQUEST, 4000, "User has already registered.", "", "");
    public static AppException USER_PIN_NOTFOUND_400_4001 = new AppException(HttpStatus.BAD_REQUEST, 4001, "Pin not found.", "", "");
    public static AppException USER_PIN_INVALID_400_4002 = new AppException(HttpStatus.BAD_REQUEST, 4002, "Pin is invalid.", "", "");
    public static AppException USER_LOGIN_FAILED_400_4003 = new AppException(HttpStatus.BAD_REQUEST, 4003, "Login failed.", "", "");
    public static AppException USER_SENDING_VERIFICATION_FAILED_400_4004 = new AppException(HttpStatus.BAD_REQUEST, 4004, "We cannot send a verification code at the moment. Please come back later", "Service provider problem", "");
    public static AppException USER_NOTFOUND_400_4005 = new AppException(HttpStatus.BAD_REQUEST, 4005, "User not found.", "", "");
    public static AppException USER_REGISTRATION_FAILED_500_4006 = new AppException(HttpStatus.INTERNAL_SERVER_ERROR, 4006, "User registration failed.", "", "");
    public static AppException USER_RESET_PASSWORD_FAILED_500_4007 = new AppException(HttpStatus.INTERNAL_SERVER_ERROR, 4007, "Reset password failed.", "", "");



    //HR STUFF
    public static AppException LEAVE_INVALID_REQUEST_400_1000 = new AppException(HttpStatus.BAD_REQUEST, 1000, "Invalid request", "","");

    public static AppException APPROVAL_INVALID_REQUEST_400_2000 = new AppException(HttpStatus.BAD_REQUEST, 2000, "Invalid request", "","");
    public static AppException APPROVAL_DOC_CANNOT_BE_MODIFIED_400_2001 = new AppException(HttpStatus.BAD_REQUEST, 2001, "Approved documents cannot be modified ", "","");
    public static AppException APPROVAL_DOC_IS_READONLY_400_2002 = new AppException(HttpStatus.BAD_REQUEST, 2002, "Readonly documents cannot be modified ", "","");
    public static AppException APPROVAL_SECURITY_LEVEL_CANNOTBESET_400_2003 = new AppException(HttpStatus.BAD_REQUEST, 2003, "Only the owner or resolvers can update the security level", "","");
    public static AppException APPROVAL_NOTALLOWED_TOADD_CC_400_2004 = new AppException(HttpStatus.BAD_REQUEST, 2004, "Not allowed to add more cc references", "","");

    //COMMON
    public static AppException COMMON_FILE_INVALIDPATH_400_9000 = new AppException(HttpStatus.BAD_REQUEST, 9000, "Filename contains invalid path sequence %s", "","");
    public static AppException COMMON_FILE_IOEXCEPTION_500_9000 = new AppException(HttpStatus.INTERNAL_SERVER_ERROR, 9000, "Could not store file %s. Please try again!", "","");

}
