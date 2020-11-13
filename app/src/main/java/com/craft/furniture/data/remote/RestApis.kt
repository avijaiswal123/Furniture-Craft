package com.craft.furniture.data.remote

import com.craft.furniture.constants.ApiConstants.HOME_PAGE
import com.craft.furniture.constants.ApiConstants.PAGE_NO
import com.craft.furniture.constants.ApiConstants.PER_PAGE
import com.craft.furniture.constants.ApiConstants.SIGN_UP
import com.craft.furniture.constants.ApiConstants.USER_ID
import com.craft.furniture.data.model.apis.*
import retrofit2.Response
import retrofit2.http.*

interface RestApis {

    @POST(SIGN_UP)
    suspend fun userRegistration(
        @Body request: RegistrationRequest,
        ): Response<RegisterResponse>


    @FormUrlEncoded
    @POST(HOME_PAGE)
    suspend fun getFurnitureList(
        @Field(USER_ID) userId: String,
        @Field(PAGE_NO) pageNo: String,
        @Field(PER_PAGE) perPage: String
    ): Response<FurnitureListResponse>



}