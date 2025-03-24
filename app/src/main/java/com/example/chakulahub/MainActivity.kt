package com.example.chakulahub

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.chakulahub.ui.theme.ChakulaHubTheme
import kotlinx.coroutines.delay
import com.example.chakulahub.navigation.recipeRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakulaHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChakulaHubNavigation()
                }
            }
        }
    }
}

@Composable
fun ChakulaHubNavigation() {
    val navController = rememberNavController() // Create NavController

    NavHost(
        navController = navController,
        startDestination = "splash-screen"
    ) {
        composable("splash-screen") { SplashScreen(navController) }
        composable("teaser-screen") { TeaserScreen(navController) }
        composable("create-screen") { SecondTeaserScreen(navController) }
        composable("signup-screen") { SignUpScreen(navController) }
        composable("login-screen") { LoginScreen(navController) }
        composable("complete-profile-screen") { CompleteProfileScreen(navController) }
        composable("home-screen") { HomeScreen(navController) }
        composable("explore-screen") { ExploreScreen(navController,viewModel()) }
        composable("saved-recipe-screen") { SavedRecipesScreen(navController) }
        composable("profile-screen") { ProfileScreen(navController) }
        composable("edit-screen") { EditProfileScreen(navController) }
        composable("change-pwd-screen") { ChangePasswordScreen(navController) }
        composable("reset-pwd-screen") { ResetPasswordScreen(navController) }
        composable("rate-recipe-screen") { RateRecipeScreen(navController) }
        composable("logout-page") { LogoutConfirmationScreen(navController) }

        // Recipe Screens
        composable("banana-screen") { BananaCrispsRecipeScreen(navController) }
        composable("bhajia-screen") { BhajiaRecipeScreen(navController) }
        composable("chai-screen") { ChaiRecipeScreen(navController) }
        composable("chapati-screen") { ChapatiRecipeScreen(navController) }
        composable("githeri-screen") { GitheriRecipeScreen(navController) }
        composable("kuku-screen") { KukuManenosRecipeScreen(navController) }
        composable("mahamri-screen") { MahamriRecipeScreen(navController) }
        composable("mandazi-screen") { MandaziRecipeScreen(navController) }
        composable("masala-screen") { MasalaChipsRecipeScreen(navController) }
        composable("mukimo-screen") { MokimoRecipeScreen(navController) }
        composable("pojo-screen") { PojoRecipeScreen(navController) }
        composable("recipe-screen") { SwahiliSamosaRecipeScreen(navController) }
        composable("shakshouka-screen") { ShakshoukaRecipeScreen(navController) }
        composable("ugali-screen") { UgaliSukumaRecipeScreen(navController) }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChakulaHubTheme {
    }
}

//Logo page
@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.logoScreen)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Chakula Hub Logo",
            modifier = Modifier.size(200.dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(2000) // Wait for 2 seconds
        navController.navigate("teaser-screen") {
            popUpTo("splash-screen") { inclusive = true }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}

//Get started page
@Composable
fun TeaserScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.teaser),
            contentDescription = "Teaser Image",
            contentScale = ContentScale.Crop, // Ensures the image fills the box without distortion
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp), // Increased bottom padding to move up
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("create-screen") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
                    .offset(y = (-16).dp), // Moves button up slightly
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Get Started", fontSize = 20.sp, color = Color(0xFFFFA500))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeaserScreenPreview() {
    val navController = rememberNavController()
    TeaserScreen(navController = navController)
}

