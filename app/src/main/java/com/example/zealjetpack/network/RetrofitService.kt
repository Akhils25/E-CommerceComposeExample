package com.oges.zealfresh.network

import com.example.zealjetpack.model.FeaturedProductResponseModel
import com.example.zealjetpack.model.HomeDataResponseModel
import com.example.zealjetpack.model.NewArrivalsProductResponseModel
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST("home")
    fun getHomeData(
        @Body request: RequestBody
    ): Call<HomeDataResponseModel>
    @Headers("Accept: application/json")
    @POST("featured-products")
    fun getFeaturedProduct(
        @Body request: RequestBody
    ): Call<FeaturedProductResponseModel>

    @Headers("Accept: application/json")
    @POST("latest-products")
    fun getNewArrivalsProduct(
        @Body request: RequestBody
    ): Call<NewArrivalsProductResponseModel>

    companion object {
        private var retrofitService: RetrofitService? = null
        private var prevUrl = ""

        fun getInstance(url: String = Const.BASE_URL): RetrofitService {
            if (prevUrl != url) {
                getApiService(url)
                prevUrl = url
            } else {
                if (retrofitService == null) {
                    getApiService(url)
                }
            }
            return retrofitService!!
        }

        private fun getApiService(url: String) {

            val retrofit = Retrofit.Builder().baseUrl(url).client(
                    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS).build()
                ).addConverterFactory(GsonConverterFactory.create()).build()
            retrofitService = retrofit.create(RetrofitService::class.java)
        }
    }
}