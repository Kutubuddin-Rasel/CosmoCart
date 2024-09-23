package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.ElectronicsRepository
import com.example.ecommerceapp.repository.Men_Women_Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ElectronicsViewModel @Inject constructor(private val electronicsRepository: ElectronicsRepository) : ViewModel() {
    val datalist=electronicsRepository.datalist

    fun getData(colletion:String,category:String){
        viewModelScope.launch(Dispatchers.IO) {
            electronicsRepository.getData(colletion,category)
        }
    }
}