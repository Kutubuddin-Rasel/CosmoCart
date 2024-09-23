package com.example.ecommerceapp.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.UserAddress
import com.example.ecommerceapp.data.profile
import com.example.ecommerceapp.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _selectedImageUri= MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> =_selectedImageUri
    private val _userProfile= MutableStateFlow<profile?>(null)
    val userProfile:StateFlow<profile?> =_userProfile
    private val _username= MutableStateFlow("")
    val username: StateFlow<String> =_username

    private val _pincode= MutableStateFlow("")
    val pincode:StateFlow<String> =_pincode
    private val _city= MutableStateFlow("")
    val city:StateFlow<String> =_city
    private val _district= MutableStateFlow("")
    val district:StateFlow<String> =_district
    private val _houseAddress= MutableStateFlow("")
    val houseAddress:StateFlow<String> =_houseAddress


    init {
        getImgerUrl()
        getuserdetais()
    }
    fun setUserName(username:String){
        _username.value=username
    }
    fun setPincode(pincode:String){
        _pincode.value=pincode
    }
    fun setcity(city:String){
        _city.value=city
    }
    fun setDistrict(district:String){
        _district.value=district
    }
    fun setHouseAddress(houseAddress:String){
        _houseAddress.value=houseAddress
    }
    fun setUri(uri:Uri){
        _selectedImageUri.value=uri
    }

    fun update(username: String,image:Uri?,address: UserAddress){
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfile(username,image,address)
        }
    }
    fun getImgerUrl(){
        viewModelScope.launch(Dispatchers.IO) {
            val image=profileRepository.getImageUrl()
            if(image!=null){
                _selectedImageUri.value=image
            }
        }
    }
    fun getuserdetais(){
        viewModelScope.launch(Dispatchers.IO) {
           val profile = profileRepository.getUserdetails()
            _username.value=profile.username
            Log.d("USERADDRESS",profile.address.toString())
            _pincode.value=profile.address.pincode
            _city.value=profile.address.city
            _district.value=profile.address.district
            _houseAddress.value=profile.address.houseAdress
            _userProfile.value=profile
        }
    }
}