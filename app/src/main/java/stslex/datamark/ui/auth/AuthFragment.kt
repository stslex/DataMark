package stslex.datamark.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import stslex.datamark.data.model.ui.TokenModel
import stslex.datamark.databinding.FragmentAuthBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.ui.core.UIResult
import stslex.datamark.util.snackBarError

@ExperimentalCoroutinesApi
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
        binding.btnSignIn.setOnClickListener(signInClickListener)
    }

    private val signInClickListener = View.OnClickListener {
        binding.SHOWPROGRESS.visibility = View.VISIBLE
        val username = binding.textEmail.editText?.text.toString()
        val password = binding.textPassword.editText?.text.toString()
        val agree = true
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.auth(username, password, agree).collect {
                it.collect()
            }
        }
    }

    private fun UIResult<TokenModel>.collect() {
        when (this) {
            is UIResult.Success -> {
                binding.SHOWPROGRESS.visibility = View.INVISIBLE
                binding.root.snackBarError("Success")
                val directions =
                    AuthFragmentDirections.actionNavAuthToNavMain(data.token)
                findNavController().navigate(directions)
            }
            is UIResult.Failure -> {
                binding.SHOWPROGRESS.visibility = View.INVISIBLE
                binding.root.snackBarError(exception.message.toString())
            }
            is UIResult.Error -> {
                binding.SHOWPROGRESS.visibility = View.INVISIBLE
                binding.root.snackBarError(message)
            }
            is UIResult.Loading -> {
                binding.SHOWPROGRESS.visibility = View.VISIBLE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}