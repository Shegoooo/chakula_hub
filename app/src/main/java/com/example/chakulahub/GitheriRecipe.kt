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
fun GitheriRecipeScreen(navController: NavController) {
    val recipeTitle = "Githeri"
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.githeri),
                        contentDescription = "Githeri",
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
                                text = "Githeri",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
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
                                    text = "30 min . by Holy Dave",
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
                                text = "Githeri is a wholesome Kenyan dish made from boiled maize and beans, often enhanced with sautéed onions, tomatoes, and spices. It’s a nutritious meal commonly enjoyed on its own or with vegetables and meat stew.",
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
                                        "• 2 cups dry maize, 1 cup dry beans, 1 onion, 2 tomatoes.\n" +
                                        "• 1 tbsp cooking oil, salt, black pepper, curry powder, dhania.",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Cooking Steps
                            Text(
                                text = "Cook & Prep\n" +
                                        "1. Soak maize and beans overnight, then boil until tender.\n" +
                                        "2. Sauté onions, add tomatoes and spices, cook until thick.\n" +
                                        "3. Add the boiled maize and beans, stir, and simmer for 10 mins.\n" +
                                        "4. Garnish with dhania and serve hot.",
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

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
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
fun GitheriRecipePreview() {
    val navController = rememberNavController()
    GitheriRecipeScreen(navController)
}