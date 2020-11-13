package com.craft.furniture.ui.home

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.craft.furniture.data.model.db.FurnitureListEntity
import com.craft.furniture.databinding.AdapterHomeBinding
import com.craft.furniture.utils.FurnitureListDiffCallback

class HomeAdapter:
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val furnitureList= ArrayList<FurnitureListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        HomeViewHolder(AdapterHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
    fun setData(mFurnitureList: List<FurnitureListEntity>){
        val diffCallback = FurnitureListDiffCallback(
            furnitureList,
            mFurnitureList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        furnitureList.clear()
        furnitureList.addAll(mFurnitureList)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = furnitureList[position]
        holder.binding.furniture = item
        holder.bind()
    }

    override fun getItemCount()= furnitureList.size

     class HomeViewHolder(val binding: AdapterHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind() {
            binding.priceTv.paintFlags =
                binding.priceTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
     }

}