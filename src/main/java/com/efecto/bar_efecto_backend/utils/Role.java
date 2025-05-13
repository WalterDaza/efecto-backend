package com.efecto.bar_efecto_backend.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {

    EMPLOYEE(Arrays.asList(
            Permission.READ_ALL_PRODUCTS,
            Permission.READ_PRODUCT_BY_ID,
            Permission.READ_ALL_CATEGORIES,
            Permission.READ_CATEGORY_BY_ID,
            Permission.READ_ALL_PRODUCT_CATEGORY
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
            Permission.SAVE_ONE_CATEGORY
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
