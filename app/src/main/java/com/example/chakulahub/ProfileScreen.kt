package com.example.chakulahub

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun ProfileScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.butter_chicken), // Replace with actual background image
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Back Button (Top Left)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
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

        // Profile Card (Centered)
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f)
                .height(600.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title
                Text(
                    text = "Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Profile Picture
                Image(
                    painter = painterResource(id = R.drawable.profile), // Replace with actual profile image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // User Name
                Text(
                    text = "Grace Mwingirwa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(15.dp))

                // User Description
                Text(
                    text = "A girl who loves exploring",
                    fontSize = 13.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(15.dp))

                // Edit Button
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate("edit-screen") }
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_text),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Edit", color = Color.Black, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(25.dp))

                // Info Cards (Clickable)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileInfoCard("Saved Recipes", R.drawable.service, navController, "saved-recipe-screen")
                    ProfileInfoCard("Change Password", R.drawable.lock, navController, "change-pwd-screen")
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Logout & Reset Password Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Logout Button
                    Button(
                        onClick = { navController.navigate("logout-page") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "Log Out",
                            color = colorResource(id = R.color.Font),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Reset Password Button
                    Button(
                        onClick = { navController.navigate("home-screen") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "Go Home",
                            color = Color(0xFFFFA500), // Orange text
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/* 📌 Clickable Profile Info Card */
@Composable
fun ProfileInfoCard(text: String, iconRes: Int, navController: NavController, destination: String) {
    Card(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .clickable { navController.navigate(destination) }, // ✅ Clickable for navigation
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text, fontSize = 12.sp, color = colorResource(id = R.color.Font), textAlign = TextAlign.Center)
        }
    }
}

/* 📌 Preview */
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}
