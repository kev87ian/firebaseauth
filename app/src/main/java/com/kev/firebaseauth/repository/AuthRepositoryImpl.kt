package com.kev.firebaseauth.repository

import com.google.firebase.auth.*
import com.kev.firebaseauth.util.Resource
import com.kev.firebaseauth.util.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val firebaseAuth: FirebaseAuth
) : AuthRepository {
	override val currentUser: FirebaseUser?
		get() = firebaseAuth.currentUser

	override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
		return try {
			val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
			Resource.Success(result.user!!)

		} catch (e:Exception) {
			when(e){
				is FirebaseAuthInvalidCredentialsException ->{
					Resource.Failure("Invalid credentials")
				}

				is FirebaseAuthInvalidUserException ->{
					Resource.Failure("The email entered does not exist")
				}
				else -> {
					Resource.Failure("")
				}
			}
		}

	}

	override suspend fun signup(
		email: String,
		name: String,
		password: String
	): Resource<FirebaseUser> {

		return try {
			val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
			Resource.Success(result.user!!)
		}catch (e:Exception){
			Resource.Failure(e.message.toString())
		}
	}

	override fun logout() {
		firebaseAuth.signOut()
	}
}