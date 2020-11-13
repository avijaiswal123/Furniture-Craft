package com.craft.furniture.ui.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.craft.furniture.R
import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.data.model.apis.RegistrationRequest
import com.craft.furniture.utils.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val repository: RegisterRepository
):ViewModel() {

    private val _registration = MutableLiveData<Result<ReggisDataEntity>>()
    val registration :LiveData<Result<ReggisDataEntity>> = _registration

    val name = MutableLiveData<String>()
    val mobile = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val errorName = MutableLiveData<Int>()
    val errorMobile = MutableLiveData<Int>()
    val errorEmail = MutableLiveData<Int>()
    val errorPassword = MutableLiveData<Int>()


    private fun isValid():Boolean{
        return when {
            name.value.isNullOrEmpty() -> {
                errorName.postValue(R.string.enter_valid_name)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.value?:"").matches() -> {
                errorEmail.postValue(R.string.enter_valid_email)
                false
            }
            password.value?.length?:0 < 4->{
                errorPassword.postValue(R.string.enter_valid_password)
                false
            }
            mobile.value?.length != 10 -> {
                errorMobile.postValue(R.string.enter_valid_mobile)
                false
            }
            else -> true
        }
    }

    fun onSignUpBtnClicked(){
        clearErrorField()
        if(isValid()) doRegistration(RegistrationRequest(
            first_name = name.value,
            email = email.value,
            password = password.value,
            phone = mobile.value
        ))
    }

    private fun clearErrorField() {
        errorName.postValue(null)
        errorEmail.postValue(null)
        errorMobile.postValue(null)
        errorPassword.postValue(null)
    }

    fun onResetBtnClicked(){
        clearErrorField()
        name.postValue(null)
        email.postValue(null)
        mobile.postValue(null)
        password.postValue(null)
    }

    private fun doRegistration(request :RegistrationRequest){
       // _registration.postValue(Result.Loading)
        viewModelScope.launch {
            repository.doRegistration(request).collect {
                 _registration.postValue(it)
            }
        }
    }
}