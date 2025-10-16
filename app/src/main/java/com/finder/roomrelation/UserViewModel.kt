package com.finder.roomrelation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity
import com.finder.roomrelation.entity.UserWithDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _readAllData = MutableStateFlow<List<UserWithDetailModel>>(emptyList())
    var readAllData = _readAllData.asStateFlow()

    private val _readAllCustomers = MutableStateFlow<List<Customers>>(emptyList())
    var readAllCustomers = _readAllCustomers.asStateFlow()

    init {
        getCustomers()
    }

    fun insertUser(user: List<UserEntity>) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }

    }

    fun insertUserDetails(user: List<UserDetailsEntity>) {
        viewModelScope.launch {
            userRepository.insertUserDetails(user)
        }

    }

    fun getUserWithDetails(userId: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.value = userRepository.getUserWithDetails(userId)
        }

    }

    fun insertCustomers(customers: List<Customers>) {
        viewModelScope.launch {
            userRepository.insertCustomers(customers)
        }
    }
    private fun getCustomers() {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllCustomers.value = userRepository.getCustomers()
        }
    }

    fun insertOrder(order: List<Orders>){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertOrder(order)
        }

    }

    fun insertExamRank(exam: List<Exam>){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertExamRank(exam)
        }
    }
}