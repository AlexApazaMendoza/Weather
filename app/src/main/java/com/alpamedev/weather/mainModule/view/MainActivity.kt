package com.alpamedev.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpamedev.weather.R
import com.alpamedev.weather.common.entities.ForecastX
import com.alpamedev.weather.common.utils.getDateTime
import com.alpamedev.weather.databinding.ActivityMainBinding
import com.alpamedev.weather.mainModule.view.adapters.ForecastAdapter
import com.alpamedev.weather.mainModule.view.listeners.OnClickItemListener
import com.alpamedev.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickItemListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ForecastAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpViewModel()
        setUpObservers()
        setUpAdapter()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvForecastWeather.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setUpAdapter() {
        adapter = ForecastAdapter(this)
    }

    private fun setUpViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setUpObservers() {
        viewModel.snackBarMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.forecast.observe(this) {
            adapter.submitList(it.list)
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.getWeather(-11.88017985388384, -77.13366245457782)
            viewModel.getForecastWeather(-11.88017985388384, -77.13366245457782)
        }
    }

    override fun onClickItem(item: ForecastX) {
        Snackbar.make(binding.root, getDateTime(item.dt.toLong()), Snackbar.LENGTH_LONG).show()
    }
}