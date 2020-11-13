package com.craft.furniture.data.local.db

import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.data.model.db.FurnitureListEntity
import kotlinx.coroutines.flow.Flow

/**
 * A Single Helper class that contains methods of all @Dao,s of Database
 *
 */
interface DatabaseHelper {
    suspend fun insertFurnitureList(furnitureListEntity : List<FurnitureListEntity>)

    fun getFurnitureList() : Flow<List<FurnitureListEntity>>

    suspend fun insertRegistrationData(reggisEntityEntity : ReggisDataEntity)

    fun getRegistrationData() : Flow<ReggisDataEntity>
}