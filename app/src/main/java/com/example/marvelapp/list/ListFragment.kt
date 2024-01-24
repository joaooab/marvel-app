package com.example.marvelapp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentListBinding
import com.example.marvelapp.list.ListAdapter.Companion.COMIC_VIEW_TYPE
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupTryAgain()
        collectComics()
        collectListState()
    }

    private fun collectComics() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.comicPagingData().collect { pagingData ->
                    listAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun collectListState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listAdapter.loadStateFlow.collectLatest { state ->
                    binding.flipper.displayedChild = when (state.refresh) {
                        is LoadState.Loading -> {
                            toggleShimmerVisibility(true)
                            FLIPPER_CHILD_LOADING
                        }

                        is LoadState.NotLoading -> {
                            toggleShimmerVisibility(false)
                            FLIPPER_CHILD_CHARACTERS
                        }

                        is LoadState.Error -> {
                            toggleShimmerVisibility(false)
                            FLIPPER_CHILD_ERROR
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        listAdapter = ListAdapter()

        with(binding.recyclerView) {
            scrollToPosition(0)
            setHasFixedSize(true)
            val concatAdapter = listAdapter.withLoadStateFooter(
                footer = ListLoadStateAdapter {
                    listAdapter.retry()
                }
            )
            layoutManager = setupLayoutManager(concatAdapter)
            adapter = concatAdapter
        }
    }

    private fun setupLayoutManager(concatAdapter: ConcatAdapter): GridLayoutManager {
        return GridLayoutManager(requireContext(), FULL_SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (concatAdapter.getItemViewType(position) == COMIC_VIEW_TYPE) {
                        FULL_SPAN_COUNT
                    } else {
                        SINGLE_SPAN_COUNT
                    }
                }
            }
        }
    }

    private fun setupTryAgain() {
        binding.includeErrorState.buttonRetry.setOnClickListener {
            listAdapter.refresh()
        }
    }

    private fun toggleShimmerVisibility(
        visibility: Boolean
    ) = with(binding.includeLoadingState.shimmerCharacters) {
        isVisible = visibility
        if (visibility) {
            startShimmer()
        } else {
            stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CHARACTERS = 1
        private const val FLIPPER_CHILD_ERROR = 2
        private const val FULL_SPAN_COUNT = 2
        private const val SINGLE_SPAN_COUNT = 1
    }
}