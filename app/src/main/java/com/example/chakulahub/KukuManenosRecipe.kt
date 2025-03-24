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
fun KukuManenosRecipeScreen(navController: NavController) {
    val recipeTitle = "Kuku Manenos"

    Column(modifier = Modifier.fillMaxSize()) {
        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Ensures the column takes available space without pushing the bottom bar away
                .fillMaxSize()
        ) {
            item {
                // Background Image with Back Button
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.kuku),
                        contentDescription = "Kuku Manenos",
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
                // Box to create overlap effect
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
                                text = "Kuku Manenos",
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
                                    text = "40 min . by Pika Chakula",
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
                                text = "Kuku Manenos is a flavorful, spicy grilled or fried chicken dish with a Kenyan twist, featuring a blend of rich spices and a crispy outer layer.",
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

                            // Ingredients
                            Text(
                                text = "Ingredients\n" +
                                        "• 1 whole chicken (cut into pieces)\n" +
                                        "• Garlic, ginger, lemon juice, salt, black pepper\n" +
                                        "• Paprika, cayenne pepper, coriander, turmeric\n" +
                                        "• Cooking oil or butter for frying/grilling",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Cooking Steps
                            Text(
                                text = "Cook & Prep\n" +
                                        "1. Marinate chicken with spices, garlic, ginger, and lemon juice.\n" +
                                        "2. Let it sit for at least 30 mins (best overnight).\n" +
                                        "3. Grill or deep fry until crispy and golden brown.\n" +
                                        "4. Serve with kachumbari and ugali.",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Action Buttons (Comment & Share)
                            ActionButtons(navController, recipeTitle)

                            Spacer(modifier = Modifier.height(16.dp))

                            // Save Button
                            SaveRecipeButton(navController)

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomNavBar(
                navController,
                currentRoute = "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun KukuManenosRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        KukuManenosRecipeScreen(navController = navController)
    }
}