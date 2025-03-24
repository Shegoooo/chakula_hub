package com.example.chakulahub

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.chakulahub.navigation.recipeRoutes
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController) {
    val recipeViewModel: RecipeViewModel = viewModel()
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(35.dp))
            ProfileSection(navController)
            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar (Always Visible)
            RecipeSearchScreen(
                searchQuery = searchQuery,
                onSearchQueryChanged = { query ->
                    searchQuery = query
                    recipeViewModel.searchRecipe(query)
                },
                viewModel = recipeViewModel,
                navController = navController
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp), // Ensures space for bottom navigation
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    SectionTitle("Popular Recipes")
                    Spacer(modifier = Modifier.height(10.dp))

                    RecipeCard(
                        imageId = R.drawable.shakshuka,
                        title = "Shakshouka",
                        details = "35 min • Easy • by Miss Mandi",
                        isSelected = false,
                        onClick = { navController.navigate("shakshouka-screen") }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    SectionTitle("Recommended Recipes")
                    Spacer(modifier = Modifier.height(10.dp))

                    RecipeCardSmall(
                        imageId = R.drawable.masala_chips,
                        title = "Masala Fries",
                        details = "25 min • Easy • by Miss Mandi",
                        isSelected = false,
                        onClick = { navController.navigate("masala-screen") },
                        titleColor = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    RecipeCardSmall(
                        imageId = R.drawable.samosa,
                        title = "Swahili Samosas",
                        details = "25 min • Medium • by Pika Chakula",
                        isSelected = false,
                        onClick = { navController.navigate("recipe-screen") },
                        titleColor = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    RecipeCardSmall(
                        imageId = R.drawable.mukimo,
                        title = "Mukimo",
                        titleColor = Color.Black,
                        details = "25 min • Medium • by Chef Wambui",
                        isSelected = false,
                        onClick = { navController.navigate("mukimo-screen") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    RecipeCardSmall(
                        imageId = R.drawable.mandazi,
                        title = "Mandazi",
                        details = "25 min • Medium • by Chef Wambui",
                        isSelected = false,
                        onClick = { navController.navigate("mandazi-screen") },
                        titleColor = Color.Black
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        // Bottom Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            BottomNavBar(
                navController, currentRoute = "home-screen", Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun ProfileSection(navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .clickable { navController.navigate("profile-screen") }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text("Hello, Grace M.", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("Let’s Get Cooking!", fontSize = 14.sp,  color = colorResource(id = R.color.Font))
        }
    }
}

//search bar
@Composable
fun RecipeSearchScreen(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    viewModel: RecipeViewModel,
    navController: NavController
) {
    val recipes by viewModel.filteredRecipes.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Search Input Field
        TextField(
            value = searchQuery,
            onValueChange = {
                onSearchQueryChanged(it)
                viewModel.searchRecipe(it)
            },
            placeholder = { Text("Search for recipes", color = colorResource(id = R.color.Font)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White, RoundedCornerShape(20.dp)) // Ensures background has rounded edges
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)) // Rounded border
                .padding(horizontal = 12.dp, vertical = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(20.dp), // Ensures internal text field also has rounded corners
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isNotEmpty()) {
            if (recipes.isEmpty()) {
                Text(
                    text = "No recipes found",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(recipes) { recipe ->
                        RecipeCard(
                            title = recipe.title,
                            imageId = recipe.imageId,
                            details = recipe.duration,
                            author = recipe.author,
                            isSelected = false,
                            onClick = {
                                val route = recipeRoutes[recipe.title] ?: "defaultRoute"
                                navController.navigate(route)
                            }
                        )
                    }
                }
            }
        }
    }
}


data class RecipeItem(
    val title: String,
    val imageId: Int,
    val duration: String,
    val category: String,
    val author: String
)

class RecipeViewModel : ViewModel() {
    private val _allRecipes = MutableStateFlow<List<RecipeItem>>(emptyList())
    private val _filteredRecipes = MutableStateFlow<List<RecipeItem>>(emptyList())

    val filteredRecipes: StateFlow<List<RecipeItem>> = _filteredRecipes

    init {
        viewModelScope.launch {
            loadRecipes()
        }
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            try {
                val fetchedRecipes = getRecipeList()
                Log.d("RecipeViewModel", "✅ Recipes loaded: ${fetchedRecipes.size}") // Debug log

                _allRecipes.value = fetchedRecipes
                _filteredRecipes.value = fetchedRecipes // Ensure initial list is set
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "❌ Error fetching recipes: ${e.message}")
            }
        }
    }

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            if (_allRecipes.value.isEmpty()) {
                Log.d("RecipeViewModel", "⚠️ No recipes available yet.")
                return@launch
            }

            Log.d("RecipeViewModel", "🔍 Searching for: '$query'") // Debug query

            val filteredList = _allRecipes.value.filter {
                it.title.contains(query, ignoreCase = true)
            }

            _filteredRecipes.emit(filteredList) // Properly update StateFlow

            Log.d("RecipeViewModel", "📌 Filtered recipes count: ${filteredList.size}") // Debug result
        }
    }

    private fun getRecipeList(): List<RecipeItem> {
        val list = listOf(
            RecipeItem("Banana Crisps", R.drawable.banana_crips, "15 mins", "Snacks", "Chef Kendi"),
            RecipeItem("Bhajia", R.drawable.bhajia, "30 mins", "Snacks", "by Chef Ali"),
            RecipeItem("Chai", R.drawable.tea, "10 mins", "Beverages", "Miss Mandi"),
            RecipeItem("Chapati", R.drawable.chapati, "60 mins", "Main Dish", "Pika Chakula"),
            RecipeItem("Githeri", R.drawable.githeri, "30 mins", "Main Dish", "Holy Dave"),
            RecipeItem("Kuku Manenos", R.drawable.kuku, "40 mins", "Main Dish", "Pika Chakula"),
            RecipeItem("Mahamri", R.drawable.mahamri, "35 mins", "Snacks", "Pika Chakula"),
            RecipeItem("Mandazi", R.drawable.mandazi, "30 mins", "Snacks", "Pika Chakula"),
            RecipeItem("Masala Chips", R.drawable.masala_chips, "20 mins", "Snacks", "Miss Mandi"),
            RecipeItem("Mukimo", R.drawable.mukimo, "40 mins", "Main Dish", "Holy Dave"),
            RecipeItem("Pojo", R.drawable.ndengu, "20 mins", "Main Dish", "Miss Mandi"),
            RecipeItem("Shakshouka", R.drawable.shakshuka, "25 mins", "Breakfast", "Pika Chakula"),
            RecipeItem("Swahili Samosa", R.drawable.samosa, "35 mins", "Snacks", "Miss Mandi"),
            RecipeItem("Ugali na Sukuma", R.drawable.ugali, "20 mins", "Main Dish", "Dennis Ombachi")
        )


        Log.d("RecipeViewModel", "🍽️ Initial recipe list size: ${list.size}") // Debug initial list
        return list
    }
}

