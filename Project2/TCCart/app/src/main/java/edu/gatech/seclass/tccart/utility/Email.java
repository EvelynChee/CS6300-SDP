package edu.gatech.seclass.tccart.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import edu.gatech.seclass.services.EmailService;

/**
 * Created by bulentcoskun on 4/6/2016.
 */
public final class Email {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static boolean IsValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean SendEmail(String emailaddress, String subject, String msg ){
       return  EmailService.sendEMail(emailaddress, subject, msg );
    }
}
