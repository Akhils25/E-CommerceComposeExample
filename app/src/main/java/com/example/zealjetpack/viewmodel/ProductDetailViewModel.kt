package com.example.zealjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zealjetpack.model.Product

class ProductDetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _productsDetails = MutableLiveData<Product>()
    val productsDetails: LiveData<Product> get() = _productsDetails

    private val productsDataList = ArrayList<Product>()


    fun getProductDetails(productId: String) {
        _isLoading.value = true
        productsDataList.clear()
        productsDataList.add(
            Product(
                productId = "101",
                productName = "Fresh Chicken Curry Cut",
                productCategoryName = "Chicken",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 280.0,
                sellingPrice = 220.0,
                offerValues = 20,
                offerType = "PERCENTAGE",
                quantity = 500,
                unitType = "gm",
                stockStatus = true,
                productRemarks = "Fresh farm chicken"
            )
        )
        productsDataList.add(
            Product(
                productId = "102",
                productName = "Fresh Seer Fish",
                productCategoryName = "Fish",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 350.0,
                sellingPrice = 299.0,
                offerValues = 15,
                offerType = "PERCENTAGE",
                quantity = 1,
                unitType = "kg",
                stockStatus = true,
                productRemarks = "Sea fresh"
            )
        )
        productsDataList.add(
            Product(
                productId = "103",
                productName = "Premium Goat Mutton",
                productCategoryName = "Mutton",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 750.0,
                sellingPrice = 699.0,
                offerValues = 50,
                offerType = "FLAT",
                quantity = 500,
                unitType = "gm",
                stockStatus = true,
                productRemarks = "Tender & juicy"
            )
        )
        productsDataList.add(
            Product(
                productId = "104",
                productName = "Farm Fresh Eggs (6 pcs)",
                productCategoryName = "Eggs",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 90.0,
                sellingPrice = 80.0,
                offerValues = 10,
                offerType = "PERCENTAGE",
                quantity = 6,
                unitType = "pcs",
                stockStatus = true,
                productRemarks = "Organic eggs"
            )
        )
        productsDataList.add(
            Product(
                productId = "MEAT001",
                productName = "Fresh Chicken Curry Cut",
                productCategoryName = "Meats",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 650.0,
                sellingPrice = 585.0,
                offerValues = 10,
                offerType = "PERCENTAGE",
                quantity = 1,
                unitType = "500g",
                stockStatus = true,
                productRemarks = "Farm fresh, antibiotic-free"
            )
        )
        productsDataList.add(
            Product(
                productId = "MEAT002",
                productName = "Fresh Mutton Boneless",
                productCategoryName = "Meats",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 950.0,
                sellingPrice = 900.0,
                offerValues = 50,
                offerType = "FLAT",
                quantity = 1,
                unitType = "500g",
                stockStatus = true,
                productRemarks = "Tender and fresh cut"
            )
        )
        productsDataList.add(
            Product(
                productId = "MEAT003",
                productName = "Fresh Fish Fillet",
                productCategoryName = "Meats",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 780.0,
                sellingPrice = 663.0,
                offerValues = 15,
                offerType = "PERCENTAGE",
                quantity = 1,
                unitType = "500g",
                stockStatus = true,
                productRemarks = "Cleaned & ready to cook"
            )
        )
        productsDataList.add(
            Product(
                productId = "MEAT004",
                productName = "Pork Belly Slices",
                productCategoryName = "Meats",
                productImage = "https://i.postimg.cc/SR5wBqxb/fea7cdf28187ec4541c740567cafbb71e01fc60c.jpg",
                productMRP = 420.0,
                sellingPrice = 420.0,
                offerValues = 10,
                offerType = "PERCENTAGE",
                quantity = 1,
                unitType = "500g",
                stockStatus = true,
                productRemarks = "Juicy and premium quality",
            )
        )
        _productsDetails.value = productsDataList.find { it.productId == productId }
        _isLoading.value = false
    }
}