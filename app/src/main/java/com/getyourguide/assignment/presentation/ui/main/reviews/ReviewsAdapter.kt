package com.getyourguide.assignment.presentation.ui.main.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.getyourguide.assignment.databinding.ItemReviewBinding
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.presentation.util.CommonUtils
import com.getyourguide.assignment.presentation.util.DateTimeUtils

class ReviewsAdapter(val glide: RequestManager, val listener: OnClickListener) :
    PagingDataAdapter<Review, ReviewsAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }

    interface OnClickListener {
        fun onItemClick(review: Review)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class ViewHolder(
        private val binding: ItemReviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClick(review)
                }

                val avatarUrl = review.author.photo
                if (avatarUrl != null) {
                    glide
                        .load(avatarUrl)
                        .circleCrop()
                        .into(imageViewAvatar)
                    textViewNameLetter.text = ""
                } else {
                    glide.clear(imageViewAvatar)
                    imageViewAvatar.background = CommonUtils.getRandomColorCircularDrawable()
                    textViewNameLetter.text = review.author.fullName.substring(0, 1).uppercase()
                }

                textViewDate.text = review.created
                textViewComment.text = review.message
                ratingBar.rating = review.rating
                val nameAndCountry = "${review.author.fullName} - ${review.author.country ?: ""}"
                textViewViewer.text = nameAndCountry
                textViewDate.text = DateTimeUtils.getFormattedDateTime(review.created)
            }
        }

    }
}