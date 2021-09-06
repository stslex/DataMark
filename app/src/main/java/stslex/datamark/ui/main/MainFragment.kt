package stslex.datamark.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import stslex.datamark.R
import stslex.datamark.data.model.CodeModel
import stslex.datamark.databinding.FragmentMainBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.ui.main.adapter.MainAdapter
import stslex.datamark.util.Result
import stslex.datamark.util.snackBarError


@ExperimentalCoroutinesApi
class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory.get() }

    private lateinit var token: String
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val args: MainFragmentArgs by navArgs()
        token = args.token
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerView()

        binding.btnSignOut.setOnClickListener {
            viewModel.logOut(token)
            findNavController().navigate(R.id.action_nav_main_to_nav_auth)
        }
    }

    private fun initListeners() = viewLifecycleOwner.lifecycleScope.launch {
        val dateFrom = binding.textDateFrom.editText?.text.toString()
        val dateTo = binding.textDateTo.editText?.text.toString()
        val page = binding.textPage.editText?.text.toString()
        if (dateFrom.isNotEmpty() && dateTo.isNotEmpty() && page.isNotEmpty()) {
            binding.btnGetLabels.setOnClickListener {
                getListCode(dateFrom, dateTo, page)
            }
        }
    }

    private fun getListCode(dateFrom: String, dateTo: String, page: String) =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getShipsList(token, dateFrom, dateTo, page).collect {
                when (it) {
                    is Result.Success -> {
                        getLabels(it.data.ships_list)
                    }
                    is Result.Failure -> {
                        binding.root.snackBarError(it.exception.toString())
                    }
                    is Result.Loading -> {

                    }
                }
            }
        }

    private fun getLabels(shipsList: List<CodeModel>) = viewLifecycleOwner.lifecycleScope.launch {
        shipsList.forEach { itemCode ->
            viewModel.getShipsLabels(token, itemCode.code).collect { result ->
                when (result) {
                    is Result.Success -> {
                        adapter.addItems(result.data.labels, itemCode.code)
                        binding.btnMakeShips.setOnClickListener { view ->
                            val list = result.data.labels.map { it.label }
                            makeShips(itemCode.code, list)
                        }
                    }
                    is Result.Failure -> {
                        binding.root.snackBarError(result.exception.toString())
                    }
                    is Result.Loading -> {

                    }
                }
            }
        }
    }

    private fun makeShips(code: String, labels: List<String>) =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.makeShips(token, code, labels).collect {
                when (it) {
                    is Result.Success -> {
                        Snackbar.make(
                            binding.root,
                            "${it.data.count} Number ${it.data.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is Result.Failure -> {
                        binding.root.snackBarError(it.exception.toString())
                    }
                    is Result.Loading -> {
                    }
                }
            }
        }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        adapter = MainAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}