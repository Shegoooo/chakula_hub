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
fun ChapatiRecipeScreen(navController: NavController) {
    val recipeTitle = "Chapati" // Define recipe title

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Background Image with Back Button
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.chapati),
                        contentDescription = "Chapati",
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
                                    text = "1 hr • by Pika Chakula",
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
                                text = "Chapati is a popular Kenyan flatbread that pairs well with ndengu, beef stew, or any dish of your choice.",
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
                                            "• 3 cups all-purpose flour – The base of chapati.\n" +
                                            "• 1 cup warm water – Helps form a soft dough.\n" +
                                            "• 2 tbsp vegetable oil – Adds softness.\n" +
                                            "• 1 tsp salt – Enhances flavor.\n" +
                                            "• Extra flour – For rolling the dough.",
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

                                Text("1. Mix flour, salt, and oil in a bowl.", fontSize = 15.sp, color = Color.Black)
                                Text("2. Slowly add warm water and knead into a soft dough.", fontSize = 15.sp, color = Color.Black)
                                Text("3. Cover and let rest for 30 minutes.", fontSize = 15.sp, color = Color.Black)
                                Text("4. Divide dough into balls and roll out into circles.", fontSize = 15.sp, color = Color.Black)
                                Text("5. Cook each chapati on a hot pan, flipping until golden brown.", fontSize = 15.sp, color = Color.Black)
                                Text("6. Serve warm with ndengu, beef stew, or any dish you like!", fontSize = 15.sp, color = Color.Black)
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
fun ChapatiRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme { ChapatiRecipeScreen(navController) }
}