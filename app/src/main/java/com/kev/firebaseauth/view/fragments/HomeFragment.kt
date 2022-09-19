package com.kev.firebaseauth.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kev.firebaseauth.R
import com.kev.firebaseauth.databinding.FragmentHomeBinding
import com.kev.firebaseauth.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding
	private val viewModel: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)

		return binding?.root
	}


	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}