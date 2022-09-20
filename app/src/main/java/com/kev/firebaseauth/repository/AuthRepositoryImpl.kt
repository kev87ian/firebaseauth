package com.kev.firebaseauth.repository

import com.google.firebase.auth.*
import com.kev.firebaseauth.util.Resource
import com.kev.firebaseauth.util.await
import java.io.IOException
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

		} catch (e: Exception) {
			when (e) {
				is FirebaseAuthInvalidCredentialsException -> {
					Resource.Failure("Incorrect password")
				}

				is FirebaseAuthInvalidUserException -> {
					Resource.Failure("This user does not exist.")
				}
				is IOException -> {
					Resource.Failure("Ensure ")
				}


				else -> {
					Resource.Failure(e.localizedMessage)
				}
			}
		}

	}

/*	override suspend fun signup(email: String, name: String, password: String, confirmPassword: String
	): Resource<FirebaseUser> {

		return try {
			val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
			Resource.Success(result.user!!)
		} catch (e: Exception) {
			when(e){
				is FirebaseAuthUserCollisionException ->{
					Resource.Failure("Could not create an account. Please try again")
				}

				is FirebaseAuthWeakPasswordException ->{
					Resource.Failure("Your password isn't strong enough")
				}

				else -> {
					Resource.Failure(e.localizedMessage)
				}
			}
		}
	}*/

	override suspend fun signup(email: String, password: String): Resource<FirebaseUser> {
		return try {
			val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
			Resource.Success(result.user!!)
		}catch (e:Exception){
			when(e){
				is IOException -> Resource.Failure("Ensure you have an active internet connection")
				is FirebaseAuthWeakPasswordException -> Resource.Failure("Your password isn't strong enough.")
				is FirebaseAuthUserCollisionException -> Resource.Failure("Could not create your account. Please try again.")
				else -> Resource.Failure(e.localizedMessage)
			}

		}
	}

	override fun logout() {
		firebaseAuth.signOut()
	}
}