package com.kev.firebaseauth.di

import com.google.firebase.auth.FirebaseAuth
import com.kev.firebaseauth.repository.AuthRepository
import com.kev.firebaseauth.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Singleton
	@Provides
	fun providesFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

	@Singleton
	@Provides
	fun providesRepository(impl: AuthRepositoryImpl) : AuthRepository = impl
}