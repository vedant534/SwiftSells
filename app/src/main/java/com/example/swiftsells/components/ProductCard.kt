package com.example.swiftsells.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.swiftsells.R
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.ui.theme.BgGrey
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor

@Composable
fun ProductCard(productInCard: ProductDetails = ProductDetails("", 0.0, "N/A", "N/A", 0.0)) {
    val imageUrl = productInCard.image
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = R.drawable.splashlogo)
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        colors = CardDefaults.cardColors(containerColor = BgGrey)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Product Image
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Product Name
            Text(
                text = productInCard.productName,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Product Type
            Text(
                text = productInCard.productType,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(SecondaryColor)
                    .fillMaxWidth()
                    .padding(2.dp),
                color = MainBgColor
            )

            // Product Price and M.R.P
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "$",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
                Text(
                    text = productInCard.price.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(start = 4.dp),
                    color = Color.White
                )
                Text(
                    text = "M.R.P",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 4.dp),
                    color = Color.White
                )
            }

            // Product Tax
            Text(
                text = "(Tax : ${productInCard.tax}% )",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryColor,
                    contentColor = MainBgColor,
                ),
            ) {
                Text(text = "Buy")
            }
        }
    }
}