@Composable
fun RecipeCard(title: String, imageId: Int, details: String, author: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        // White background overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomStart)
                .background(Color.White)
        )

        // Text content inside the overlay
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.clock), // Replace with your clock icon
                    contentDescription = "Time",
                    tint = Color.Black,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = details,
                    color = colorResource(id = R.color.Font),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add space before author name

                Text(
                    text = "• $author", // Adding author name with a middle dot separator
                    color = colorResource(id = R.color.Font),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


// ✅ Recipe Card for the popular recipes
@Composable
fun RecipeCard(
    title: String,
    imageId: Int,
    details: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    imageHeight: Dp = 140.dp  // Adjusted image height for correct proportions
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)  // Adjusted height to match UI
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // No shadow
    ) {
        Column {
            // Recipe Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // White space containing title and details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                // Title Text
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Row for Time Icon + Details
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Time",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = details,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.Font)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeCardSmall(
    imageId: Int,
    title: String,
    details: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    cardHeight: Dp = 80.dp, // Adjusted height
    imageSize: Dp = 55.dp,  // Adjusted image size
    spacing: Dp = 4.dp, // Reduced spacing for compact layout
    titleColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickable { onClick() }
            .padding(vertical = spacing),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Light gray background
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // No shadow
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp), // Adjusted padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Recipe Image
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Recipe Details (Title + Time Icon + Details)
            Column {
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(2.dp)) // Reduced spacing

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Time",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp) // Reduced icon size
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = details,
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.Font)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String, modifier: Modifier) {
    val items = listOf(
        BottomNavItem("Home", R.drawable.home, "home-screen"),
        BottomNavItem("Explore", R.drawable.explore, "explore-screen"),
        BottomNavItem("Saved", R.drawable.bookmark, "saved-recipe-screen"),
        BottomNavItem("Profile", R.drawable.profile_user, "profile-screen")
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp) // Slightly taller for better aesthetics
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color(0xFFE0E0E0)) // Warm background (fits a recipe app)
            .shadow(8.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomNavIcon(
                    navController = navController,
                    icon = item.icon,
                    label = item.label,
                    route = item.route,
                    isSelected = currentRoute == item.route
                )
            }
        }
    }
}

@Composable
fun BottomNavIcon(navController: NavController, icon: Int, label: String, route: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                if (navController.currentDestination?.route != route) {
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    if (isSelected) Color(0xFFFFA726).copy(alpha = 0.2f) else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(if (isSelected) 26.dp else 24.dp),
                colorFilter = if (isSelected) ColorFilter.tint(Color(0xFFFFA726)) else null
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color(0xFFFFA726) else Color.Gray
        )
    }
}

data class BottomNavItem(val label: String, val icon: Int, val route: String)


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}