//Second teaser screen - create account intro page
@Composable
fun SecondTeaserScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.create),
            contentDescription = "Teaser Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Centered Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .fillMaxHeight(0.48f)
                .fillMaxWidth(0.85f),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)), // Semi-transparent
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icon (Carrot)
                Icon(
                    painter = painterResource(id = R.drawable.carrot), // Ensure this icon exists
                    contentDescription = "Carrot Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Title
                Text(
                    text = "Deliciously Simple",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Description
                Text(
                    text = "Your kitchen companion for quick, easy, and delicious meals",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Button
                Button(
                    onClick = { navController.navigate(route = "signup-screen" ) },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Create Account",
                        color = Color(0xFFFFA500), // Orange text
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Login Text
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Already have an account?",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        color = Color(0xFFFFA500), // Orange color
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {navController.navigate(route = "login-screen" ) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondTeaserScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        SecondTeaserScreen(navController = navController)
    }
}

//Signup page
@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var termsError by remember { mutableStateOf("") }

    fun validate(): Boolean {
        var isValid = true

        if (name.isBlank()) {
            nameError = "Name cannot be empty"
            isValid = false
        } else {
            nameError = ""
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Enter a valid email"
            isValid = false
        } else {
            emailError = ""
        }

        if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordError = ""
        }

        if (!isChecked) {
            termsError = "You must agree to the Terms and Conditions"
            isValid = false
        } else {
            termsError = ""
        }

        return isValid
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.butter_chicken),
            contentDescription = "Sign-Up Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 200.dp),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Create Account", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fill in your information or register with your social account",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFFA500)
                )

                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(value = name, onValueChange = { name = it }, placeholder = "Name", error = nameError)
                CustomTextField(value = email, onValueChange = { email = it }, placeholder = "Email", error = emailError)
                CustomTextField(value = password, onValueChange = { password = it }, placeholder = "Password", error = passwordError, isPassword = true)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFFFA500), // Orange when checked
                            uncheckedColor = Color.Gray, // Gray when unchecked
                            checkmarkColor = Color.Black // Checkmark color inside the box
                        )
                    )
                    Text(text = "Agree with ", color = Color.Black, fontWeight = FontWeight.Light)
                    Text(text = "Terms and Conditions", color = Color(0xFFFFA500), fontWeight = FontWeight.Bold)
                }

                if (termsError.isNotEmpty()) {
                    Text(text = termsError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (validate()) {
                            navController.navigate(route = "complete-profile-screen")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Create Account",
                        color = Color(0xFFFFA500),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Already have an account?", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        color = Color(0xFFFFA500),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { navController.navigate(route = "login-screen") }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    error: String,
    isPassword: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = value,
            textStyle = TextStyle(color = Color.Black),
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            singleLine = true,
            shape = RoundedCornerShape(16.dp), // Rounded edges
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), // Padding outside the text field
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color(0xFFFFA500),
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,  // User input color (focused state)
                unfocusedTextColor = Color.Black, // User input color (unfocused state)
                focusedContainerColor = Color(0xFFF0F0F0), // Background color inside the text field
                unfocusedContainerColor = Color(0xFFF0F0F0) // Background color inside the text field
            ),
            trailingIcon = {
                if (isPassword) {
                    val iconRes = if (isPasswordVisible) R.drawable.visible else R.drawable.hidden
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Adjust icon size here
                        )
                    }
                }
            }

        )
        if (error.isNotEmpty()) {
            Text(text = error, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
        }
    }
}


@Composable
fun SocialIcon(iconRes: Int, navController: NavController) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = "Social Icon",
        modifier = Modifier
            .size(32.dp)
            .clickable { navController.navigate("login") }, // Navigate to login page
        tint = Color.Unspecified // Keeps original icon color
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        SignUpScreen(navController = navController)
    }
}


//Login page
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    // Error states
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // Validation function
    fun validateInput(): Boolean {
        var isValid = true

        if (email.isBlank()) {
            emailError = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email format"
            isValid = false
        } else {
            emailError = null
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.aloo_paratha), // Replace with your new image
            contentDescription = "Login Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        // White Card with Rounded Top Corners
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 190.dp),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
        ) {
            Column(
                modifier = Modifier.padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Hii! Welcome back to Chakula Hub, you've been missed",
                    fontSize = 16.sp,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                /// Email Field
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    isPassword = false,
                    error = emailError
                )
                Spacer(modifier = Modifier.height(8.dp))

                /// Password Field
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    isPassword = true,
                    error = passwordError
                )

                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.Black, // Thumb color when switched ON
                            checkedTrackColor = Color(0xFFFFA500), // Track color when switched ON
                            uncheckedThumbColor = Color.Black, // Thumb color when switched OFF
                            uncheckedTrackColor = Color.LightGray // Track color when switched OFF
                        )
                    )
                    Text(text = "Remember", modifier = Modifier.padding(start = 8.dp), color = Color.Black, fontSize = 15.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Forgot Password?",
                        color = Color(0xFFFFA500),
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.clickable { navController.navigate(route = "change-pwd-screen") }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                // Login Button with Validation
                Button(
                    onClick = {
                        if (validateInput()) {
                            navController.navigate(route = "home-screen")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Login",
                        color = Color(0xFFFFA500),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Don't have an account?", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Create Account",
                        fontSize = 14.sp,
                        color = Color(0xFFFFA500),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { navController.navigate(route = "signup-screen") }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    error: String? = null
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black),
            placeholder = { Text(placeholder) },
            singleLine = true,
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            isError = error != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFFA500), // Custom focused outline color
                unfocusedBorderColor = Color.Gray, // Outline color when not focused
                errorBorderColor = Color.Red, // Border color when there's an error
                cursorColor =  Color.Black, // Cursor color
            ),
                    trailingIcon = {
                if (isPassword) {
                    val iconRes = if (isPasswordVisible) R.drawable.visible else R.drawable.hidden
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        )
        error?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}


//complete profile page
@Composable
fun CompleteProfileScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton(navController)
        Spacer(modifier = Modifier.height(16.dp))
        ProfileHeader() // ✅ No more unresolved reference
        Spacer(modifier = Modifier.height(20.dp))
        ProfilePicture(context = context)
        Spacer(modifier = Modifier.height(20.dp))

        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(15.dp))

        CustomOutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            textStyle = TextStyle(color = Color.Black),
        )
        Spacer(modifier = Modifier.height(15.dp))

        PhoneNumberInput(phoneNumber) { phoneNumber = it }
        Spacer(modifier = Modifier.height(18.dp))

        GenderDropdown(selectedGender = gender, onGenderSelected = { gender = it })
        Spacer(modifier = Modifier.height(20.dp))

        SubmitButton(destination = "home-screen", navController = navController)
    }
}

