package com.craft.furniture.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.craft.furniture.R
import com.craft.furniture.utils.NoInternetException
import com.craft.furniture.utils.Result
import kotlinx.coroutines.flow.*
import retrofit2.Response


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * [RESULT_TYPE] represents the type for database.
 * [REQUEST_TYPE] represents the type for network.
 * </REQUEST_TYPE></RESULT_TYPE>
 */

abstract class NetworkBoundResource<RESULT_TYPE, REQUEST_TYPE> {
    fun asFlow() = flow<Result<RESULT_TYPE>> {

        //First emitting loading state
        emit(Result.Loading)

        try {
             //Emit local data
            emit(Result.Success(loadFromDb().first()))


            //Check if new data should be loaded from remote source
            if(shouldFetch()) {
                val apiResponse = createApiCall()
                apiResponse?.let {
                    //save api response in persistence storage first
                    saveCallResult(it)

                    //Emit the data from persistence storage after saving.
                    emitAll(loadFromDb().map {r->
                        Result.Success(r)
                    })
                    return@flow
                }
                emit(Result.Error(
                    getApiErrorMsg()?:R.string.something_went_wrong)
                )
            }

        }catch (i:NoInternetException){
            emit(Result.Error(R.string.internet_exception_msg))
        }catch (e:Exception){
            emit(Result.Error(R.string.api_exception_msg))
        }
    }




    /**
     * Saves api response from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveCallResult(apiResponse: REQUEST_TYPE)

    /**
     * when to fetch refresh data from remote source
     */
    @MainThread
    protected abstract fun shouldFetch(): Boolean

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun loadFromDb(): Flow<RESULT_TYPE>

    /**
     * Fetches [Response] from the remote network.
     */
    @MainThread
    protected abstract suspend fun createApiCall(): REQUEST_TYPE?

    /**
     * Get error message from api
     */
    @MainThread
    protected abstract suspend fun getApiErrorMsg():String?

    //@WorkerThread
    // protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body
}