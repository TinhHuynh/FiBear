package com.fibear.android.fibear.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.data.model.User
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by TINH HUYNH on 3/22/2018.
 */
class BearListAdapter(private val context: Context, var bearList: List<User>, private val listener: OnBearItemClickedListener) : RecyclerView.Adapter<BearListAdapter.BearViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BearViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bear_list, parent, false)
        return BearViewHolder(view)
    }

    override fun getItemCount(): Int = bearList.size

    override fun onBindViewHolder(holder: BearViewHolder?, position: Int) {
        val user = bearList[position]
        holder?.bindView(user)
    }

    inner class BearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        private lateinit var imageAvatar: ImageView
        private lateinit var txtNameAge: TextView
        private lateinit var txtDescription: TextView
        private lateinit var txtRating: TextView
        private lateinit var layout: CardView

        init {
            with(itemView) {
                imageAvatar = find(R.id.image_avatar)
                txtNameAge = find(R.id.txt_name_age)
                txtDescription = find(R.id.txt_description)
                txtRating = find(R.id.txt_rating)
                layout = find(R.id.layout_item_bear)
            }
        }

        override fun onClick(v: View?) {
            listener.onBearClicked(bearList[adapterPosition])
//            context.startActivity(BearDetailActivity.getIntent(context, bearList[adapterPosition]))
        }

        @SuppressLint("SetTextI18n")
        fun bindView(bear: User) {
            with(bear) {
                Picasso.with(context)
                        .load(profile?.avatar)
                        .placeholder(R.drawable.ic_default_user)
                        .into(imageAvatar)

                txtNameAge.text = title()
                txtDescription.text = profile?.description
                txtRating.text = rating()
                layout.setOnClickListener(this@BearViewHolder)
            }

        }

    }
}

interface OnBearItemClickedListener {
    fun onBearClicked(bear: User)
}


