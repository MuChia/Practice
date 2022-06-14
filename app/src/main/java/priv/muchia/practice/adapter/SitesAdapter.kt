package priv.muchia.practice.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.R
import priv.muchia.practice.WebActivity
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.model.SitesData

/**
 * FileName: ArticleAdapter
 * Author: MuChia
 * Date: 2022/6/13 21:10
 * Description:
 */
class SitesAdapter : ListAdapter<SitesData, SitesAdapter.ViewHolder>(SitesDiffCallback()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sitesTv: TextView = itemView.findViewById(R.id.home_sites_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_sites, parent, false))

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val position = holder.adapterPosition
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", getItem(position).link)
            context.startActivity(intent)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sitesTv.text = getItem(position).name
    }
}

private class SitesDiffCallback : DiffUtil.ItemCallback<SitesData>() {
    override fun areItemsTheSame(oldItem: SitesData, newItem: SitesData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SitesData, newItem: SitesData): Boolean {
        return oldItem == newItem
    }
}