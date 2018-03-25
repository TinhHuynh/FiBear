package com.fibear.android.fibear.view.forBear.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fibear.android.fibear.R
import com.fibear.android.fibear.data.model.UserBlockDate
import org.jetbrains.anko.find

/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class RegisterBlockAdapter(private val context: Context,
                           private val userBlockDates: List<UserBlockDate>)
    : RecyclerView.Adapter<RegisterBlockAdapter.RegisterBlockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RegisterBlockViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_register_block, parent, false)
        return RegisterBlockViewHolder(view)
    }

    override fun getItemCount(): Int = userBlockDates.size

    override fun onBindViewHolder(holder: RegisterBlockViewHolder, position: Int) {
        holder.bindView(userBlockDates[position])
    }

    inner class RegisterBlockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener {

        private lateinit var txtBlockTitle: TextView
        private lateinit var cbSelected: CheckBox
        private lateinit var layoutPriceDescription: LinearLayout
        private lateinit var etPrice: EditText
        private lateinit var etDescription: EditText

        init {
            with(itemView) {
                txtBlockTitle = find(R.id.txt_block_title)
                cbSelected = find(R.id.cb_selected)
                layoutPriceDescription = find(R.id.layout_price_description)
                etPrice = find(R.id.et_price)
                etDescription = find(R.id.et_description)
                cbSelected.setOnCheckedChangeListener(this@RegisterBlockViewHolder)
            }
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            with(cbSelected) {
                layoutPriceDescription.visibility = if (isChecked) View.VISIBLE else View.GONE
                etDescription.visibility = View.VISIBLE
            }
        }

        fun bindView(userBlockDate: UserBlockDate) {
            with(userBlockDate) {
                txtBlockTitle.text = title()
                cbSelected.isChecked = block?.isCreated!!
                if (block.isCreated) {
                    cbSelected.isEnabled = false
                    layoutPriceDescription.visibility = View.VISIBLE
                    etPrice.isEnabled = false
                    etPrice.setText(price.toString())
                    description?.let {
                        etDescription.visibility = View.VISIBLE
                        etDescription.isEnabled = false
                        etDescription.setText(description)
                    }
                }
            }
        }

    }
}


