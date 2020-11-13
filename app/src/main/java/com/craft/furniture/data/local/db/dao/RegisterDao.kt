package com.craft.furniture.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.craft.furniture.data.model.db.ReggisDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterDao {
    /**
     * Inserts [ReggisDataEntity] into the [ReggisDataEntity.REGISTER_TABLE] table.
     * Duplicate values are replaced in the table.
     * @param reggisEntityEntity Registration field's values.
     */
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegistrationData(reggisEntityEntity : ReggisDataEntity)

    /**
     * Fetches  Registration values from the [ReggisDataEntity.REGISTER_TABLE] where id
     * is zero. Here we are adding and updating data only at first row of table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${ReggisDataEntity.REGISTER_TABLE} WHERE id=0")
    fun getRegistrationData() : Flow<ReggisDataEntity>
}