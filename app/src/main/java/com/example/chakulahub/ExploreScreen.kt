package com.example.chakulahub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chakulahub.navigation.recipeRoutes

// Sample Data for Recipes
val trendingRecipes = listOf(
    Recipe(R.drawable.banana_crips, "Banana Crisps", "20 min", "Pika Chakula"),
    Recipe(R.drawable.mahamri, "Mahamri", "30 min", "Easy by M."),
    Recipe(R.drawable.chapati, "Chapati", "35 min", "HD Kitchen"),
    Recipe(R.drawable.samosa, "Swahili Samosa", "25 min", "Miss Mandi")
)

val comradeFriendlyMeals = listOf(
    Recipe(R.drawable.ugali, "Ugali na Sukuma", "15 min", "Mama Mboga"),
    Recipe(R.drawable.githeri, "Githeri", "30 min", "HD Kitchen"),
    Recipe(R.drawable.mukimo, "Mukimo", "40 min", "Tasty Bites"),
    Recipe(R.drawable.ndengu, "Pojo", "25 min", "Budget Bites")
)

val cheatMeals = listOf(
    Recipe(R.drawable.kuku, "Kuku Manenos", "45 min", "Ombachi"),
    Recipe(R.drawable.mandazi, "Mandazi", "30 min", "Miss Mandi"),
    Recipe(R.drawable.bhajia, "Bhajia", "25 min", "Chef Chevelo"),
    Recipe(R.drawable.masala_chips, "Masala Chips", "20 min", "Pika Chakula")
)

@Composable
fun ExploreScreen(navController: NavController, viewModel: RecipeViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredRecipes by viewModel.filteredRecipes.collectAsState()

    fun updateSearch(query: String) {
        searchQuery = query
        viewModel.searchRecipe(query)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.joseph),
                        contentDescription = "Flatbread",
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
                        .fillMaxWidth()
                        .offset(y = (-45).dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Explore",
                                textAlign = TextAlign.Center,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { updateSearch(it) },
                                label = { Text("Search for recipes", color = Color(0xFFFFA500)) },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = "Search",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                textStyle = TextStyle(color = Color.Black), // Ensures user input is black
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Gray,
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedLabelColor = Color(0xFFFFA500),
                                    unfocusedLabelColor = Color(0xFFFFA500),
                                    cursorColor = Color.Black, // Ensures the cursor is black
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                )
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "Popular Tags",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    listOf("Chai", "Mukimo", "Ugali", "Mandazi", "Kuku Manenos", "Samosa")
                                        .forEach { tag ->
                                            Text(
                                                text = tag,
                                                fontSize = 14.sp,
                                                modifier = Modifier
                                                    .background(Color.LightGray, RoundedCornerShape(16.dp))
                                                    .padding(horizontal = 15.dp, vertical = 10.dp),
                                                color = Color.Black
                                            )
                                        }
                                }

                                Spacer(modifier = Modifier.height(20.dp))
                                RecipeSection("Trending Recipes", trendingRecipes, navController)
                                RecipeSection("Comrade Friendly Meals", comradeFriendlyMeals, navController)
                                RecipeSection("Cheat Meals", cheatMeals, navController)
                            } else {
                                if (filteredRecipes.isEmpty()) {
                                    Text(
                                        text = "No recipes found.",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                } else {
                                    Column {
                                        filteredRecipes.forEach { recipe ->
                                            SearchResultCard(
                                                title = recipe.title,
                                                imageId = recipe.imageId,
                                                duration = recipe.duration,
                                                author = recipe.author,
                                                route = recipeRoutes[recipe.title] ?: "",
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar fixed at the bottom of the screen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomNavBar(navController, currentRoute = "explore-screen", Modifier.fillMaxWidth())
        }
    }
}


@Composable
fun SearchResultCard(
    title: String,
    imageId: Int,
    duration: String,
    author: String,
    route: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(route) }
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)) // Light gray color
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Recipe Image
            Image(
                painter = painterResource(id = imageId),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Recipe Details
            Column {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock), // Replace with your clock icon resource
                        contentDescription = "Clock",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(4.dp)) // Adds spacing between the icon and the text

                    Text(
                        text = "$duration | by $author",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.Font)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeSection(title: String, recipes: List<Recipe>, navController: NavController) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(10.dp))

    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        items(recipes) { recipe ->
            RecipeCard(recipe, navController)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    val navController = rememberNavController()
    val viewModel: RecipeViewModel = viewModel()
    ExploreScreen(navController, viewModel)
}