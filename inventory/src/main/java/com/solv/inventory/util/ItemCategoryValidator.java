package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgumentException;

public class ItemCategoryValidator {
    public static boolean isValidCategory(String s) throws BadArgumentException {
        if ((s==null || s.isEmpty() || !(s.matches("[a-zA-Z]+")))){
            throw new BadArgumentException("Not a valid Name");
        }
        else{
            return true;
        }
    }
}
