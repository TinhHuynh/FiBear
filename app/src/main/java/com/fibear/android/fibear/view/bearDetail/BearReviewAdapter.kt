package com.fibear.android.fibear.view.bearDetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.data.model.bear.ReviewsItem
import org.jetbrains.anko.find

/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class BearReviewAdapter(private val context: Context, private val reviewList: List<ReviewsItem>) : RecyclerView.Adapter<BearReviewAdapter.BearReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BearReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_review, null)
        return BearReviewViewHolder(view)
    }

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: BearReviewViewHolder, position: Int) {
        holder.bindView(reviewList[position])
    }

    inner class BearReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var txtReviewer: TextView
        private lateinit var ratingBar: RatingBar
        private lateinit var txtReview: TextView

        init {
            with(itemView){
                txtReviewer = find(R.id.txt_reviewer)
                ratingBar = find(R.id.ratingbar)
                txtReview = find(R.id.txt_review)
            }
        }

        fun bindView(reviewItem: ReviewsItem){
            with(reviewItem){
                txtReviewer.text = userReviewed?.username
                ratingBar.rating = rate!!.toFloat()
                txtReview.text = description
            }
        }

    }
}

