package com.joaoibarra.broker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joaoibarra.broker.R
import com.joaoibarra.broker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this@MainActivity
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}