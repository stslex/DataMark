package stslex.datamark.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import stslex.datamark.R
import stslex.datamark.databinding.FragmentMainBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.ui.core.UIResult
import stslex.datamark.ui.main.adapter.MainAdapter
import stslex.datamark.util.snackBarError
import stslex.datamark.util.snackBarSuccess


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
        binding.btnGetShipsList.setOnClickListener(getShipsTakeClickListener)
        binding.btnSignOut.setOnClickListener(logOutClickListener)
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
            viewModel.getShipsList(token, dateFrom, dateTo, page).collect {
                when (it) {
                    is UIResult.Success -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarSuccess()
                        binding.btnGetLabels.isEnabled = true
                        val shipsCodes = it.data.ships_list.map { list -> list.code.toString() }
                        binding.btnGetLabels.setOnClickListener(getShipsLabels(shipsCodes))
                        adapter.addItems(it.data.ships_list)
                    }
                    is UIResult.Failure -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.exception.message.toString())
                    }
                    is UIResult.Error -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.message)
                    }
                    is UIResult.Loading -> {
                        binding.SHOWPROGRESS.visibility = View.VISIBLE
                    }
                }
            }
        }

    private fun getShipsLabels(shipsCodes: List<String>) = View.OnClickListener {
        viewLifecycleOwner.lifecycleScope.launch {
            val shipsMap = mutableMapOf<String, List<String>>()
            shipsCodes.forEach { code ->
                viewModel.getShipsLabels(token, code).collect {
                    when (it) {
                        is UIResult.Success -> {
                            binding.SHOWPROGRESS.visibility = View.INVISIBLE
                            binding.root.snackBarSuccess()
                            binding.btnMakeShips.isEnabled = true
                            val list = it.data.labels.map { item -> item.label }
                            shipsMap[code] = list
                        }
                        is UIResult.Error -> {
                            binding.SHOWPROGRESS.visibility = View.INVISIBLE
                            binding.root.snackBarError(it.message)
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
            binding.btnMakeShips.setOnClickListener(makeShips(shipsMap))
        }

    }

    private fun makeShips(shipsMap: Map<String, List<String>>) = View.OnClickListener {
        viewLifecycleOwner.lifecycleScope.launch {
            shipsMap.forEach { item ->
                viewModel.takeShips(token, item.key, item.value).collect { result ->
                    when (result) {
                        is UIResult.Success -> {
                            binding.SHOWPROGRESS.visibility = View.INVISIBLE
                            Snackbar.make(
                                binding.root,
                                "оприходовано ${result.data.count} элементов",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is UIResult.Error -> {
                            binding.SHOWPROGRESS.visibility = View.INVISIBLE
                            binding.root.snackBarError(result.message)
                        }
                        is UIResult.Failure -> {
                            binding.SHOWPROGRESS.visibility = View.INVISIBLE
                            binding.root.snackBarError(result.exception.message.toString())
                        }
                        is UIResult.Loading -> {
                            binding.SHOWPROGRESS.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private val logOutClickListener = View.OnClickListener {
        binding.SHOWPROGRESS.visibility = View.VISIBLE
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logOut(token).collect {
                when (it) {
                    is UIResult.Success -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarSuccess()
                        findNavController().navigate(R.id.action_nav_main_to_nav_auth)
                    }
                    is UIResult.Error -> {
                        binding.SHOWPROGRESS.visibility = View.INVISIBLE
                        binding.root.snackBarError(it.message)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}