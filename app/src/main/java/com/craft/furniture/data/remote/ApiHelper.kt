package com.craft.furniture.data.remote

import com.craft.furniture.data.model.apis.*
import retrofit2.Response

/**
 * Helper interface contains all the methods to call Methods in [RestApis] class
 */
interface ApiHelper {
    suspend fun getFurnitureList(
        userId :String,
        pageNo: String = "1",
        perPage :String = "50"
    ): Response<FurnitureListResponse>

    suspend fun userRegistration(request: RegistrationRequest
    ): Response<RegisterResponse>


}