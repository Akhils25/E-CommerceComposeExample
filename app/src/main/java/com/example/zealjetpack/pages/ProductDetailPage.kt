package com.example.zealjetpack.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zealjetpack.Utility.LoadingView
import com.example.zealjetpack.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailPage(

) {
    val viewModel: ProductDetailViewModel = viewModel()
    val loading by viewModel.isLoading.observeAsState(initial = false)

    if (loading) {
        LoadingView()
    } else {
        SetProductDetailsContent()
    }
}

@Composable
fun SetProductDetailsContent() {
}
