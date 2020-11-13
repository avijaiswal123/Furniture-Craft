package com.craft.furniture.data.model.apis

import com.craft.furniture.data.model.db.ReggisDataEntity


data class RegisterResponse(
    val status:Boolean,
    val message:String?,
    val user_data: ReggisDataEntity?
)

