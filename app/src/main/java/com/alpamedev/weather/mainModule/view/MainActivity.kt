package com.alpamedev.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.alpamedev.weather.R
import com.alpamedev.weather.databinding.ActivityMainBinding
import com.alpamedev.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpViewModel()
        setUpObservers()
    }

    private fun setUpViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setUpObservers() {
        viewModel.snackBarMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.getWeather(-11.88017985388384, -77.13366245457782)
        }
    }
}