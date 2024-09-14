package com.example.shopapp.Activity

import android.content.Intent
import android.os.Bundle
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        binding =ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startActivity(Intent(this@IntroActivity,MainActivity::class.java))
        }
//        enableEdgeToEdge()
    }
}