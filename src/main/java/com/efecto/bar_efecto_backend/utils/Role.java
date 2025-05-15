package com.efecto.bar_efecto_backend.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {

    EMPLOYEE(Arrays.asList(
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_PRODUCT_BY_ID,
            Permission.READ_ALL_CATEGORIES,
            Permission.READ_CATEGORY_BY_ID,
            Permission.READ_ALL_PRODUCT_CATEGORY,
            Permission.READ_ALL_TABLES,
            Permission.READ_TABLE_BY_ID,
            Permission.READ_ALL_PAYMENTMETHOD,
            Permission.READ_PAYMENTMETHOD_BY_ID
    )),

    ADMINISTRATOR(Arrays.asList(
            Permission.CREATE_ONE_USER,
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_PRODUCT_BY_ID,
            Permission.READ_ALL_PRODUCT_CATEGORY,
            Permission.READ_ALL_CATEGORIES,
            Permission.READ_CATEGORY_BY_ID,
            Permission.DELETE_ONE_PRODUCT,
            Permission.DELETE_ONE_CATEGORY,
            Permission.UPDATE_ONE_PRODUCT,
            Permission.UPDATE_ONE_CATEGORY,
            Permission.SAVE_ONE_PRODUCT,
            Permission.SAVE_ONE_CATEGORY,
            Permission.READ_ALL_TABLES,
            Permission.READ_TABLE_BY_ID,
            Permission.SAVE_ONE_TABLE,
            Permission.UPDATE_ONE_TABLE,
            Permission.DELETE_ONE_TABLE,
            Permission.READ_ALL_PAYMENTMETHOD,
            Permission.READ_PAYMENTMETHOD_BY_ID,
            Permission.SAVE_ONE_PAYMENTMETHOD,
            Permission.UPDATE_ONE_PAYMENTMETHOD,
            Permission.DELETE_ONE_PAYMENTMETHOD
    ));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
