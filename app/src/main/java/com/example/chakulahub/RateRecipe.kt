package com.example.chakulahub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RateRecipeScreen(navController: NavController) {
    var feedback by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.banana_crips), // Ensure this exists
            contentDescription = "Recipe Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Back Button
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
                    tint = Color(0xFFFFA500) // Orange color
                )
            }
        }

        // Rating & Feedback Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .height(350.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.70f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Rating Icon
                Icon(
                    painter = painterResource(id = R.drawable.meal), // Ensure this exists
                    contentDescription = "Rating Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = "Let Us Know How Your Overall Experience",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Feedback Input
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    placeholder = { Text(" ") }, // Empty placeholder to match image
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(16.dp), // Rounded corners
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray.copy(alpha = 0.5f),  // Darker background
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.5f),
                        disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
                        focusedIndicatorColor = Color.Transparent, // Remove bottom underline
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Save Button
                Button(
                    onClick = { /* Handle Save Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(45.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Save",
                        color = Color(0xFFFFA500),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// ✅ Preview Function
@Preview(showBackground = true)
@Composable
fun RateRecipeScreenPreview() {
    val navController = rememberNavController()
    RateRecipeScreen(navController)
}