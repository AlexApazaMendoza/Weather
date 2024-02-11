package com.alpamedev.weather.mainModule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alpamedev.weather.R
import com.alpamedev.weather.databinding.ActivitySearchBinding
import com.alpamedev.weather.mainModule.viewModel.SearchViewModel

class SearchActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        setUpViewModel()
        setUpObservers()
        setUpAdapter()
        setUpRecyclerView()
    }

    private fun setUpAdapter() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
    }

    private fun setUpRecyclerView() {
        binding.rvList.apply {
            adapter = this@SearchActivity.adapter
        }
        binding.rvList.onItemClickListener = this
    }

    private fun setUpObservers() {
        viewModel.text.observe(this) {
            viewModel.searchPlace(it)
        }
        viewModel.result.observe(this) {
            viewModel.updateData(it)
        }
        viewModel.data.observe(this) {
            adapter.clear()
            adapter.addAll(it)
        }
    }

    private fun setUpViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.result.value?.get(position)?.let { place ->
            val state = if (place.state != null) "(${place.state})" else ""
            val name = "${place.name} $state [${place.country}]"
            val latitude = place.latitude
            val longitude = place.longitude
            val intentResult = Intent().putExtra("name", name) .putExtra("latitude", latitude).putExtra("longitude", longitude)
            setResult(RESULT_OK, intentResult)
            finish()
        }
    }
}