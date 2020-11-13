package com.craft.furniture.ui.registration

import com.craft.furniture.data.NetworkBoundResource
import com.craft.furniture.data.local.db.DatabaseHelper
import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.data.model.apis.RegisterResponse
import com.craft.furniture.data.model.apis.RegistrationRequest
import com.craft.furniture.data.remote.ApiHelper
import com.craft.furniture.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
class RegisterRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
) {
    /**
     * Do registration from network and store the User details which is in Response of Api in database.
     * At the end, data from persistence storage is fetched and emitted.
     */
    fun doRegistration(request: RegistrationRequest): Flow<Result<ReggisDataEntity>> {
        var apiResponse:RegisterResponse?  = null

        return object : NetworkBoundResource<ReggisDataEntity, ReggisDataEntity>(){

            override suspend fun saveCallResult(apiResponse: ReggisDataEntity) =
                databaseHelper.insertRegistrationData(apiResponse)

            override fun shouldFetch()= true

            override fun loadFromDb(): Flow<ReggisDataEntity> {
                return databaseHelper.getRegistrationData()
            }
            override suspend fun getApiErrorMsg()=
                apiResponse?.message

            override suspend fun createApiCall(): ReggisDataEntity? {
                apiResponse = apiHelper.userRegistration(request).body()
                return if(apiResponse?.status == true){
                    apiResponse?.user_data
                }else null
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
}