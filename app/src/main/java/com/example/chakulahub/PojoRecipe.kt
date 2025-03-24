package com.example.chakulahub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun PojoRecipeScreen(navController: NavController) {
    val recipeTitle = "Pojo" // Define recipe title

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Background Image with Back Button
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ndengu),
                        contentDescription = "Pojo",
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
                                text = recipeTitle,
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
                                    text = "20 mins • by Miss Mandi",
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
                                    text = "Serves 2",
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.Font)
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Description
                            Text(
                                text = "Pojo (green grams) is a nutritious Kenyan dish enjoyed as a stew, often served with rice or chapati.",
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
                                            "• 1 cup pojo (green grams) – Base of the dish, rich in protein.\n" +
                                            "• 1 onion, chopped – Adds flavor and aroma.\n" +
                                            "• 2 tomatoes, chopped – Enhances taste and texture.\n" +
                                            "• 1 tsp cumin – Provides a warm, earthy flavor.\n" +
                                            "• 1 tsp salt – Balances taste.\n" +
                                            "• 2 cups water – Needed for boiling and simmering.",
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

                                Text("1. Boil pojo in water until tender.", fontSize = 15.sp, color = Color.Black)
                                Text("2. Sauté onions until golden brown.", fontSize = 15.sp, color = Color.Black)
                                Text("3. Add tomatoes and cook until soft.", fontSize = 15.sp, color = Color.Black)
                                Text("4. Stir in cumin and salt, then add cooked pojo.", fontSize = 15.sp, color = Color.Black)
                                Text("5. Simmer for 10 minutes, stirring occasionally.", fontSize = 15.sp, color = Color.Black)
                                Text("6. Serve hot with rice or chapati.", fontSize = 15.sp, color = Color.Black)
                            }

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
fun PojoRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme { PojoRecipeScreen(navController) }
}
