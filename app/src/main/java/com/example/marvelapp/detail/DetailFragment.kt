package com.example.marvelapp.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
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

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiState()
        Log.i("ID", "onViewCreated: ${args.id}")
        viewModel.load(args.id)
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        DetailUiState.Error -> errorState()
                        DetailUiState.Loading -> loadingState()
                        is DetailUiState.Success -> successState(state.comic)
                    }
                }
            }
        }
    }

    private fun successState(comic: ComicDetail) = with(binding) {
        textAuthor.text = formatBoldLabel(R.string.label_author, comic.author)
        textPublication.text = formatBoldLabel(R.string.label_publication, comic.publicationDate)
        textPrice.text = formatBoldLabel(R.string.label_price, comic.price)
        textDescription.text = formatBoldLabel(R.string.label_description, comic.description)
        Glide.with(root)
            .load(comic.thumbnail)
            .fallback(R.drawable.ic_image_error)
            .into(imageDetail)
    }

    private fun loadingState() {

    }

    private fun errorState() {

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
}