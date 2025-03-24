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
fun ChaiRecipeScreen(navController: NavController) {
    val recipeTitle = "Chai (Kenyan Tea)" // Recipe Title

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                // Background Image with Back Button
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.tea),
                        contentDescription = "Chai",
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
                // Card for Recipe Details
                Box(modifier = Modifier.fillMaxSize().offset(y = (-45).dp)) {
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
                                    text = "10 min • by Miss Mandi",
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
                                text = "Chai is a classic Kenyan tea brewed with milk, tea leaves, and optional spices like ginger or cardamom. It's best enjoyed with mandazi, mahamri, or any snack of your choice!",
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // How to Make It
                            Text(
                                text = "How to make it",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Ingredients\n" +
                                            "• 2 cups water - Base for brewing tea leaves.\n" +
                                            "• 1 cup milk - Adds creaminess.\n" +
                                            "• 2 tsp tea leaves - Black tea for flavor.\n" +
                                            "• 2 tbsp sugar - Adjust to taste.\n" +
                                            "• 1/2 tsp ginger (optional) - For a spicy kick.\n" +
                                            "• 1/2 tsp cardamom (optional) - Adds aroma.",
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text("Preparation Steps:", fontSize = 18.sp, color = Color.Black)
                                Spacer(modifier = Modifier.height(8.dp))

                                Text("1. Boil water in a saucepan.", fontSize = 15.sp, color = Color.Black)
                                Text("2. Add tea leaves and let it simmer for 3 minutes.", fontSize = 15.sp, color = Color.Black)
                                Text("3. Stir in milk and sugar, then bring to a boil.", fontSize = 15.sp, color = Color.Black)
                                Text("4. Add ginger and/or cardamom for extra flavor.", fontSize = 15.sp, color = Color.Black)
                                Text("5. Let it simmer for another 2 minutes.", fontSize = 15.sp, color = Color.Black)
                                Text("6. Strain and serve hot with mandazi, mahamri, or your favorite snack!", fontSize = 15.sp, color = Color.Black)
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
fun ChaiRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme { ChaiRecipeScreen(navController) }
}
