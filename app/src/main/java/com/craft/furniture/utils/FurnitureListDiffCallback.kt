package com.craft.furniture.utils

import androidx.recyclerview.widget.DiffUtil
import com.craft.furniture.data.model.db.FurnitureListEntity

class FurnitureListDiffCallback(
    private val oldList: List<FurnitureListEntity>,
    private val newList: List<FurnitureListEntity>

) : DiffUtil.Callback() {

    override fun getOldListSize()=oldList.size

    override fun getNewListSize()= newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}