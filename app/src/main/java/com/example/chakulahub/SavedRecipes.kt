package com.example.chakulahub

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.draw.clip
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
import com.example.chakulahub.navigation.recipeRoutes


// ✅ Recipe Data Class
data class Recipe(val imageRes: Int, val title: String, val duration: String, val author: String)

// ✅ Sample saved recipes
val savedRecipes = listOf(
    Recipe(R.drawable.shakshuka, "Shakshouka", "20 min", "Miss Mandi"),
    Recipe(R.drawable.samosa, "Swahili Samosa", "30 min", "Pika Chakula"),
    Recipe(R.drawable.masala_chips, "Masala Chips", "20 min", "Miss Mandi"),
)

// ✅ Saved Recipes Screen
@Composable
fun SavedRecipesScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Ensure background consistency
    ) {
        // Top Image with Back Button
        TopImageWithBackButton(
            navController,
            R.drawable.aloo_paratha,
            "Saved Recipes"
        )

        // Main Content (Card Overlapping Image)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 160.dp, bottom = 60.dp), // Prevents overlap with navbar
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .offset(y = (20).dp),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Top, // Align content to the top
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Saved Recipes",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, bottom = 20.dp)
                    )

                    // Recipe Grid
                    RecipeGrid(recipes = savedRecipes, navController)
                }
            }
        }

        // Bottom Navigation Bar fixed at the bottom
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter // Ensures child is at bottom center
        ) {
            BottomNavBar(
                navController,
                currentRoute = "saved-recipe-screen",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


// ✅ Recipe Grid
@Composable
fun RecipeGrid(recipes: List<Recipe>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipes) { recipe ->
            RecipeCard(recipe = recipe, navController = navController)
        }
    }
}

// ✅ Recipe Card Component
@Composable
fun RecipeCard(recipe: Recipe, navController: NavController) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp) // Increased height
            .clickable {
                val route = recipeRoutes[recipe.title]
                if (route != null) {
                    navController.navigate(route)
                } else {
                    Log.e("NavigationError", "No route found for ${recipe.title}")
                }
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = recipe.imageRes),
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp), // Slightly increased image height
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Row for Clock Icon and Duration
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Duration",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = recipe.duration,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.Font)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "by ${recipe.author}",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.Font)
                )
            }
        }
    }
}


// ✅ Top Image with Back Button
@Composable
fun TopImageWithBackButton(
    navController: NavController,
    imageRes: Int,
    contentDescription: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back",
                tint = colorResource(id = R.color.Font)
            )
        }
    }
}

// ✅ Preview with Theme
@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        SavedRecipesScreen(navController)
    }
}
