package com.kev.firebaseauth.view.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kev.firebaseauth.R
import com.kev.firebaseauth.databinding.FragmentSignInBinding
import com.kev.firebaseauth.util.Resource
import com.kev.firebaseauth.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

	private var _binding: FragmentSignInBinding? = null
	private val binding get() = _binding!!
	private val viewModel: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentSignInBinding.inflate(inflater, container, false)
		return binding.root

	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		navigateToSignUp()

		binding.signInButton.setOnClickListener {
			signInUsers()
		}

		setUpObserver()

		super.onViewCreated(view, savedInstanceState)
	}


	private fun setUpObserver(){
		viewModel.loginLiveData.observe(viewLifecycleOwner) {resource->
			when(resource){
				is Resource.Failure ->{
					Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
					binding.progressBarSignin.visibility = View.GONE

				}
				is Resource.Loading->{
					binding.progressBarSignin.visibility = View.VISIBLE

				}

				is Resource.Success->{
					binding.progressBarSignin.visibility = View.GONE
					Toast.makeText(requireContext(), "Karibu sana,".plus(resource.result.email), Toast.LENGTH_SHORT).show()
				}
			}


		}
	}


	private fun signInUsers() {
		val email = binding.userEmailEtv.text.toString()
		val password = binding.userPasswordEtv.text.toString()


		if (email.isEmpty() || password.isEmpty() || password.length <= 5) {
			Toast.makeText(requireContext(), "Ensure all fields are filled, and password has more than 6 characters", Toast.LENGTH_SHORT).show()
		}


		if (email.isNotEmpty() && password.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			Toast.makeText(requireContext(), "Ensure the email is correct", Toast.LENGTH_SHORT).show()
		} else {

			viewModel.loginUser(email, password)
		}


	}

	private fun navigateToSignUp() {
		binding.signUpTxt.setOnClickListener {
			findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
		}
	}

	override fun onDestroy() {
		_binding = null
		super.onDestroy()
	}

}