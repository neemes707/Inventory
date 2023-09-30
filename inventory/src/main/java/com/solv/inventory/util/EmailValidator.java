package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgumentException;
import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isValidEmail(String email) throws BadArgumentException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null ||!( pattern.matcher(email).matches())){
            throw new BadArgumentException("Invalid Email");
        }
        else {
            return true;
        }
    }
}
