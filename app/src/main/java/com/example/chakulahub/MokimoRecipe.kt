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
fun MokimoRecipeScreen(navController: NavController) {
    val recipeTitle = "Mukimo"

    Column(modifier = Modifier.fillMaxSize()) {
        // Scrollable Content
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    // Background Image with Back Button
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.mukimo),
                            contentDescription = "Mukimo",
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
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = recipeTitle,
                                    textAlign = TextAlign.Center,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(8.dp))

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
                                        text = "40 mins • by Holy Dave",
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

                                Text(
                                    text = "Mukimo is a traditional Kenyan dish made by mashing potatoes with maize, peas, and greens. It is often served with beef stew or any side of your choice.",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(14.dp))

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
                                                "• 5 large potatoes – Provides the base for the dish.\n" +
                                                "• 1 cup boiled maize – Adds texture and flavor.\n" +
                                                "• 1 cup green peas – Enhances the nutritional value.\n" +
                                                "• 1 bunch pumpkin leaves/spinach – Gives color and extra nutrients.\n" +
                                                "• 1 onion, chopped – For added flavor.\n" +
                                                "• 2 tbsp butter or margarine – To enrich the taste.\n" +
                                                "• Salt to taste – Balances flavor.",
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

                                    Text("1. Boil the potatoes until soft.", fontSize = 15.sp, color = Color.Black)
                                    Text("2. In a separate pot, boil the maize and peas until tender.", fontSize = 15.sp, color = Color.Black)
                                    Text("3. Sauté the chopped onions until golden brown.", fontSize = 15.sp, color = Color.Black)
                                    Text("4. Add pumpkin leaves/spinach and cook until wilted.", fontSize = 15.sp, color = Color.Black)
                                    Text("5. Mash the potatoes together with the maize, peas, and cooked greens.", fontSize = 15.sp, color = Color.Black)
                                    Text("6. Stir in butter and season with salt to taste.", fontSize = 15.sp, color = Color.Black)
                                    Text("7. Serve hot with beef stew, chicken, or any side of your choice!", fontSize = 15.sp, color = Color.Black)
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
}

@Preview(showBackground = true)
@Composable
fun MokimoRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme { MokimoRecipeScreen(navController) }
}
