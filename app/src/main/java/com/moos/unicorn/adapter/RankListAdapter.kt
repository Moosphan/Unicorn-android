package com.moos.unicorn.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moos.unicorn.R
import com.moos.unicorn.loader.ImageLoader
import com.moos.unicorn.loader.LoadOptions
import com.moos.unicorn.model.data.UnicornResult

/**
 * Generate adapter of rank list for unicorns.
 */
class RankListAdapter(private val rankData: List<UnicornResult>) : RecyclerView.Adapter<RankListAdapter.RankVH>() {

    private var listener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankVH {
        return RankVH(View.inflate(parent.context, R.layout.rv_unicorn_item_layout, null))
    }

    override fun getItemCount(): Int {
        return rankData.size
    }

    override fun onBindViewHolder(holder: RankVH, position: Int) {
        displayItem(holder.itemView, position)

    }

    @SuppressLint("SetTextI18n")
    private fun displayItem(view: View, position: Int) {
        val textViewRank = view.findViewById<TextView>(R.id.unicorn_item_rank)
        val textViewName = view.findViewById<TextView>(R.id.unicorn_item_name)
        val textViewDesc = view.findViewById<TextView>(R.id.unicorn_item_desc)
        val textViewTime = view.findViewById<TextView>(R.id.unicorn_item_time)
        val imageViewLogo = view.findViewById<ImageView>(R.id.unicorn_item_logo)

        textViewRank.text = "${position+1}"
        textViewName.text = rankData[position].name
        textViewTime.text = rankData[position].last_funding_on
        textViewDesc.text = rankData[position].country + " | " + rankData[position].category + " | " +
                rankData[position].post_money_val

        ImageLoader.INSTANCE
            .with {
                url = rankData[position].logo_url
                cacheStyle = LoadOptions.LoaderCacheStrategy.ALL
                displayStyle = LoadOptions.LoaderImageScaleType.CENTER_CROP
                angle = 30
            }.into(imageViewLogo)

        view.setOnClickListener {
            if (listener != null) {
                listener?.invoke(position)
            }
        }
    }

    /**
     * go scanning unicorn info details.
     */
    fun scanUnicornDetail(callback: (position: Int) -> Unit) {
        this.listener = callback
    }

    class RankVH(itemView: View): RecyclerView.ViewHolder(itemView)
}