package com.getyourguide.assignment.presentation.ui.main.reviews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getyourguide.assignment.R
import com.getyourguide.assignment.databinding.FragmentReviewsBinding
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.presentation.adapter.CustomLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewsFragment : Fragment(R.layout.fragment_reviews), ReviewsAdapter.OnClickListener {

    private val viewModel: ReviewsViewModel by viewModels()
    private lateinit var binding: FragmentReviewsBinding
    private lateinit var reviewsAdapter: ReviewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReviewsBinding.bind(view)

        init()
        subscribe()
    }

    private fun init() {
        reviewsAdapter = ReviewsAdapter(Glide.with(requireContext()), this)

        setUpAdapter()
    }

    private fun subscribe() {
        binding.apply {

            lifecycleScope.launchWhenResumed {
                viewModel.reviews.collectLatest {
                    reviewsAdapter.submitData(it)
                }
            }
        }
    }


    override fun onItemClick(review: Review) {
        val action = ReviewsFragmentDirections.actionReviewsToReviewDetail(review)
        findNavController().navigate(action)
    }

    private fun setUpAdapter() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL))
            reviewsAdapter.addLoadStateListener { loadState ->

                if (loadState.mediator?.refresh is LoadState.Loading) {
                    if (reviewsAdapter.snapshot().isEmpty()) {
                        binding.progressBar.isVisible = true
                    }
                } else {
                    binding.progressBar.isVisible = false

                    val error = when {
                        loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                        loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                        loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                        else -> null
                    }
                    error?.let {
                        if (reviewsAdapter.snapshot().isEmpty()) {
                            Toast.makeText(requireContext(),
                                it.error.localizedMessage,
                                Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
            adapter = reviewsAdapter.withLoadStateFooter(
                footer = CustomLoadStateAdapter { reviewsAdapter.retry() }
            )
        }
    }
}