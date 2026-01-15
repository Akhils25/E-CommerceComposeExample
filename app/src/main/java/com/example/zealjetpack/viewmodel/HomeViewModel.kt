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
        _isLoading.value = true
        newArrivalsFullList.clear()
        newArrivalsFullList.add(
            NewArrivalsProductResponseModel.Data(
                availableProductCount = 120,
                currency = "INR",
                expirationDate = "2026-03-15",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 10,
                productCategoryName = "Meats",
                productId = "MEAT001",
                productImage = "https://images.unsplash.com/photo-1604908177225-3e7c8fbd4a63",
                productMRP = 650.0,
                productName = "Fresh Chicken Curry Cut",
                productQuantityInCart = 0,
                productRemarks = "Farm fresh, antibiotic-free",
                quantity = 1,
                sellingPrice = 585.0,
                stockStatus = true,
                totalProductCount = 120,
                indirectCartAdd = 0,
                indirectCartmessage = null,
                unitType = "500g"
            )
        )
        newArrivalsFullList.add(
            NewArrivalsProductResponseModel.Data(
                availableProductCount = 120,
                currency = "INR",
                expirationDate = "2026-03-15",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 10,
                productCategoryName = "Meats",
                productId = "MEAT001",
                productImage = "https://images.unsplash.com/photo-1604908177225-3e7c8fbd4a63",
                productMRP = 650.0,
                productName = "Fresh Chicken Curry Cut",
                productQuantityInCart = 0,
                productRemarks = "Farm fresh, antibiotic-free",
                quantity = 1,
                sellingPrice = 585.0,
                stockStatus = true,
                totalProductCount = 120,
                indirectCartAdd = 0,
                indirectCartmessage = null,
                unitType = "500g"
            )
        )
        newArrivalsFullList.add(
            NewArrivalsProductResponseModel.Data(
                availableProductCount = 80,
                currency = "INR",
                expirationDate = "2026-02-28",
                isInCart = true,
                offerType = "FLAT",
                offerValues = 50,
                productCategoryName = "Meats",
                productId = "MEAT002",
                productImage = "https://images.unsplash.com/photo-1603046891744-76e6481cfdb3",
                productMRP = 950.0,
                productName = "Fresh Mutton Boneless",
                productQuantityInCart = 1,
                productRemarks = "Tender and fresh cut",
                quantity = 1,
                sellingPrice = 900.0,
                stockStatus = true,
                totalProductCount = 80,
                indirectCartAdd = 1,
                indirectCartmessage = "Already added to cart",
                unitType = "500g"
            )
        )
        newArrivalsFullList.add(
            NewArrivalsProductResponseModel.Data(
                availableProductCount = 60,
                currency = "INR",
                expirationDate = "2026-04-10",
                isInCart = false,
                offerType = "PERCENTAGE",
                offerValues = 15,
                productCategoryName = "Meats",
                productId = "MEAT003",
                productImage = "https://images.unsplash.com/photo-1625943555419-56a2cb596640",
                productMRP = 780.0,
                productName = "Fresh Fish Fillet",
                productQuantityInCart = 0,
                productRemarks = "Cleaned & ready to cook",
                quantity = 1,
                sellingPrice = 663.0,
                stockStatus = true,
                totalProductCount = 60,
                indirectCartAdd = 0,
                indirectCartmessage = null,
                unitType = "500g"
            )
        )
        newArrivalsFullList.add(
            NewArrivalsProductResponseModel.Data(
                availableProductCount = 100,
                currency = "INR",
                expirationDate = "2026-03-05",
                isInCart = false,
                offerType = null,
                offerValues = null,
                productCategoryName = "Meats",
                productId = "MEAT004",
                productImage = "https://images.unsplash.com/photo-1606756790138-261d2b21cd75",
                productMRP = 420.0,
                productName = "Pork Belly Slices",
                productQuantityInCart = 0,
                productRemarks = "Juicy and premium quality",
                quantity = 1,
                sellingPrice = 420.0,
                stockStatus = true,
                totalProductCount = 100,
                indirectCartAdd = 0,
                indirectCartmessage = null,
                unitType = "500g"
            )
        )
        _newArrivalsProducts.value = newArrivalsFullList
        _isLoading.value = false
    }
}