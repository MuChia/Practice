package priv.muchia.practice.ui.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import priv.muchia.practice.R
import priv.muchia.practice.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.touchEventTv.setOnClickListener {
            startActivity(Intent(this, TouchEventActivity::class.java))
        }
        binding.countDownTv.setOnClickListener {
            startActivity(Intent(this, CountDownActivity::class.java))
        }
    }
}