package com.getyourguide.assignment.presentation.ui.main.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.getyourguide.assignment.R
import com.getyourguide.assignment.databinding.FragmentReviewDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDetailFragment : Fragment(R.layout.fragment_review_detail) {

    private lateinit var binding: FragmentReviewDetailBinding
    private val args: ReviewDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReviewDetailBinding.bind(view)

        init()
        subscribe()
    }

    private fun init() {
        binding.apply {
        }
    }

    private fun subscribe() {
        val review = args.review
        binding.apply {
            Glide.with(requireContext())
                .load(review.author.photo)
                .circleCrop()
                .into(imageViewAvatar)
            textViewComment.text = review.message
            textViewDate.text = review.createdDate
            ratingBar.rating = review.rating
            textViewName.text = review.author.fullName
            textViewCountry.text = review.author.country ?: ""
        }
    }
}