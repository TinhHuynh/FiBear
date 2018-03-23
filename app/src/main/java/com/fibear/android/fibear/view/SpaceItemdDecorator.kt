package com.fibear.android.fibear.view

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class SpaceItemdDecorator(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = space
        } else {
            outRect.top = 0
        }

        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = space / 2
        } else {
            outRect.left = space / 2

        }

    }
}