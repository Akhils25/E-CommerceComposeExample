package com.example.zealjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zealjetpack.model.FeaturedProductResponseModel
import com.example.zealjetpack.model.HomeDataResponseModel
import com.example.zealjetpack.model.NewArrivalsProductResponseModel
import com.oges.zealfresh.network.Const
import com.oges.zealfresh.network.RetrofitService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _bannersData = MutableLiveData<ArrayList<HomeDataResponseModel.Banner>>()
    val bannerData: LiveData<ArrayList<HomeDataResponseModel.Banner>> get() = _bannersData

    private val _featuredProducts = MutableLiveData<ArrayList<FeaturedProductResponseModel.Data>>()
    val featuredProducts: LiveData<ArrayList<FeaturedProductResponseModel.Data>> get() = _featuredProducts

    private val _newArrivalsProducts =
        MutableLiveData<ArrayList<NewArrivalsProductResponseModel.Data>>()
    val newArrivalsProducts: LiveData<ArrayList<NewArrivalsProductResponseModel.Data>> get() = _newArrivalsProducts

    private val newArrivalsFullList = ArrayList<NewArrivalsProductResponseModel.Data>()

    private val featuredFullList = ArrayList<FeaturedProductResponseModel.Data>()

    private val bannerList = ArrayList<HomeDataResponseModel.Banner>()


    fun getHomeRequiredData(

    ) {
        _isLoading.value = true

        bannerList.clear()
        bannerList.add(
            HomeDataResponseModel.Banner(
                "1",
                "https://images.pexels.com/photos/616401/pexels-photo-616401.jpeg",
                "banner One"
            )
        )
        bannerList.add(
            HomeDataResponseModel.Banner(
                "2",
                "https://images.pexels.com/photos/1435904/pexels-photo-1435904.jpeg",
                "banner Two"
            )
        )
        bannerList.add(
            HomeDataResponseModel.Banner(
                "3",
                "https://images.pexels.com/photos/264537/pexels-photo-264537.jpeg",
                "banner Three"
            )
        )
        _bannersData.value = bannerList

        _isLoading.value = false
    }

    fun fetchFeaturedProduct() {
        _isLoading.value = true

        featuredFullList.clear()
        featuredFullList.add(
            FeaturedProductResponseModel.Data(
                availableProductCount = 20,
                currency = "INR",
                expirationDate = "2026-01-31",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 20,
                productCategoryName = "Chicken",
                productId = "101",
                productImage = "https://images.pexels.com/photos/616401/pexels-photo-616401.jpeg",
                productMRP = 280.0,
                productName = "Fresh Chicken Curry Cut",
                productQuantityInCart = 0,
                productRemarks = "Fresh farm chicken",
                quantity = 500,
                sellingPrice = 220.0,
                stockStatus = true,
                totalProductCount = 100,
                unitType = "gm",
                indirectCartmessage = null,
                indirectCartAdd = 0
            )
        )
        featuredFullList.add(
            FeaturedProductResponseModel.Data(
                availableProductCount = 15,
                currency = "INR",
                expirationDate = "2026-02-10",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 15,
                productCategoryName = "Fish",
                productId = "102",
                productImage = "https://images.pexels.com/photos/264537/pexels-photo-264537.jpeg",
                productMRP = 350.0,
                productName = "Fresh Seer Fish",
                productQuantityInCart = 0,
                productRemarks = "Sea fresh",
                quantity = 1,
                sellingPrice = 299.0,
                stockStatus = true,
                totalProductCount = 60,
                unitType = "kg",
                indirectCartmessage = null,
                indirectCartAdd = 0
            )
        )
        featuredFullList.add(
            FeaturedProductResponseModel.Data(
                availableProductCount = 25,
                currency = "INR",
                expirationDate = "2026-01-25",
                isInCart = false,
                offerType = "FLAT",
                offerValues = 50,
                productCategoryName = "Mutton",
                productId = "103",
                productImage = "https://images.pexels.com/photos/1435904/pexels-photo-1435904.jpeg",
                productMRP = 750.0,
                productName = "Premium Goat Mutton",
                productQuantityInCart = 0,
                productRemarks = "Tender & juicy",
                quantity = 500,
                sellingPrice = 699.0,
                stockStatus = true,
                totalProductCount = 40,
                unitType = "gm",
                indirectCartmessage = null,
                indirectCartAdd = 0
            )
        )
        featuredFullList.add(
            FeaturedProductResponseModel.Data(
                availableProductCount = 30,
                currency = "INR",
                expirationDate = "2026-02-15",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 10,
                productCategoryName = "Eggs",
                productId = "104",
                productImage = "https://images.pexels.com/photos/4110255/pexels-photo-4110255.jpeg",
                productMRP = 90.0,
                productName = "Farm Fresh Eggs (6 pcs)",
                productQuantityInCart = 0,
                productRemarks = "Organic eggs",
                quantity = 6,
                sellingPrice = 80.0,
                stockStatus = true,
                totalProductCount = 200,
                unitType = "pcs",
                indirectCartmessage = null,
                indirectCartAdd = 0
            )
        )
        _featuredProducts.value = featuredFullList
        _isLoading.value = false
    }

    fun fetchNewArrivalsProduct() {
        _isLoading.value = false


    }

}