package com.craft.furniture.ui.home

import com.craft.furniture.data.NetworkBoundResource
import com.craft.furniture.data.local.db.DatabaseHelper
import com.craft.furniture.data.remote.ApiHelper
import com.craft.furniture.data.model.db.FurnitureListEntity
import com.craft.furniture.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
){

    fun getHomeData(): Flow<Result<List<FurnitureListEntity>>> {

        return object :NetworkBoundResource<List<FurnitureListEntity>, List<FurnitureListEntity>>(){

            override suspend fun saveCallResult(apiResponse: List<FurnitureListEntity>) {
                databaseHelper.insertFurnitureList(apiResponse)
            }
             //Decide when to fetch new data.
            override fun shouldFetch(): Boolean {
                  return true
            }

            override fun loadFromDb(): Flow<List<FurnitureListEntity>> {
                return databaseHelper.getFurnitureList()
            }
            override suspend fun getApiErrorMsg(): String? {
                TODO("Not yet implemented")
            }

            override suspend fun createApiCall(): List<FurnitureListEntity> {
                val id = databaseHelper.getRegistrationData().first().user_id
                return id?.let{
                    apiHelper.getFurnitureList(it).body()?.data?.home_screen_product!!
                }!!
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
}