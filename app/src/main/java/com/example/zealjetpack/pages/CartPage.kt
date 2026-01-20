package com.example.zealjetpack.pages

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.zealjetpack.Utility.LoadingView
import com.example.zealjetpack.model.Product
import com.example.zealjetpack.viewmodel.CartViewModel

@Composable
fun CartPage(
    productId: String,
    onCheckoutClick: () -> Unit = {},
    onBack: () -> Unit,
    onNotification: () -> Unit
) {
    val viewModel: CartViewModel = viewModel()
    val loading by viewModel.isLoading.observeAsState(initial = false)
    val product by viewModel.productsDetails.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getProductCart(productId)
    }
    if (loading) {
        LoadingView()
    } else {
        CartContentPage(product, onBack, onNotification, onCheckoutClick)
    }
}

@Composable
fun CartContentPage(
    product: Product?,
    onBack: () -> Unit,
    onNotification: () -> Unit,
    onCheckoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CartToolbar(
                onBackClick = onBack,
                onNotificationClick = onNotification
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            product?.let {
                CartProductsSection(it, onCheckoutClick)
            }
        }
    }
}

@Composable
fun CartProductsSection(
    product: Product?,
    onCheckoutClick: () -> Unit
) {
    var showSuccessDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = 10.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = product?.productImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp),
                )

                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = product?.productName ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        minLines = 2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${product?.quantity} ${product?.unitType}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${product?.offerValues}% OFF",
                        color = Color.Red,
                        fontSize = 12.sp,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(
                    text = "Sub Total",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "₹${product?.sellingPrice}",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(
                    text = "Service Fee",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "₹10",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(
                    text = "Grand Total",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "₹${product?.sellingPrice?.plus(10)}",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                )
            }
        }
        Button(
            onClick = {
                showSuccessDialog = true
            },
            shape = RoundedCornerShape(12.dp),
            enabled = product?.stockStatus ?: false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Proceed to Pay")
        }
        if (showSuccessDialog) {
            OrderSuccessDialog(
                show = true,
                onDismiss = {
                    showSuccessDialog = false
                },
                onContinue = {
                    showSuccessDialog = false
                    onCheckoutClick()
                }
            )
        }

    }
}
@Composable
fun OrderSuccessDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onContinue: () -> Unit
) {
    if (!show) return

    Dialog(onDismissRequest = onDismiss) {

        val scale by animateFloatAsState(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )

        val alpha by animateFloatAsState(
            targetValue = 1f,
            animationSpec = tween(500)
        )

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFE8F5E9), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Order Successful!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your order has been placed successfully.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Continue Shopping")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartToolbar(
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Cart",
                maxLines = 1,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}
