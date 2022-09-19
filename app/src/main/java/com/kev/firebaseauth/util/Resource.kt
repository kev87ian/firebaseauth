package com.kev.firebaseauth.util

import java.lang.Exception

sealed class Resource<out R>{
	data class Success<out R >(val result:R):Resource<R>()
	data class Failure(val errorMessage: String) : Resource<Nothing>()
	object Loading : Resource<Nothing>()
}
