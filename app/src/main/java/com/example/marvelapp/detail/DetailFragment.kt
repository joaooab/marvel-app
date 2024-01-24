package com.example.marvelapp.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.core.model.ComicDetail
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel { parametersOf(args.id) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTryAgain()
        collectUiState()
    }

    private fun setupTryAgain() {
        binding.includeErrorState.buttonRetry.setOnClickListener {
            viewModel.tryAgain()
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.flipper.displayedChild = when (state) {
                        DetailUiState.Error -> errorState()
                        DetailUiState.Loading -> loadingState()
                        is DetailUiState.Success -> successState(state.comic)
                    }
                }
            }
        }
    }

    private fun successState(comic: ComicDetail): Int = with(binding.includeSuccessState) {
        textAuthor.text = formatBoldLabel(R.string.label_author, comic.author)
        textPublication.text = formatBoldLabel(R.string.label_publication, comic.publicationDate)
        textPrice.text = formatBoldLabel(R.string.label_price, comic.price)
        textDescription.text = formatBoldLabel(R.string.label_description, comic.description)
        Glide.with(root)
            .load(comic.thumbnail)
            .fallback(R.drawable.ic_image_error)
            .into(imageDetail)

        toggleShimmerVisibility(false)
        return FLIPPER_CHILD_COMICS
    }

    private fun loadingState(): Int {
        toggleShimmerVisibility(true)
        return FLIPPER_CHILD_LOADING
    }

    private fun errorState(): Int {
        toggleShimmerVisibility(false)
        return FLIPPER_CHILD_ERROR
    }

    private fun formatBoldLabel(@StringRes labelResId: Int, content: String): SpannableString {
        val label = getString(labelResId)
        val fullText = "$label $content"
        val spannableString = SpannableString(fullText)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }

    private fun toggleShimmerVisibility(
        visibility: Boolean
    ) = with(binding.includeLoadingState.shimmerComics) {
        isVisible = visibility
        if (visibility) {
            startShimmer()
        } else {
            stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_COMICS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}