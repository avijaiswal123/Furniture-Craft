package com.craft.furniture.ui.registration

import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.craft.furniture.R
import com.craft.furniture.constants.Preference
import com.craft.furniture.data.local.pref.SharedPref
import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.databinding.ActivityRegistrationBinding
import com.craft.furniture.ui.base.BaseActivity
import com.craft.furniture.ui.home.HomeActivity
import com.craft.furniture.utils.Result
import com.craft.furniture.utils.getResString
import com.craft.furniture.utils.showToast

class RegistrationActivity : BaseActivity<RegistrationViewModel,ActivityRegistrationBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeRegistration()
    }

    private fun observeRegistration() {
        getViewModel().registration.observe(this,{
            when(it){
                is Result.Loading->showProgress()
                is Result.Success->onSignUpSuccess(it.data)
                is Result.Error->showErrorMsg(it.msgOrId)
            }
        })
    }

    private fun onSignUpSuccess(data: ReggisDataEntity?) {
        data?.let {
            hideProgress()
            SharedPref.setValue(Preference.IS_LOGIN,true)
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }

    private fun showErrorMsg(msgOrId: Any) {
        hideProgress()
        when(msgOrId){
            is Int ->showToast(getResString(msgOrId))
            is String->showToast(msgOrId)
        }
    }


    override fun getBindingVariable() = BR.registerVM

    override fun getLayoutId()= R.layout.activity_registration

}