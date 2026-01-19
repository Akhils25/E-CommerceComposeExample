package com.example.zealjetpack.model

data class Product(
    val productId: String,
    val productName: String,
    val productCategoryName: String,
    val productImage: String,
    val productMRP: Double,
    val sellingPrice: Double,
    val offerValues: Int,
    val offerType: String,
    val quantity: Int,
    val unitType: String,
    val stockStatus: Boolean,
    val productRemarks: String
)

