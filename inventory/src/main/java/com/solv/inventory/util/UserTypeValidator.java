package com.solv.inventory.util;

import com.solv.inventory.constant.enums.UserType;
import com.solv.inventory.exceptions.BadArgumentException;
public class UserTypeValidator {
    public static boolean isValidUserType(String userType) throws BadArgumentException {
        if(userType==null || userType.isEmpty())
            throw new BadArgumentException("User Type cannot be Null");
        for(UserType u:UserType.values())
        {
            if(u.getValue().equalsIgnoreCase(userType)){
                return true;
            }
        }
        throw new BadArgumentException("Invalid User Type");
    }
}
