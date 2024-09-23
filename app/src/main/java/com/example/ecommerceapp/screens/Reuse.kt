package com.example.ecommerceapp.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.util.Authentication
import com.example.ecommerceapp.viewModel.AuthenticatioinViewModel
import com.example.ecommerceapp.viewModel.SingInUpViewModel

@Composable
fun Reuse(navController: NavController, state: String) {
    val authenticatioinViewModel:AuthenticatioinViewModel= hiltViewModel()
    val signInUpViewModel:SingInUpViewModel= hiltViewModel()
    val authState = authenticatioinViewModel.authstate.collectAsState()
    val email = signInUpViewModel.email.collectAsState()
    val password = signInUpViewModel.password.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
       when(authState.value){
           Authentication.Authenticated ->navController.navigate(Routes.home)
          else->Unit
       }
    }
Column {
    Box(
        modifier = Modifier
            .weight(.9f)
            .fillMaxWidth().background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
            // .padding(bottom = 100.dp) // Ensure there's enough space for the image at the bottom
        ) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { signInUpViewModel.setEmail(it) },
                placeholder = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { signInUpViewModel.setPassword(it) },
                placeholder = { Text("Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (state ==Routes.login) {
                button(
                    "Log In", navController,
                    "Don't have an Account? Sign In", Routes.singIn,
                    email.value, password.value, context, state, authenticatioinViewModel
                )
            }
            if (state == Routes.singIn) {
                button(
                    "Sign In", navController,
                    "Already have an Account? Log In", Routes.login,
                    email.value, password.value, context, state, authenticatioinViewModel
                )
            }
        }
    }
}
}


@Composable
fun button(
    buttontext: String,
    navController: NavController,
    text: String,
    changestate: String,
    email: String,
    password: String,
    context: Context,
    state: String,
    authenticationViewModel: AuthenticatioinViewModel
){
    Button(
        onClick = {
            if(Authentication(email,password,context)) {
                if (state == Routes.login) {
                  authenticationViewModel.login(email,password)
                }
                if (state == Routes.singIn) {
                    authenticationViewModel.singIn(email,password){
                        if(it){
                            navController.navigate(Routes.allCategories)
                        }
                    }
                }
            }
        },
        colors = ButtonDefaults.buttonColors(Color(16, 29, 77)),
        modifier = Modifier.size(290.dp, 50.dp)
    ) {
        Text(buttontext, fontSize = 18.sp)
    }
    TextButton(
        onClick = {
            navController.navigate(changestate)
        }
    ) {
        Text(text, fontSize = 17.sp, modifier = Modifier
            .padding(start = 15.dp, top = 10.dp)
        )
    }
}