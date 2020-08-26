package codeswarm.io.perun.utility;

public class Constants {

    public static final String HEADER_OAUTH_TOKEN_NAME = "X-Auth-Token";
    public static final String ADMIN_EMAIL = "admin@imagene.me";

    /**
     * JSON RESPONSE ERROR MESSAGES
     */
    public static final String ERROR_EMAIL_REQURIED = "The email is required";
    public static final String ERROR_EMAIL_ALREADY_EXISTS = "This email already exists";
    public static final String ERROR_PASSWORD_REQURIED = "The password is required";
    public static final String ERROR_PASSWORD_POLICY = "The password you entered doesn't meet the minimum security requirements";
    public static final String ERROR_FIRST_NAME_REQURIED = "The first name is required";
    public static final String ERROR_LAST_NAME_REQURIED = "The last name is required";
    public static final String ERROR_PASSWORD_IS_NOT_VALID = "This password is not valid";
    public static final String ERROR_INCORRECT_EMAIL_OR_PASSWORD = "Incorrect email or password";
    public static final String ERROR_THERE_IS_NO_USER_YET = "There is no user yet";

    public static final String ERROR_VARIANT_ID_REQUIRED = "Variant id number required";
    public static final String ERROR_THERE_IS_NO_VARIANT_YET = "There is no variant yet";
    public static final String ERROR_VARIANT_POSITION_REQUIRED = "Variant position cannot be empty";
    public static final String ERROR_VARIANT_ALTERATION_REQUIRED = "Variant alteration cannot be empty";
    public static final String ERROR_VARIANT_CHROMOSOME_REQUIRED = "Variant chromosome cannot be empty";
    public static final String ERROR_VARIANT_DESCRIPTION_REQUIRED = "Variant description cannot be empty";

    /**
     * JSON STATUS
     */
    public static final String STATUS_FAILED = "Failed";
    public static final String STATUS_PASSED = "Passed";
}
