package com.alpamedev.weather.mainModule.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
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
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.extras?.let {
                val latitude = it.getDouble("latitude")
                val longitude = it.getDouble("longitude")
                loadData(latitude, longitude)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpViewModel()
        setUpObservers()
        setUpAdapter()
        setUpRecyclerView()
        loadData()
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
            adapter.setTimeZone(it.city.timezone)
            adapter.submitList(it.list)
        }
        viewModel.onSearchPlaceClick.observe(this) {
            if (it == true) {
                val intent = Intent(this, SearchActivity::class.java)
                resultLauncher.launch(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //loadData()
    }

    private fun loadData(latitude: Double = -11.88017985388384, longitude: Double = -77.13366245457782) {
        lifecycleScope.launch {
            viewModel.getWeather(latitude, longitude)
            viewModel.getForecastWeather(latitude, longitude)
        }
    }

    override fun onClickItem(item: ForecastX, timeZone: Int?) {
        Snackbar.make(binding.root, getDateTime(item.dt.toLong(), timeZone), Snackbar.LENGTH_LONG).show()
    }
}