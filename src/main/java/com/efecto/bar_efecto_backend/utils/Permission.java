package com.efecto.bar_efecto_backend.utils;

public enum Permission {

    //Register ****************************************
    CREATE_ONE_USER,

    //Products ****************************************

    READ_ALL_PRODUCTS,
    READ_PRODUCT_BY_ID,
    READ_ALL_PRODUCT_CATEGORY,
    SAVE_ONE_PRODUCT,
    UPDATE_ONE_PRODUCT,
    DELETE_ONE_PRODUCT,

    //Category *****************************************

    READ_ALL_CATEGORIES,
    READ_CATEGORY_BY_ID,
    SAVE_ONE_CATEGORY,
    UPDATE_ONE_CATEGORY,
    DELETE_ONE_CATEGORY,


    //Tables ********************************************
    READ_ALL_TABLES,
    READ_TABLE_BY_ID,
    SAVE_ONE_TABLE,
    UPDATE_ONE_TABLE,
    DELETE_ONE_TABLE,

    //Payment Method ************************************
    READ_ALL_PAYMENTMETHOD,
    READ_PAYMENTMETHOD_BY_ID,
    SAVE_ONE_PAYMENTMETHOD,
    UPDATE_ONE_PAYMENTMETHOD,
    DELETE_ONE_PAYMENTMETHOD

}
