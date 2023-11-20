package com.example.myica

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myica.theme.UKPoliceTheme
import com.example.myica.viewmodel.PostViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var db: FirebaseFirestore

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        database = Firebase.database
        db = Firebase.firestore

        setContent {
            UKPoliceTheme {
                val openDialog = remember { mutableStateOf(false) }
                val logining = remember { mutableStateOf(true) }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    if (logining.value) {
                        loginScreen(auth, logining)
                    } else {
                        val postViewModel = viewModel<PostViewModel>()
                        postViewModel.refresh()
                        mainScreen(openDialog, logining, auth, database, db, postViewModel)
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun mainScreen(
        openDialog: MutableState<Boolean>,
        logining: MutableState<Boolean>,
        auth: FirebaseAuth,
        database: FirebaseDatabase,
        db: FirebaseFirestore,
        postViewModel: PostViewModel
    ) {
        Greeting(name = "Welcome to UKPoliceApp")
    }


    @Composable
    fun Greeting(name: String) {
        val logining = remember { mutableStateOf(true) }
        Column {
            Text(
                text = "Hello $name!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()

            )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimestamp(time: String): String {
        val duration = Duration.between(
            LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            LocalDateTime.now()
        )
        var timestamp = ""
        if (duration.toDays() > 6)
            timestamp = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME).format(
                DateTimeFormatter.ofPattern("LLLL dd")
            )
        else if (duration.toDays() > 0)
            timestamp = duration.toDays().toString() + " days ago"
        else if (duration.toHours() > 0)
            timestamp = duration.toHours().toString() + " hours ago"
        else if (duration.toMinutes() > 0)
            timestamp = duration.toMinutes().toString() + " minutes ago"
        else if (duration.seconds > 0)
            timestamp = duration.seconds.toString() + " seconds ago"
        else if (duration.toMillis() > 0)
            timestamp = "Just now"
        return timestamp
    }

    @Composable
    fun dialogTextField(text: MutableState<String>, label: String) {
        OutlinedTextField(
            value = text.value,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = {
                Text(
                    text = label,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Black
                )
            },
            onValueChange = {
                text.value = it
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
        )
    }

    @Composable
    fun loginScreen(auth: FirebaseAuth, logining: MutableState<Boolean>) {
        val email = remember {
            mutableStateOf("")
        }
        val pass = remember {
            mutableStateOf("")
        }
        val pass2 = remember {
            mutableStateOf("")
        }
        val registering = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.LocalPolice,
                tint = Color(0xff7e3b07),
                contentDescription = "logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = "UK Police App",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 100.dp)
            )
            loginTextField(
                text = email, label = "Email", placeholder = "Enter email",
                Icons.Filled.Email, false
            )
            loginTextField(
                text = pass,
                label = "Password",
                placeholder = "Enter password",
                Icons.Filled.Lock
            )
            if (registering.value) {
                loginTextField(
                    text = pass2, label = "Confirm Password", placeholder = "Confirm password",
                    Icons.Filled.Lock
                )
                Button(
                    onClick = {
                        if (pass.value == pass2.value) {
                            auth.createUserWithEmailAndPassword(email.value, pass.value)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        logining.value = false
                                        Log.d("DEBUG", "Create new user successful")
                                    } else
                                        Log.d("DEBUG", "Create new user fail")
                                }
                        }
                    },
                    content = {
                        Text(
                            text = "Register",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 100.dp, end = 100.dp, top = 10.dp, bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffec7b01))
                )
                TextButton(
                    onClick = { registering.value = false },
                    content = { Text(text = "Log in") },
                    modifier = Modifier.padding(10.dp)
                )
            } else {
                Button(
                    onClick = {
                        if(email.equals("") || pass.equals("")){
                            Log.d("ERROR", "Email or password shouldn't be empty. Try again.")
                        }
                        auth.signInWithEmailAndPassword(email.value, pass.value)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    logining.value = false
                                    Log.d("DEBUG", "Logging successful")
                                } else
                                    Log.d("DEBUG", "Logging fail")
                            }
                    },
                    content = {
                        Text(
                            text = "Login",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 100.dp, end = 100.dp, top = 10.dp, bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffec7b01))
                )
                TextButton(
                    onClick = { registering.value = true },
                    content = { Text(text = "Register") },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }

    @Composable
    fun loginTextField(
        text: MutableState<String>,
        label: String,
        placeholder: String,
        icon: ImageVector,
        hideText: Boolean = true
    ) {
        TextField(
            modifier = Modifier.padding(bottom = 10.dp),
            value = text.value,
            onValueChange = { text.value = it },
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = { Icon(imageVector = icon, contentDescription = "email") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )
    }


}