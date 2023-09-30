package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgumentException;

public class ItemNameValidator {
    public static boolean validName(String s) throws BadArgumentException {
        if ((s==null || s.isEmpty() || !(s.matches("[a-zA-Z]+")))){
            throw new BadArgumentException("Not a valid Name");
        }
        else{
            return true;
        }
    }
}
