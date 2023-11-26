package com.example.swiftsells.screens.addscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel
@Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    var isDialogShown by mutableStateOf(false)
    private set

    fun onAddClick(){
        isDialogShown = true
    }

    fun pushProduct(productDetails: ProductDetails) {
        viewModelScope.launch {
            repository.pushProduct(productDetails)
        }
    }

    fun onDismissDialog() {
        isDialogShown = false
    }
}