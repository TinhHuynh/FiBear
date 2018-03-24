package com.fibear.android.fibear.view.bearDetail.adapters

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.data.model.bear.block.UserBlockDatesItem
import org.jetbrains.anko.find


/**
 * Created by TINH HUYNH on 3/24/2018.
 */

class BearBlockAdapter(private val context: Context,
                       private val blocks: List<UserBlockDatesItem>,
                       private val listener: OnBlockClickedListener)
    : RecyclerView.Adapter<BearBlockAdapter.BearBlockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BearBlockViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_block, parent, false)
        return BearBlockViewHolder(view)
    }

    override fun getItemCount(): Int = blocks.size

    override fun onBindViewHolder(holder: BearBlockViewHolder, position: Int) {
        holder.bindView(blocks[position])
    }

    inner class BearBlockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var layoutBlock: LinearLayout
        private lateinit var layoutDescription: LinearLayout
        private lateinit var txtBlockTitle: TextView
        private lateinit var txtBlockPrice: TextView
        private lateinit var txtBlockDescription: TextView

        init {
            with(itemView) {
                layoutBlock = find(R.id.layout_block)
                layoutDescription = find(R.id.layout_block_description)
                txtBlockTitle = find(R.id.txt_block_title)
                txtBlockPrice = find(R.id.txt_block_price)
                txtBlockDescription = find(R.id.txt_block_description)

                layoutBlock.setOnClickListener(this@BearBlockViewHolder)
            }
        }

        override fun onClick(v: View?) {
            listener.onBlockClicked(blocks[adapterPosition])
        }

        @SuppressLint("SetTextI18n")
        fun bindView(block: UserBlockDatesItem) {
            with(block) {
                txtBlockTitle.text = title()
                txtBlockPrice.text = "$price VND"
                description?.let {
                    layoutDescription.visibility = View.VISIBLE
                    txtBlockDescription.text = it
                }
            }
        }

    }
}

interface OnBlockClickedListener {
    fun onBlockClicked(block: UserBlockDatesItem)
}
