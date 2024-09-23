package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.Men_Women_Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class Men_Women_ViewModel @Inject constructor(private val Men_Women_Repository: Men_Women_Repository) : ViewModel() {
    val datalist=Men_Women_Repository.datalist

    fun getData(colletion:String,category:String){
        viewModelScope.launch(Dispatchers.IO) {
            Men_Women_Repository.getData(colletion,category)
        }
    }
}