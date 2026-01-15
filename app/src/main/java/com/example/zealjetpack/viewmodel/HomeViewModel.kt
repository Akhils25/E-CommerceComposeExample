package com.example.zealjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zealjetpack.Constants
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
        customerId: String = "",
        version: String = "1.0"
    ) {
        _isLoading.value = true

        val requestBody = JSONObject().apply {
            put("mappingCode", Constants.mappingCode)
            put("deviceApkVersion", version)
            put("deviceType", Constants.deviceType)
            put("customerId", customerId)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        try {
            RetrofitService.getInstance(Const.BASE_URL).getHomeData(
                requestBody
            ).enqueue(object : Callback<HomeDataResponseModel> {
                override fun onResponse(
                    call: Call<HomeDataResponseModel>,
                    response: Response<HomeDataResponseModel>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body()?.code == 200) {
                        val bannerData = response.body()?.banner?.filterNotNull() ?: emptyList()
                        val featuredData =
                            response.body()?.productFeature?.filterNotNull() ?: emptyList()
                        //_address.value = response.body()?.address
                        //_cartCount.value = response.body()?.cartCount ?: 0
                        if (!bannerData.isNullOrEmpty()) {
                            bannerList.clear()
                            bannerList.addAll(bannerData)
                            _bannersData.value = bannerList
                        }
                        /*featuresFullList.clear()
                        featuresFullList.addAll(featuredData)
                        _featuresListData.value = featuresFullList
                        if (!response.body()?.zealFreshWay.isNullOrEmpty()) {
                            _zealWaysData.value = response.body()?.zealFreshWay
                        }
                        if (response.body()?.watchNow != null) {
                            _youtubeVideoData.value = response.body()?.watchNow
                        }
                        if (response.body()?.referAndEarn != null) {
                            _referFriend.value = response.body()?.referAndEarn
                        }*/
                    } else {
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<HomeDataResponseModel>, t: Throwable) {
                     _isLoading.value = false
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
        }
    }

    fun fetchFeaturedProduct(customerId: String) {
        _isLoading.value = true

        val requestBody = JSONObject().apply {
            put("mappingCode", Constants.mappingCode)
            put("customerId", customerId)
            put("page", 1)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        try {
            RetrofitService.getInstance(Const.BASE_URL).getFeaturedProduct(
                requestBody
            ).enqueue(object : Callback<FeaturedProductResponseModel> {
                override fun onResponse(
                    call: Call<FeaturedProductResponseModel>,
                    response: Response<FeaturedProductResponseModel>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body()?.code == 200) {
                        val data = response.body()?.data?.filterNotNull() ?: emptyList()
                        if (!data.isNullOrEmpty()) {
                            featuredFullList.clear()
                            featuredFullList.addAll(data)
                            _featuredProducts.value = featuredFullList
                        }
                    } else {
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<FeaturedProductResponseModel>, t: Throwable) {
                    _isLoading.value = false
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
        }
    }

    fun fetchNewArrivalsProduct(customerId: String) {
        _isLoading.value = true

        val requestBody = JSONObject().apply {
            put("mappingCode", Constants.mappingCode)
            put("customerId", customerId)
            put("page", 1)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        try {
            RetrofitService.getInstance(Const.BASE_URL).getNewArrivalsProduct(
                requestBody
            ).enqueue(object : Callback<NewArrivalsProductResponseModel> {
                override fun onResponse(
                    call: Call<NewArrivalsProductResponseModel>,
                    response: Response<NewArrivalsProductResponseModel>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body()?.code == 200) {
                        val data = response.body()?.data?.filterNotNull() ?: emptyList()
                        if (data.isNotEmpty()) {
                            newArrivalsFullList.clear()
                            newArrivalsFullList.addAll(data)
                            _newArrivalsProducts.value = newArrivalsFullList
                        }
                    } else {
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<NewArrivalsProductResponseModel>, t: Throwable) {
                    _isLoading.value = false
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
        }
    }

}