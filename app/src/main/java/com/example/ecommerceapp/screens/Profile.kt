package com.example.ecommerceapp.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.UserAddress
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.util.Authentication
import com.example.ecommerceapp.viewModel.AuthenticatioinViewModel
import com.example.ecommerceapp.viewModel.ProfileViewModel

@Composable
fun Profile(navController: NavController) {
    val profileViewModel:ProfileViewModel= hiltViewModel()
    val userprofile=profileViewModel.userProfile.collectAsState()
    val authenticatioinViewModel: AuthenticatioinViewModel = hiltViewModel()
    val authState = authenticatioinViewModel.authstate.collectAsState()
    val username=profileViewModel.username.collectAsState()
    val selectedImageUri=profileViewModel.selectedImageUri.collectAsState()
    val pincode=profileViewModel.pincode.collectAsState()
    val city=profileViewModel.city.collectAsState()
    val district=profileViewModel.district.collectAsState()
    val houseAddress=profileViewModel.houseAddress.collectAsState()
    var seeAddress by remember { mutableStateOf(false) }
    LaunchedEffect(authState.value) {
        when(authState.value){
            Authentication.UnAuthenticated -> navController.navigate(Routes.login)
            else->Unit
        }
    }
    val font= FontFamily(Font(R.font.lora))
    val singlePhotoLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if(it!=null){
                profileViewModel.setUri(it)
            }
        }
    )
    val keyboardController= LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxSize()
        .paint(painter = painterResource( R.drawable.background), contentScale = ContentScale.Crop)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.3f)
        ) {
            AsyncImage(
                model = if(selectedImageUri.value==null){
                    R.drawable.name
                } else {
                    selectedImageUri.value
                },
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .clickable {
                        singlePhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ,
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier.weight(.6f).background(Color.White, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)).fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                Column (modifier = Modifier.padding(start = 10.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material.Text(
                        "Name ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = font,
                    )
                    Spacer(Modifier.padding(bottom = 10.dp))
                    OutlinedTextField(
                        value =username.value,
                        onValueChange = { profileViewModel.setUserName(it) },
                        placeholder = { Text("Username") },
                    //    modifier = Modifier.align(Alignment.CenterHorizontally),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done // Set the action button to "Done"
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                            }
                        )
                    )
                }
                Spacer(Modifier.height(15.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    TextButton (
                        onClick = {
                            seeAddress=!seeAddress
                        },
                        border = BorderStroke(3.dp, Color.Blue)
                    ){
                        Text("Address ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = font,)
                    }
                    Spacer(Modifier.padding(bottom = 10.dp))
                    if(seeAddress) {
                        Row(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            userprofile.value?.address?.let {
                                OutlinedTextField(
                                    value = pincode.value,
                                    onValueChange = { profileViewModel.setPincode(it) },
                                    placeholder = { Text("Pin code") },
                                    modifier = Modifier.weight(1f),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Decimal,
                                        imeAction = ImeAction.Done // Set the action button to "Done"
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                                        }
                                    )
                                )
                            }
                            userprofile.value?.address?.let {
                                OutlinedTextField(
                                    value = city.value,
                                    onValueChange = { profileViewModel.setcity(it)},
                                    placeholder = { Text("City") },
                                    modifier = Modifier.weight(1f),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done // Set the action button to "Done"
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                                        }
                                    )
                                )
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            userprofile.value?.address?.let {
                                OutlinedTextField(
                                    value = district.value,
                                    onValueChange = { profileViewModel.setDistrict(it) },
                                    placeholder = { Text("District") },
                                    modifier = Modifier.weight(1f),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done // Set the action button to "Done"
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                                        }
                                    )
                                )
                            }
                            userprofile.value?.address?.let {
                                OutlinedTextField(
                                    value = houseAddress.value,
                                    onValueChange = { profileViewModel.setHouseAddress(it) },
                                    placeholder = { Text("House Address") },
                                    modifier = Modifier.weight(1f),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done // Set the action button to "Done"
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                                        }
                                    )
                                )
                            }
                        }
                    }
                    else{
                        val address: UserAddress? = userprofile.value?.address
                        if (address != null && address.city.isNotEmpty() && address.pincode.isNotEmpty() && address.district.isNotEmpty() && address.houseAdress.isNotEmpty()) {
                            Text("Address: ${address.pincode}, ${address.city},${address.district},${address.houseAdress}", fontSize = 20.sp, fontFamily = font)
                        }
                        else{
                            Text("No address added", fontSize = 20.sp, fontFamily = font)
                        }
                    }
                }
                Spacer(Modifier.padding(bottom = 40.dp))
                Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                    Button(
                        onClick = {
                            profileViewModel.update(
                                username.value,
                                selectedImageUri.value,
                                UserAddress(pincode.value,city.value,district.value,houseAddress.value)
                            )
                            keyboardController?.hide()
                            navController.navigate(Routes.home)
                        },
                        colors = ButtonDefaults.buttonColors(Color(16, 29, 77)),
                        modifier = Modifier.size(290.dp, 50.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text("Update Profile", fontSize = 18.sp, fontFamily = font)
                    }
                    Spacer(Modifier.padding(bottom = 40.dp))
                    Button(
                        onClick = { authenticatioinViewModel.logOut() },
                        colors = ButtonDefaults.buttonColors(Color(16, 29, 77)),
                        modifier = Modifier.size(290.dp, 50.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text("Log out", fontSize = 18.sp, fontFamily = font)
                    }
                }
            }
        }
    }
}