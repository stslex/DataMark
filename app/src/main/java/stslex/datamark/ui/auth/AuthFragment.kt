package stslex.datamark.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import stslex.datamark.data.model.TokenModel
import stslex.datamark.databinding.FragmentAuthBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.util.Result


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
            val username = binding.textEmail.editText?.text.toString()
            val password = binding.textPassword.editText?.text.toString()
            val agree = true
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.auth(username, password, agree).collect {
                    it.collect()
                }
            }
        }
    }

    private fun Result<TokenModel>.collect() = when (this) {
        is Result.Success -> {
            val directions =
                AuthFragmentDirections.actionNavAuthToNavMain(data.token)
            findNavController().navigate(directions)
        }
        is Result.Failure -> {

        }
        is Result.Loading -> {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}