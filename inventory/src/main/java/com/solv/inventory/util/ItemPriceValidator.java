package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgumentException;

public class ItemPriceValidator {
    public static boolean isValidPrice(double price) throws BadArgumentException {
        if(price<0 ) {
            throw new BadArgumentException("Not a valid price");
        }
        else {
            return true;
        }
    }
}
