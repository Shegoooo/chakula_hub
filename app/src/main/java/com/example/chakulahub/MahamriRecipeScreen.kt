package com.example.chakulahub

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MahamriRecipeScreen(navController: NavController) {
    val recipeTitle = "Mahamri"

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(1f) // Allows scrolling and pushes navbar down
            ) {
                item {
                    // Background Image with Back Button
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.mahamri),
                            contentDescription = "Mahamri",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )

                        // Back Button
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(16.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                .size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.back_button),
                                contentDescription = "Back",
                                tint = colorResource(id = R.color.Font)
                            )
                        }
                    }
                }

                item {
                    // Overlapping Card for Recipe Details
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = (-45).dp)
                    ) {
                        Card(
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)),
                            elevation = CardDefaults.elevatedCardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(8.dp))

                                // Title
                                Text(
                                    text = "Mahamri",
                                    textAlign = TextAlign.Center,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Time and Servings
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.clock),
                                        contentDescription = "Time",
                                        tint = Color.Black,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "35 min • by Pika Chakula",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.Font)
                                    )

                                    Icon(
                                        painter = painterResource(id = R.drawable.hot_food),
                                        contentDescription = "Servings",
                                        tint = Color.Black,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "Serves 4",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.Font)
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Description
                                Text(
                                    text = "Mahamri are soft, fluffy, and slightly sweet Swahili-style fried bread, commonly enjoyed for breakfast with tea.",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                // How to Cook it
                                Text(
                                    text = "How to cook it",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Ingredients\n" +
                                                "• Flour, yeast, sugar – Forms the dough base and adds sweetness.\n" +
                                                "• Coconut milk, cardamom – Enhances flavor and aroma.\n" +
                                                "• Warm water – Activates the yeast for soft, fluffy mahamri.\n" +
                                                "• Oil – For deep frying until golden brown.",
                                        fontSize = 15.sp,
                                        color = Color.Black
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text("Cook & Prep:", fontSize = 18.sp, color = Color.Black)
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("1. Mix flour, yeast, sugar, cardamom, and coconut milk.", fontSize = 15.sp, color = Color.Black)
                                    Text("2. Gradually add warm water and knead into a soft dough.", fontSize = 15.sp, color = Color.Black)
                                    Text("3. Cover and let rise for 1 hour until doubled in size.", fontSize = 15.sp, color = Color.Black)
                                    Text("4. Roll out the dough and cut into triangular shapes.", fontSize = 15.sp, color = Color.Black)
                                    Text("5. Deep fry in hot oil until golden brown.", fontSize = 15.sp, color = Color.Black)
                                    Text("6. Drain and serve warm with tea.", fontSize = 15.sp, color = Color.Black)
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Action Buttons (Comment & Share)
                                ActionButtons(navController, recipeTitle)

                                Spacer(modifier = Modifier.height(16.dp))

                                // Save Button
                                SaveRecipeButton(navController)
                            }
                        }
                    }
                }
            }
        }

        // Sticky Bottom Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White) // Ensure visibility
                .padding(bottom = 8.dp) // Comfortable spacing
        ) {
            BottomNavBar(
                navController, currentRoute = "", Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MahamriRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        MahamriRecipeScreen(navController = navController)
    }
}