package com.example.zealjetpack.model


import com.google.gson.annotations.SerializedName

data class NewArrivalsProductResponseModel(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("status")
    val status: String?
) {
    data class Data(
        @SerializedName("availableProductCount")
        val availableProductCount: Int?,
        @SerializedName("currency")
        val currency: String?,
        @SerializedName("expirationDate")
        val expirationDate: String?,
        @SerializedName("isInCart")
        var isInCart: Boolean?,
        @SerializedName("offerType")
        val offerType: String?,
        @SerializedName("offerValues")
        val offerValues: Int?,
        @SerializedName("productCategoryName")
        val productCategoryName: String?,
        @SerializedName("productId")
        val productId: String?,
        @SerializedName("productImage")
        val productImage: String?,
        @SerializedName("productMRP")
        val productMRP: Double?,
        @SerializedName("productName")
        val productName: String?,
        @SerializedName("productQuantityInCart")
        var productQuantityInCart: Int?,
        @SerializedName("productRemarks")
        val productRemarks: String?,
        @SerializedName("quantity")
        val quantity: Int?,
        @SerializedName("sellingPrice")
        val sellingPrice: Double?,
        @SerializedName("stockStatus")
        val stockStatus: Boolean?,
        @SerializedName("totalProductCount")
        val totalProductCount: Int?,
        @SerializedName("indirectCartAdd")
        val indirectCartAdd: Int?,
        @SerializedName("indirectCartmessage")
        val indirectCartmessage: String?,
        @SerializedName("unitType")
        val unitType: String?
    )

    data class Pagination(
        @SerializedName("current_page")
        val currentPage: Int?,
        @SerializedName("from")
        val from: Int?,
        @SerializedName("last_page")
        val lastPage: Int?,
        @SerializedName("per_page")
        val perPage: Int?,
        @SerializedName("to")
        val to: Int?,
        @SerializedName("total")
        val total: Int?
    )
}