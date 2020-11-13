package com.craft.furniture.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.craft.furniture.data.model.db.FurnitureListEntity
import kotlinx.coroutines.flow.Flow
/**
 * DAO  for [FurnitureListEntity] of AppDatabase
 */
@Dao
interface FurnitureDao {
    /**
     * Inserts [FurnitureListEntity] into the [FurnitureListEntity.HOME_PAGE_TABLE] table.
     * Duplicate values are replaced in the table.
     * @param homeEntity List of FurnitureListEntity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(homeEntity : List<FurnitureListEntity>)


    /**
     * Fetches all the HomePageData from the [FurnitureListEntity.HOME_PAGE_TABLE] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${FurnitureListEntity.HOME_PAGE_TABLE}")
    fun getFurnitureList() : Flow<List<FurnitureListEntity>>
}