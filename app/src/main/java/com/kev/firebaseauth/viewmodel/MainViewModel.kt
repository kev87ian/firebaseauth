package com.kev.firebaseauth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.kev.firebaseauth.repository.AuthRepository
import com.kev.firebaseauth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val repository: AuthRepository
) : ViewModel() {

	val loginLiveData: MutableLiveData<Resource<FirebaseUser>?> = MutableLiveData()
	val signUpLiveData: MutableLiveData<Resource<FirebaseUser>?> = MutableLiveData()


	fun loginUser(email: String, password: String) = viewModelScope.launch {
		loginLiveData.postValue(Resource.Loading)
		val result = repository.login(email, password)
		loginLiveData.postValue(result)
	}


	fun signupUser(email: String, password: String) = viewModelScope.launch {
		signUpLiveData.postValue(Resource.Loading)
		val result = repository.signup(email, password)
		signUpLiveData.postValue(result)
	}

}

