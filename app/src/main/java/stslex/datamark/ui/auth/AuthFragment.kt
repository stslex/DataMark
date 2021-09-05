package stslex.datamark.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import stslex.datamark.R
import stslex.datamark.databinding.FragmentAuthBinding
import stslex.datamark.ui.BaseFragment


class AuthFragment : BaseFragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by activityViewModels { viewModelFactory.get() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_nav_auth_to_nav_main)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}