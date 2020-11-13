package com.craft.furniture.data.local.db

import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.data.model.db.FurnitureListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Helper implementation class to access all the Dao of Database  at single place
 * */
class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDatabase):DatabaseHelper{

    override suspend fun insertFurnitureList(furnitureListEntity: List<FurnitureListEntity>)  =
          appDatabase.getFurnitureDao().insertData(furnitureListEntity)


    override fun getFurnitureList(): Flow<List<FurnitureListEntity>> =
        appDatabase.getFurnitureDao().getFurnitureList()

    override suspend fun insertRegistrationData(reggisEntityEntity: ReggisDataEntity) =
          appDatabase.getRegisterDao().insertRegistrationData(reggisEntityEntity)


    override fun getRegistrationData(): Flow<ReggisDataEntity> =
         appDatabase.getRegisterDao().getRegistrationData()

}