@Composable
fun ProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Complete Profile",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Don't worry, only you can see your personal data.",
            fontSize = 14.sp,
            color = Color(0xFFFFA500),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun BackButton( navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_button), // ✅ Uses drawable resource
            contentDescription = "Back",
            modifier = Modifier
                .size(30.dp)
                .clickable { navController.popBackStack()}
        )
    }
}

@Composable
fun ProfilePicture(context: Context) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // Launcher to pick image from gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it }
    }

    // Launcher to capture image using camera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let { imageBitmap = it }
    }

    Box(contentAlignment = Alignment.BottomEnd) {
        // Profile Picture (either selected image or default)
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!.asImageBitmap(),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        } else {
            Image(
                painter = imageUri?.let { rememberAsyncImagePainter(it) } ?: painterResource(id = R.drawable.user),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        }

        // Edit Button
        Image(
            painter = painterResource(id = R.drawable.edit_text),
            contentDescription = "Edit",
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .clickable { showDialog = true }
        )
    }

    // Show Dialog to Choose Camera or Gallery
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select Profile Picture") },
            text = {
                Text("Choose an option to upload a profile picture.")
            },
            confirmButton = {
                Row {
                    Button(onClick = {
                        cameraLauncher.launch(null)
                        showDialog = false
                    }) {
                        Text("Camera")
                    }
                    Button(onClick = {
                        galleryLauncher.launch("image/*")
                        showDialog = false
                    }) {
                        Text("Gallery")
                    }
                }
            }
        )
    }
}


@Composable
fun PhoneNumberInput(phoneNumber: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = phoneNumber,
        onValueChange = onValueChange,
        label = { Text("0712345678", color = Color.Black) }, // Placeholder text in black
        textStyle = TextStyle(color = Color.Black), // User input text color set to black
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.kenya), // ✅ Uses drawable resource
                contentDescription = "Kenya Flag",
                modifier = Modifier.size(24.dp)
            )
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black, // Text color when field is focused
            unfocusedTextColor = Color.Black // Text color when field is unfocused
        )
    )
}


@Composable
fun GenderDropdown(selectedGender: String, onGenderSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female", "Not Disclose")

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedGender.ifEmpty { "Select Gender" },
            onValueChange = {}, // Read-only field
            readOnly = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }, // Ensures clicking opens the dropdown
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black, // Text color when field is focused
                unfocusedTextColor = Color.Black // Text color when field is unfocused
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.down_arrow),
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded } // Allows clicking the icon to toggle
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            genderOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onGenderSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            color = Color.Black // Ensures gender options text is black
                        )
                    }
                )
            }
        }
    }
}



@Composable
fun SubmitButton(navController: NavController, destination: String, buttonText: String = "Complete Profile") {
    Button(
        onClick = { navController.navigate(destination) },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = buttonText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500)
        )
    }
}


@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    textStyle: TextStyle
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(16.dp), // ✅ Rounded corners for consistency
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun CompleteProfileScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        CompleteProfileScreen(navController = navController)
    }
}

