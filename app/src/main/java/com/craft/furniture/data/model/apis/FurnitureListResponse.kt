package com.craft.furniture.data.model.apis

import com.craft.furniture.data.model.db.FurnitureListEntity

data class FurnitureListResponse(
    val status: Int,
    val message: String?,
    val data: FurnitureData?
)


data class FurnitureData(
    val home_screen_product :List<FurnitureListEntity>
)
