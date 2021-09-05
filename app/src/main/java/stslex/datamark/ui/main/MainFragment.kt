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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import stslex.datamark.R
import stslex.datamark.data.model.ShipModel
import stslex.datamark.databinding.FragmentMainBinding
import stslex.datamark.ui.BaseFragment
import stslex.datamark.ui.main.adapter.MainAdapter
import stslex.datamark.util.Result


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
            findNavController().navigate(R.id.action_nav_main_to_nav_auth)
        }
    }

    private fun initListeners() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.getShipsList(token).collect {
            when (it) {
                is Result.Success -> {
                    getLabels(it.data.ships_list)
                }
                is Result.Failure -> {

                }
                is Result.Loading -> {

                }
            }
        }
    }

    private fun getLabels(shipsList: List<ShipModel>) = viewLifecycleOwner.lifecycleScope.launch {
        shipsList.forEach { shipModel ->
            viewModel.getShipsLabels(token, shipModel.code).collect { result ->
                when (result) {
                    is Result.Success -> {
                        adapter.addItems(result.data.labels, result.data.code)
                        binding.btnMakeShips.setOnClickListener { view ->
                            result.data.labels.forEach {
                                viewModel.makeShips(token, shipModel.code, it.label)
                            }
                        }
                    }
                    is Result.Failure -> {

                    }
                    is Result.Loading -> {

                    }
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