//level of expertise page
//@Composable
//fun CookingLevelScreen(navController: NavController) {
//    var selectedOption by remember { mutableStateOf("Beginner") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(10.dp))
//        // Progress Bar (Indicates last step)
//        LinearProgressIndicator(
//            progress = { 1f }, // ✅ Updated to use a lambda function
//            modifier = Modifier
//                .fillMaxWidth(0.6f) // ✅ Shorter width (60% of the screen)
//                .height(5.dp),
//            color = Color(0xFFFFA500) // ✅ Orange progress bar
//        )
//
//        Spacer(modifier = Modifier.height(8.dp)) // Space after progress bar
//
//        // Back button
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = {navController.navigate(route = "complete-profile-screen")
//            }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.back_button), // ✅ Uses drawable resource
//                    contentDescription = "Back",
//                    modifier = Modifier.size(30.dp) // ✅ Adjustable size
//                )
//            }
//        }
//
//        // Title and Subtitle
//        Text(
//            text = "Select Your Cooking Level Expertise",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = "Enhance Your Recipe Recommendations Based on Your Cooking Level.",
//            fontSize = 14.sp,
//            color = Color(0xFFFF7043), // Orange shade for emphasis
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Cooking Level Options (Radio Buttons)
//        val options = listOf("Beginner", "Intermediate", "Expert", "Not Sure")
//
//        options.forEach { option ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(if (selectedOption == option) Color.LightGray else Color(0xFFEFEFEF))
//                    .clickable { selectedOption = option }
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                RadioButton(
//                    selected = selectedOption == option,
//                    onClick = { selectedOption = option }
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = option,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(220.dp)) // Adjust the height value as needed
//
//        // Start Cooking Button
//        Button(
//            onClick = {  navController.navigate(route = "home-screen") },
//            modifier = Modifier
//                .fillMaxWidth(0.8f)
//                .height(50.dp),
//            shape = RoundedCornerShape(50),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
//        ) {
//            Text(
//                text = "Start Cooking",
//                color = Color(0xFFFFA500), // ✅ Changed text color to orange
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CookingLevelScreenPreview() {
//    MaterialTheme {
//        val navController = rememberNavController()
//        CookingLevelScreen(navController = navController)
//    }
//}


//change password page
@Composable
fun ChangePasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image - Ensuring it fills the entire screen properly
        Image(
            painter = painterResource(id = R.drawable.___1_), // Ensure this drawable exists
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
                    tint = Color(0xFFFFA500)
                )
            }
        }

        // Centered Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon
                Icon(
                    painter = painterResource(id = R.drawable.chef),
                    contentDescription = "Chef Hat",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = "Change Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email Input Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(color = Color.Black),
                    label = { Text("Email Address", color = Color.Black) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Resend Link Text
                Text(
                    text = "Resend link",
                    color = Color(0xFFFFA500),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { sendResetEmail(context, email) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Send Button
                Button(
                    onClick = { sendResetEmail(context, email) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Send",
                        color = Color(0xFFFFA500),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// Function to send a reset password email
fun sendResetEmail(context: Context, email: String) {
    if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        val resetLink = "https://yourapp.com/reset-password?email=$email"

        // EmailJS API Details (TO BE FILLED LATER)
        val serviceId = "your_service_id"
        val templateId = "your_template_id"
        val userId = "your_user_id"

        val jsonObject = JSONObject().apply {
            put("service_id", serviceId)
            put("template_id", templateId)
            put("user_id", userId)
            put("template_params", JSONObject().apply {
                put("to_email", email)
                put("reset_link", resetLink)
            })
        }

        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val requestBody = RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                jsonObject.toString()
            )

            val request = Request.Builder()
                .url("https://api.emailjs.com/api/v1.0/email/send")
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Failed to send reset email", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Reset email sent successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error sending email", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    } else {
        Toast.makeText(context, "Enter a valid email!", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController)
}
//reset password screen
@Composable
fun ResetPasswordScreen(navController: NavController) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.biryani), // Replace with actual background
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
                    tint = Color(0xFFFFA500) // Orange color
                )
            }
        }

        // Centered Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .height(420.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon
                Icon(
                    painter = painterResource(id = R.drawable.safe), // Replace with actual lock icon
                    contentDescription = "Lock",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = "Reset Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // New Password Input Field
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    textStyle = TextStyle(color = Color.Black),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Confirm Password Input Field
                OutlinedTextField(
                    value = confirmPassword,
                    textStyle = TextStyle(color = Color.Black),
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Error Message (if passwords don't match)
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Save Button
                Button(
                    onClick = {
                        if (newPassword == confirmPassword && newPassword.isNotEmpty()) {
                            errorMessage = ""
                            navController.navigate(route = "login-page") // Redirect to login
                        } else {
                            errorMessage = "Passwords do not match!"
                        }
                    },
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
fun ResetPasswordScreenPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController)
}


//rate recipe screen:



//logout page
@Composable
fun LogoutConfirmationScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.aloo_paratha), // Ensure this drawable exists
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
                    painter = painterResource(id = R.drawable.back_button), // Ensure this drawable exists
                    contentDescription = "Back",
                    tint = Color(0xFFFFA500) // Orange color
                )
            }
        }

        // Logout Confirmation Dialog
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.70f)), // Semi-transparent white
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Chef Icon
                Icon(
                    painter = painterResource(id = R.drawable.cook), // Ensure this drawable exists
                    contentDescription = "Chef Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Logout Text
                Text(
                    text = "Are you sure you want to Logout?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Yes and No Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate(route = "login-screen") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            "Yes",
                            color = Color(0xFFFFA500),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = { navController.navigate(route = "profile-screen") }, // Handle No (Dismiss)
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            "No",
                            color = Color(0xFFFFA500),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutConfirmationScreenPreview() {
    val navController = rememberNavController()
    LogoutConfirmationScreen(navController)
}




























































