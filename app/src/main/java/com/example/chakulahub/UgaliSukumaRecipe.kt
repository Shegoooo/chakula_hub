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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UgaliSukumaRecipeScreen(navController: NavController) {
    val recipeTitle = "Ugali na Sukuma"
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ugali),
                        contentDescription = "Ugali Sukuma",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )

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
                            // Title
                            Text(
                                text = "Ugali Sukuma",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

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
                                    text = "20 min . by Dennis Ombachi",
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
                                text = "Ugali Sukuma is a staple Kenyan meal consisting of firm maize porridge (ugali) served with sautéed collard greens (sukuma wiki). This affordable and nutritious dish is a daily favorite across many households.",
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
                                        "• Ugali: 2 cups maize flour, 4 cups water, pinch of salt.\n" +
                                        "• Sukuma Wiki: 1 bunch collard greens, 1 onion, 2 tomatoes.\n" +
                                        "• Cooking oil, salt, black pepper, garlic (optional).",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Cooking Steps
                            Text(
                                text = "Cook & Prep\n" +
                                        "1. Boil water in a pot, gradually add maize flour while stirring.\n" +
                                        "2. Stir continuously until thick, cook for 10 mins, then shape.\n" +
                                        "3. Sauté onions in oil, add tomatoes and cook until soft.\n" +
                                        "4. Add chopped sukuma wiki, season, and stir-fry for 5 mins.\n" +
                                        "5. Serve hot with ugali and enjoy.",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Action Buttons (Comment & Share)
                            ActionButtons(navController, recipeTitle)

                            Spacer(modifier = Modifier.height(16.dp))

                            // Save Button
                            SaveRecipeButton(navController)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // ✅ Now works correctly
        ) {
            BottomNavBar(
                navController, currentRoute = "", Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UgaliSukumaRecipePreview() {
    val navController = rememberNavController()
    UgaliSukumaRecipeScreen(navController)
}
