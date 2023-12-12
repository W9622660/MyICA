package com.example.myica.ui.login

import android.health.connect.datatypes.units.Length
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myica.data.api.model.Force
import com.example.myica.ui.MainDestinations
import com.google.firebase.auth.FirebaseAuth


@Composable
fun loginScreen(navController: NavController, auth: FirebaseAuth, logining: MutableState<Boolean>) {
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
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Icon(
            imageVector = Icons.Filled.Warning,
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
            androidx.compose.material.Button(
                onClick = {
                    if (email.value.equals("") || pass.value.equals("")) {
                        Log.d("ERROR", "Email or password shouldn't be empty. Try again.")
                        Toast.makeText(context, "Email or password shouldn't be empty. Try again.", Toast.LENGTH_LONG).show()
                        navController?.navigate(MainDestinations.LOGIN_ROUTE)
                    }else{
                        if (pass.value == pass2.value) {
                            auth.createUserWithEmailAndPassword(email.value, pass.value)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        logining.value = false
                                        Log.d("DEBUG", "Create new user successful")

                                        Toast.makeText(context, "Create new user successful", Toast.LENGTH_SHORT).show()
                                    } else
                                        Log.d("DEBUG", "Create new user fail")
                                    Toast.makeText(context, "Create new user fail" + task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                                }
                        }else{
                            Log.d("ERROR", "Passwords doesn't match")
                            Toast.makeText(context, "Passwords doesn't match", Toast.LENGTH_SHORT).show()
                            navController?.navigate(MainDestinations.LOGIN_ROUTE)
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
            androidx.compose.material.TextButton(
                onClick = { registering.value = false },
                content = { Text(text = "Log in") },
                modifier = Modifier.padding(10.dp)
            )
        } else {
            androidx.compose.material.Button(
                onClick = {
                    if (email.value.equals("") || pass.value.equals("")) {
                        Log.d("ERROR", "Email or password shouldn't be empty. Try again.")
                        Toast.makeText(context, "Email or password shouldn't be empty. Try again.", Toast.LENGTH_SHORT).show()
                        navController?.navigate(MainDestinations.LOGIN_ROUTE)
                    }else{
                        auth.signInWithEmailAndPassword(email.value, pass.value)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    logining.value = false
                                    Log.d("DEBUG", "Logging successful")
                                    Toast.makeText(context, "Logging successful", Toast.LENGTH_LONG).show()
                                    navController?.navigate(MainDestinations.HOME_ROUTE)
                                } else
                                    Log.d("DEBUG", "Logging fail" + task.exception.toString())
                                Toast.makeText(context, "Logging fail - " + task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                            }
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
            androidx.compose.material.TextButton(
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
    androidx.compose.material.TextField(
        modifier = Modifier.padding(bottom = 10.dp),
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            androidx.compose.material.Icon(
                imageVector = icon,
                contentDescription = "email"
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}





