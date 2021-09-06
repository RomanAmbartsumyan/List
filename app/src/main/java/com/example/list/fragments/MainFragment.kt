package com.example.list.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.list.R
import com.example.list.databinding.FragmentMainBinding
import com.example.list.list.CardAdapter
import com.example.list.utils.autoCleared
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
    private var cardAdapter: CardAdapter by autoCleared()
    private val viewModel: MainFragmentViewModel by viewModels()
    private var countColumns = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.initList()
        }
        countColumns = countColumns()
        initList()
        lifecycleScope.launch {
            while (true) {
                delay(5000)
                viewModel.addCard()
            }
        }
        observes()
    }

    private fun countColumns(): Int {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 2
            Configuration.ORIENTATION_LANDSCAPE -> 4
            else -> 2
        }
    }

    private fun initList() {
        cardAdapter = CardAdapter { position -> viewModel.onDelete(position) }
        with(binding.cardList) {
            layoutManager = GridLayoutManager(requireContext(), countColumns)
            adapter = cardAdapter
            setHasFixedSize(true)
        }
    }

    private fun observes() {
        viewModel.cardLiveDataList.observe(viewLifecycleOwner) {
            cardAdapter.items = it
        }
    }
}