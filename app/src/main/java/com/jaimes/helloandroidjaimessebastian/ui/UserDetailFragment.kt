package com.jaimes.helloandroidjaimessebastian.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jaimes.helloandroidjaimessebastian.viewmodel.UserViewModel
import com.jaimes.helloandroidjaimessebastian.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Aquí configuraremos los observers después
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}