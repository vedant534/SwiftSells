package com.example.swiftsells.screens.searchscreen

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
class SearchScreenViewModel
@Inject constructor(
    private val repository: ProductRepository
)
:    ViewModel() {

    var list: ArrayList<ProductDetails> by mutableStateOf(ArrayList<ProductDetails>())


    init {
        getAllProducts()
    }

    private fun getAllProducts()
    : ArrayList<ProductDetails>
    {

        viewModelScope.launch {
            list = repository.getAllProducts()
        }
        return list
    }

}