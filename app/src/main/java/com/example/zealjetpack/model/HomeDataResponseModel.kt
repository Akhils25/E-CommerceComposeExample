package com.example.zealjetpack.model


import com.google.gson.annotations.SerializedName

data class  HomeDataResponseModel(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("banner")
    val banner: List<Banner?>?,
    @SerializedName("cartCount")
    val cartCount: Int?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("customerRating")
    val customerRating: List<CustomerRating?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("productFeature")
    val productFeature: List<ProductFeature?>?,
    @SerializedName("referAndEarn")
    val referAndEarn: ReferAndEarn?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("watchNow")
    val watchNow: WatchNow?,
    @SerializedName("zealFreshWay")
    val zealFreshWay: ArrayList<ZealFreshWay>
) {
    data class Address(
        @SerializedName("addressType")
        val addressType: String?,
        @SerializedName("cityTown")
        val cityTown: String?,
        @SerializedName("floor")
        val floor: String?,
        @SerializedName("houseNameArea")
        val houseNameArea: String?,
        @SerializedName("landmark")
        val landmark: String?,
        @SerializedName("pincode")
        val pincode: Int?,
        @SerializedName("state")
        val state: String?
    )

    data class Banner(
        @SerializedName("bannerId")
        val bannerId: String?,
        @SerializedName("bannerImage")
        val bannerImage: String?,
        @SerializedName("bannerTitle")
        val bannerTitle: String?
    )

    data class CustomerRating(
        @SerializedName("customerName")
        val customerName: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("rating")
        val rating: String?,
        @SerializedName("review")
        val review: String?
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

    data class ProductFeature(
        @SerializedName("featureId")
        val featureId: String?,
        @SerializedName("featureImage")
        val featureImage: String?,
        @SerializedName("featureTitle")
        val featureTitle: String?
    )

    data class ReferAndEarn(
        @SerializedName("description")
        val description: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("message")
        val message: String?,
        @SerializedName("policy")
        val policy: Policy?,
        @SerializedName("steps")
        val steps: List<Step?>?,
        @SerializedName("thumbnail")
        val thumbnail: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("whatsappUrl")
        val whatsappUrl: String?
    ) {
        data class Policy(
            @SerializedName("description")
            val description: String?,
            @SerializedName("title")
            val title: String?
        )

        data class Step(
            @SerializedName("referStepsDescription")
            val referStepsDescription: String?,
            @SerializedName("referStepsId")
            val referStepsId: Int?,
            @SerializedName("referStepsTitle")
            val referStepsTitle: String?
        )
    }

    data class WatchNow(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("thumbnail")
        val thumbnail: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("videoUrl")
        val videoUrl: String?
    )

    data class ZealFreshWay(
        @SerializedName("zealfreshWayId")
        val zealfreshWayId: Int?,
        @SerializedName("zealfreshWayImage")
        val zealfreshWayImage: String?
    )
}