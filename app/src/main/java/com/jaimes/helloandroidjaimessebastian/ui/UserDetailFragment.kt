package com.jaimes.helloandroidjaimessebastian.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaimes.helloandroidjaimessebastian.R
import com.jaimes.helloandroidjaimessebastian.databinding.FragmentUserDetailBinding
import com.jaimes.helloandroidjaimessebastian.viewmodel.UserViewModel

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by activityViewModels()
    private val args: UserDetailFragmentArgs by navArgs()

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
        loadUserById(args.userId)
        setupObservers()
        setupClickListeners()
    }

    private fun loadUserById(userId: Int) {
        viewModel.getUserById(userId)?.let(viewModel::selectUser)
    }

    private fun setupObservers() {
        viewModel.selectedUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.textViewId.text = getString(R.string.user_id, it.id)
                binding.textViewName.text = getString(R.string.user_name, it.name)
                binding.textViewEmail.text = getString(R.string.user_email, it.email)
                binding.textViewAge.text = getString(R.string.user_age, it.age)
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
