package com.kev.firebaseauth.repository

import com.google.firebase.auth.FirebaseUser
import com.kev.firebaseauth.util.Resource

interface AuthRepository {

	val currentUser : FirebaseUser?
	suspend fun login(email : String, password:String) : Resource<FirebaseUser>
	suspend fun signup(email: String, password:String) : Resource<FirebaseUser>
	fun logout()
}