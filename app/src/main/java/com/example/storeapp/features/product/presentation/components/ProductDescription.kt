package com.example.storeapp.features.product.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeapp.features.product.domain.model.Product

@Composable
fun ProductDescription(
    product: Product?
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp
            )
    ) {
        Text(
            text = "${product?.title}",
            modifier = Modifier
                .padding(
                    bottom = 10.dp
                ),
            style = MaterialTheme.typography.bodyMedium
                .copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
        )
        Text(
            text = "Category : ${product?.category}"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$${product?.price}",
                color = Color.Blue
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = "${product?.rating?.count} Sold"
            )
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
            )
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        horizontal = 5.dp,
                        vertical = 4.dp
                    )
            ) {
                Row {

                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rate",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .height(20.dp)
                            .padding(0.dp)
                    )
                    Text(
                        text = "${product?.rating?.rate}"
                    )
                }

            }

        }
        Text(text = "${product?.description}")
    }
}