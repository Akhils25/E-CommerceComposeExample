package com.example.zealjetpack.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.zealjetpack.model.Product
import com.example.zealjetpack.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailPage(
    productId: String,
) {
    val viewModel: ProductDetailViewModel = viewModel()
    val loading by viewModel.isLoading.observeAsState(initial = false)
    val product by viewModel.productsDetails.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getProductDetails(productId)
    }
    if (loading) {
        LoadingView()
    } else {
        ProductDetailsScreen(product)
    }
}

@Composable
fun ProductDetailsScreen(
    product: Product?
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // Top Image
            AsyncImage(
                model = product?.productImage,
                contentDescription = product?.productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))
        }

        // White rounded card (overlapping)
        ProductInfoCard(product)

        BottomCartBar(
            product = product,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}

@Composable
fun ProductInfoCard(product: Product?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 350.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = product?.productName ?: "",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${product?.quantity} ${product?.unitType}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            PriceSection(product)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product?.productRemarks ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Fresh Chicken Curry Cut is carefully processed and hygienically packed to ensure the highest quality and taste for your everyday cooking needs. Sourced from healthy farm-raised chickens, this product is freshly cut, cleaned, and prepared under strict quality standards to deliver tender, juicy pieces that cook evenly and absorb spices perfectly.\n" +
                        "\n" +
                        "Each pack contains expertly cut curry pieces including bone-in portions that add rich flavor and texture to your dishes. The chicken is cleaned thoroughly without the use of harsh chemicals or preservatives, making it safe, healthy, and suitable for all age groups. Our cold-chain delivery system ensures that the product remains fresh from farm to kitchen.\n" +
                        "This curry cut chicken is perfect for traditional Indian recipes such as chicken curry, masala, pepper chicken, and slow-cooked stews. Marinate the pieces with spices and yogurt for at least 30 minutes to enhance flavor and tenderness. Cook thoroughly until the meat is soft and the juices run clear.\n" +
                        "We follow a strict farm-to-fork approach to maintain freshness and quality. Each pack undergoes multiple quality checks before delivery. If you are not satisfied with the product, we offer a no-questions-asked return policy.\n" +
                        "For best taste, allow the chicken to rest at room temperature for 5 minutes before cooking.",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PriceSection(product: Product?) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = "₹${product?.sellingPrice?.toInt()}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "₹${product?.productMRP?.toInt()}",
            style = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = TextDecoration.LineThrough
            ),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.width(8.dp))

        if (product?.offerType == "PERCENTAGE") {
            Text(
                text = "${product.offerValues}% off",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BottomCartBar(product: Product?, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "₹${product?.sellingPrice?.toInt()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Inclusive of all taxes",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                enabled = product?.stockStatus ?: false,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32),
                    contentColor = Color.White
                )
            ) {
                Text("BUY")
            }
        }
    }
}


