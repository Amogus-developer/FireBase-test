package com.example.mychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mychat.databinding.ActivityLookBinding

class LookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val description = intent.getStringExtra("description")
        val theme = intent.getStringExtra("theme")
        if (description != null) binding.textDescription.text = description
        if (theme != null) binding.textTheme.text = theme
    }
}