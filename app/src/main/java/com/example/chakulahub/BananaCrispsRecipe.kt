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
fun BananaCrispsRecipeScreen(navController: NavController) {
    val recipeTitle = "Banana Crisps"

    Box(modifier = Modifier.fillMaxSize()) {
        // Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Background Image with Back Button
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.banana_crips),
                        contentDescription = "Banana Crisps",
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
                        .padding(bottom = 70.dp) // Prevents overlap with BottomNavBar
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

                            // Recipe Title
                            Text(
                                text = "Banana Crisps",
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
                                    text = "15 min . by Chef Kendi",
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

                            // Recipe Description
                            Text(
                                text = "Banana crisps are a delicious, crispy snack made from thinly sliced bananas, deep-fried until golden, and seasoned for extra flavor.",
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // Section Title: How to Cook It
                            Text(
                                text = "How to Cook It",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Ingredients List
                            Text(
                                text = "Ingredients\n" +
                                        "• Raw bananas (peeled & thinly sliced), salt, oil for frying.\n" +
                                        "• Optional: Chili powder, black pepper, or sugar for seasoning.",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Cooking Steps
                            Text(
                                text = "Cook & Prep\n" +
                                        "1. Peel and slice bananas into thin rounds.\n" +
                                        "2. Heat oil in a deep pan to medium-high.\n" +
                                        "3. Fry banana slices until golden and crispy.\n" +
                                        "4. Remove and drain on paper towels.\n" +
                                        "5. Sprinkle salt or seasoning of choice.\n" +
                                        "6. Serve and enjoy!",
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomNavBar(
                navController = navController,
                currentRoute = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // ✅ Works inside BoxScope
            )
        }
    }
}


// Preview Function
@Preview(showBackground = true)
@Composable
fun BananaCrispsRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        BananaCrispsRecipeScreen(navController = navController)
    }
}
