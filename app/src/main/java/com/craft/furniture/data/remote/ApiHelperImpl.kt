package com.craft.furniture.data.remote

import com.craft.furniture.data.model.apis.RegistrationRequest
import javax.inject.Inject

/**
 * Single entry point to call all the methods of [RestApis] class.
 */
class ApiHelperImpl @Inject constructor(private val restApis: RestApis):ApiHelper {


    override suspend fun getFurnitureList(
        userId :String,
        pageNo: String,
        perPage :String
    ) = restApis.getFurnitureList(userId,pageNo,perPage)


    override suspend fun userRegistration(request: RegistrationRequest
    )= restApis.userRegistration(request)

}