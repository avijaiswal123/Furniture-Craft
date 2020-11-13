package com.craft.furniture.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.craft.furniture.R
import com.craft.furniture.constants.Preference
import com.craft.furniture.data.local.pref.SharedPref
import com.craft.furniture.ui.home.HomeActivity
import com.craft.furniture.ui.registration.RegistrationActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startHandler()

    }
    private fun startHandler() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToNextActivity()

        }, 3000)
    }

    private fun goToNextActivity() {
        SharedPref.getValue<Boolean>(Preference.IS_LOGIN)?.let {isLogin->
            if(isLogin) {
                startActivity(Intent(this, HomeActivity::class.java))
            }else startActivity(Intent(this, RegistrationActivity::class.java))

            finish()
        }

    }
}