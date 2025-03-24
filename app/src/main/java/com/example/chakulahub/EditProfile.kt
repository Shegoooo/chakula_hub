package com.example.chakulahub

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EditProfileScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            val uri = MediaStore.Images.Media.insertImage(
                context.contentResolver, it, "Profile Picture", null
            )
            profileImageUri = Uri.parse(uri)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.palm_fruit_), // Ensure this drawable exists
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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

        // Profile Edit Card (Centered)
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f) // 90% of screen width
                .height(550.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(35.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Profile Picture with Edit Icon
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.edit_text),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.White, shape = CircleShape)
                            .padding(4.dp)
                            .clickable {
                                imagePickerLauncher.launch("image/*")
                            }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Name Input
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // About Input
                OutlinedTextField(
                    value = about,
                    onValueChange = { about = it },
                    label = { Text("About") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Save Button
                Button(
                    onClick = { /* Handle save action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
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

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    EditProfileScreen(navController)
}