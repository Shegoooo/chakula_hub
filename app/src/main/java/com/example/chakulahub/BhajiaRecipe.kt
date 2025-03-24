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
fun BhajiaRecipeScreen(navController: NavController) {
    val recipeTitle = "Crispy Bhajia"

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    // Background Image with Back Button
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.bhajia),
                            contentDescription = "Bhajia",
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
                                        text = "30 min . by Chef Ali",
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
                                Text(
                                    text = "Bhajia are crispy, spiced potato fritters popular in East Africa, usually served with chutney or a hot drink.",
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
                                // Ingredients
                                Text(
                                    text = "Ingredients\n" +
                                            "• Potatoes (4, thinly sliced), gram flour (1 cup), turmeric (1 tsp), cumin (1 tsp), chili powder (1/2 tsp), salt, water, cooking oil.",
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(10.dp))

// Cooking Steps
                                Text(
                                    text = "Cook & Prep\n" +
                                            "1. Mix flour, spices, salt, and water into a batter.\n" +
                                            "2. Coat potato slices in batter.\n" +
                                            "3. Deep fry until golden brown.\n" +
                                            "4. Drain and serve with chutney.",
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                // Action Buttons (Comment & Share)
                                ActionButtons(navController, recipeTitle)
                                Spacer(modifier = Modifier.height(16.dp))
                                SaveRecipeButton(navController)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
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
                navController = navController,
                currentRoute = "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BhajiaRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        BhajiaRecipeScreen(navController = navController)
    }
}
