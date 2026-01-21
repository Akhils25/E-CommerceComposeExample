package com.example.zealjetpack.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.zealjetpack.Utility.LoadingView
import com.example.zealjetpack.model.FeaturedProductResponseModel
import com.example.zealjetpack.model.HomeDataResponseModel
import com.example.zealjetpack.model.NewArrivalsProductResponseModel
import com.example.zealjetpack.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@Composable
fun HomePage(
    onItemClick: (String) -> Unit = {},
    onBuyClick: (String) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val loading by viewModel.isLoading.observeAsState(initial = false)
    val banners by viewModel.bannerData.observeAsState(emptyList())
    val featuredProducts by viewModel.featuredProducts.observeAsState(emptyList())
    val newArrivalsProducts by viewModel.newArrivalsProducts.observeAsState(emptyList())
    LaunchedEffect(Unit) {
        viewModel.getHomeRequiredData()
        viewModel.fetchFeaturedProduct()
        viewModel.fetchNewArrivalsProduct()
    }
    if (loading) {
        LoadingView()
    } else {
        HomeContent(
            banners = banners,
            featuredProductResponseModel = featuredProducts,
            newArrivalsProductResponseModel = newArrivalsProducts,
            onItemClick,
            onBuyClick
        )
    }
}

@Composable
fun HomeContent(
    banners: List<HomeDataResponseModel.Banner>,
    featuredProductResponseModel: List<FeaturedProductResponseModel.Data>,
    newArrivalsProductResponseModel: List<NewArrivalsProductResponseModel.Data>,
    onItemClick: (String) -> Unit,
    onBuyClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                notificationCount = 0,
                onNotificationClick = {},
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            item {
                BannerAutoSlider(banners = banners)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                FeaturedProductsSection(
                    products = featuredProductResponseModel,
                    onItemClick,
                    onBuyClick
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                NewArrivalsProductsSection(
                    products = newArrivalsProductResponseModel,
                    onItemClick,
                    onBuyClick
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    notificationCount: Int = 0,
    onNotificationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Zeal Fresh",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        actions = {
            Box {
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.Red
                    )
                }

                if (notificationCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = (-6).dp, y = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.Green
                        ) {
                            Text(
                                text = notificationCount.toString(),
                                color = Color.White,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun BannerAutoSlider(
    banners: List<HomeDataResponseModel.Banner>
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState { banners.size }

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 10.dp)
    ) { page ->
        BannerItem(banner = banners[page])
    }
}

@Composable
fun BannerItem(
    banner: HomeDataResponseModel.Banner
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box {
            AsyncImage(
                model = banner.bannerImage,
                contentDescription = banner.bannerTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun NewArrivalsProductsSection(
    products: List<NewArrivalsProductResponseModel.Data>,
    onItemClick: (String) -> Unit,
    onBuyClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Arrivals",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All →",
                color = Color.Red,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(products) { it ->
                ProductCardNewArrivals(
                    it,
                    onItemClick,
                    onBuyClick
                ) // For New Arrivals Products Listing
            }
        }
    }
}

@Composable
fun FeaturedProductsSection(
    products: List<FeaturedProductResponseModel.Data>,
    onItemClick: (String) -> Unit,
    onBuyClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Featured Products",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All →",
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(products) { it ->
                ProductCardFeatured(it, onItemClick, onBuyClick) // For Featured Products Listing
            }
        }
    }
}

@Composable
fun ProductCardNewArrivals(
    product: NewArrivalsProductResponseModel.Data,
    onItemClick: (String) -> Unit,
    onBuyClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(end = 12.dp)
            .background(Color.White),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = {
            onItemClick(product.productId ?: "")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White)
        ) {

            // Image + Offer badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                ) {
                    AsyncImage(
                        model = product.productImage,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                }
                Text(
                    text = "${product.offerValues}% OFF",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.productName ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                minLines = 2
            )

            Text(
                text = "${product.quantity} ${product.unitType}",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "₹${product.sellingPrice}",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "₹${product.productMRP}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onBuyClick(product.productId ?: "") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("BUY", color = Color.White)
            }
        }
    }
}

@Composable
fun ProductCardFeatured(
    product: FeaturedProductResponseModel.Data,
    onItemClick: (String) -> Unit,
    onBuyClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(end = 12.dp)
            .background(Color.White),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = {
            onItemClick(product.productId ?: "")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White)
        ) {

            // Image + Offer badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                ) {
                    AsyncImage(
                        model = product.productImage,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                }
                Text(
                    text = "${product.offerValues}% OFF",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.productName ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                minLines = 2
            )

            Text(
                text = "${product.quantity} ${product.unitType}",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "₹${product.sellingPrice}",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "₹${product.productMRP}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onBuyClick(product.productId ?: "") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("BUY", color = Color.White)
            }
        }
    }
}



