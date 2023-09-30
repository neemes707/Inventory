package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgumentException;
import java.util.regex.Pattern;

public class MobileNumberValidator {
    public static boolean isValidMobileNumber(String mobileNumber) throws BadArgumentException {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if( mobileNumber==null || !(pattern.matcher(mobileNumber).matches())){
            throw new BadArgumentException("Invalid Mobile Number");
        }
        else{
            return true;
        }
    }
}
