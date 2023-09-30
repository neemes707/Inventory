package com.solv.inventory.constant.enums;

public enum UserType {
        SUPER_ADMIN("SUPER_ADMIN"),
        ADMIN("ADMIN"),
        BRANCH_MANAGER("BRANCH_MANAGER"),
        WAREHOUSE_MANAGER("WAREHOUSE_MANAGER"),
        ACCOUNTANT("ACCOUNTANT"),
        CUSTOMER("CUSTOMER");
        private final String value;

        UserType(String value) {
                this.value = value;
        }

        public String getValue() {
                return this.value;
        }
}
