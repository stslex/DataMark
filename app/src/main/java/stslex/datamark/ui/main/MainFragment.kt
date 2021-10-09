package stslex.datamark.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import stslex.datamark.R
import stslex.datamark.databinding.FragmentMainBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.ui.core.UIResult
import stslex.datamark.ui.main.adapter.MainAdapter
import stslex.datamark.util.snackBarError


@ExperimentalCoroutinesApi
class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory.get() }

    private var _token: String? = null
    private val token: String get() = _token!!

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val args: MainFragmentArgs by navArgs()
        _token = args.token
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.btnGetLabels.setOnClickListener(getShipsTakeClickListener)
        binding.btnSignOut.setOnClickListener(logOutClickListener)
    }

    private val logOutClickListener = View.OnClickListener {
        binding.SHOWPROGRESS.visibility = View.VISIBLE
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logOut(token).collect {
                when (it) {
                    is UIResult.Success -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        findNavController().navigate(R.id.action_nav_main_to_nav_auth)
                    }
                    is UIResult.Error -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.error?.message.toString())
                    }
                    is UIResult.Failure -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.exception.message.toString())
                    }
                    is UIResult.Loading -> {
                        binding.SHOWPROGRESS.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private val getShipsTakeClickListener = View.OnClickListener {
        binding.SHOWPROGRESS.visibility = View.VISIBLE
        val dateFrom = binding.textDateFrom.editText?.text.toString()
        val dateTo = binding.textDateTo.editText?.text.toString()
        val page = binding.textPage.editText?.text.toString()
        getListCode(dateFrom, dateTo, page)
    }

    private fun getListCode(dateFrom: String, dateTo: String, page: String) =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getShipsTake(token, dateFrom, dateTo, page).collect {
                when (it) {
                    is UIResult.Success -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        adapter.addItems(it.data.ships_list)
                    }
                    is UIResult.Failure -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.exception.message.toString())
                    }
                    is UIResult.Error -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.error?.message.toString())
                    }
                    is UIResult.Loading -> {
                        binding.SHOWPROGRESS.visibility = View.VISIBLE
                    }
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}