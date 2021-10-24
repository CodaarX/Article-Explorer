package com.decagon.edconnect.presentation.ui.viewcontrollers.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decagon.edconnect.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EdConnectMain)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        supportActionBar?.hide()

    }
}