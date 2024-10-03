package com.example.storeapp.features.product.presentation.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.storeapp.features.product.domain.model.Product
import com.example.storeapp.utils.truncateString

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(270.dp)
            .clickable {

            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults
            .cardElevation(
                defaultElevation = 2.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = truncateString(product.title, 20),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rate",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .height(20.dp)
                        .padding(0.dp)
                )
                Text(
                    text = "${product.rating.rate}"
                )
                Text(
                    text = " | "
                )
                Text(
                    text = "${product.rating.count} Sold"
                )
            }
        }
    }
}
