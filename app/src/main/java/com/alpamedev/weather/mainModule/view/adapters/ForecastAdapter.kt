package com.alpamedev.weather.mainModule.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.weather.R
import com.alpamedev.weather.common.entities.ForecastX
import com.alpamedev.weather.databinding.ItemWeatherLayoutBinding
import com.alpamedev.weather.mainModule.view.listeners.OnClickItemListener

class ForecastAdapter(val listener: OnClickItemListener): ListAdapter<ForecastX, RecyclerView.ViewHolder>(ForecastDiffUtil()) {

    private var timezone: Int? = null
    inner class ForecastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemWeatherLayoutBinding>(view)
        fun setListener(forecast: ForecastX) {
            binding?.root?.setOnClickListener {
                listener.onClickItem(forecast, timezone)
            }
        }
    }

    class ForecastDiffUtil: DiffUtil.ItemCallback<ForecastX>() {
        override fun areItemsTheSame(oldItem: ForecastX, newItem: ForecastX): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: ForecastX, newItem: ForecastX): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)
        with(holder as ForecastViewHolder) {
            binding?.forecast = forecast
            if (timezone != null) binding?.timezone = timezone as Int
            binding?.executePendingBindings()

            setListener(forecast)
        }
    }

    fun setTimeZone(timezone: Int) {
        this.timezone = timezone
    }
}