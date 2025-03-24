package com.example.chakulahub

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun SwahiliSamosaRecipeScreen(navController: NavController) {
    val recipeTitle = "Swahili Samosa"

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f) // Allows scrolling and pushes the navbar down
            ) {
                item {
                    // Background Image with Back Button
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.samosa),
                            contentDescription = "Swahili Samosas",
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
                    // Recipe Details Card
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
                                        text = "35 min . by Miss Mandi",
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
                                        text = "Serves 15",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.Font)
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Description
                                Text(
                                    text = "Swahili samosas (sambusa) are a crispy, spiced snack popular in East Africa, influenced by Indian and Middle Eastern cuisine in fillings like meat, veggies, or lentils.",
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
                                            "• Ground beef (455g), onion, garlic, chili, spices, peas, dhania.\n" +
                                            "• Wrapper: Flour, salt, water, cooking oil, lime.",
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                // Cooking Steps
                                Text(
                                    text = "Cook & Prep\n" +
                                            "1. Cook beef with onion, garlic, spices. Add peas & dhania.\n" +
                                            "2. Make dough, rest for 30 mins. Roll into circles, cook briefly.\n" +
                                            "3. Seal dough with flour paste, fill with meat.\n" +
                                            "4. Fry at 350°F for 5 mins, drain, serve with lime.",
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
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BottomNavBar(
                    navController, currentRoute = "", Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ActionButtons(navController: NavController, recipeTitle: String) {
    var showShareDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current  // Get the context once here

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconSize = 24.dp

        // Leave a Comment Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { navController.navigate("rate-recipe-screen") }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Leave a Comment",
                tint = Color.Black,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Leave a Comment",
                color = colorResource(id = R.color.Font),
                fontSize = 14.sp
            )
        }

        // Share Recipe Button (Opens Share Dialog)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { showShareDialog = true }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = "Share Recipe",
                tint = Color.Black,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Share Recipe",
                color = colorResource(id = R.color.Font),
                fontSize = 14.sp
            )
        }
    }

    // Share Dialog
    if (showShareDialog) {
        AlertDialog(
            onDismissRequest = { showShareDialog = false },
            title = { Text(text = "Share Recipe") },
            text = {
                Column {
                    Text(text = "Share '$recipeTitle' with your friends:")
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ShareIcon(R.drawable.whatsapp, "WhatsApp") {
                            shareRecipe(context, "https://www.whatsapp.com/send?text=Check out this amazing recipe: $recipeTitle")
                        }
                        ShareIcon(R.drawable.instagram, "Instagram") {
                            shareRecipe(context, "https://www.instagram.com/")
                        }
                        ShareIcon(R.drawable.tiktok, "TikTok") {
                            shareRecipe(context, "https://www.tiktok.com/")
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showShareDialog = false }) {
                    Text(
                        text = "Close",
                        color = colorResource(id = R.color.Font) // Set the text color
                    )
                }
            }
        )
    }
}

@Composable
fun ShareIcon(iconRes: Int, platformName: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = platformName,
            modifier = Modifier.size(40.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = platformName, fontSize = 12.sp, color = Color.Black)
    }
}

fun shareRecipe(context: Context, shareText: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

@Composable
fun SaveRecipeButton(navController: NavController) {
    Button(
        onClick = {  navController.navigate(route = "saved-recipe-screen")},
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(50.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = "Save",
            color = colorResource(id = R.color.Font),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SwahiliSamosaRecipeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        SwahiliSamosaRecipeScreen(navController = navController)
    }
}