package com.m.phanat.coinranking.ui.cryptolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.m.phanat.coinranking.R
import com.m.phanat.coinranking.databinding.ItemCryptoBinding
import com.m.phanat.coinranking.ui.models.CryptoItemResponse

class CryptoListAdapter(private val cryptoClickListener: (cryptoItem: CryptoItemResponse) -> Unit) :
    PagingDataAdapter<CryptoItemResponse, CryptoListAdapter.ViewHolder>(CRYPTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCryptoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemCryptoBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val cryptoItem = getItem(absoluteAdapterPosition)
                cryptoItem?.let { cryptoClickListener(it) }
            }
        }

        fun bind(item: CryptoItemResponse) {
            binding.tvSymbol.text = item.symbol
            binding.tvVolume.text = context.getString(R.string.volume,item.volumeDisplay)
            binding.tvPrice.text = item.priceDisplayNoComma
            binding.tvPriceDollars.text = context.getString(R.string.dollar_price,item.priceDisplay)
            binding.tvPercentChange.background = if (item.percentChange24h > 0 ) ContextCompat.getDrawable(context,R.drawable.border_green)
            else ContextCompat.getDrawable(context,R.drawable.border_red)
        }
    }

    companion object {
        private val CRYPTO_COMPARATOR = object : DiffUtil.ItemCallback<CryptoItemResponse>() {
            override fun areItemsTheSame(oldItem: CryptoItemResponse, newItem: CryptoItemResponse): Boolean {
                return oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: CryptoItemResponse, newItem: CryptoItemResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}