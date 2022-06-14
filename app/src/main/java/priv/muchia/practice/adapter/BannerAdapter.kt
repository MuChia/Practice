package priv.muchia.practice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import priv.muchia.practice.MyApplication.Companion.context
import priv.muchia.practice.R
import priv.muchia.practice.model.BannerData

/**
 * FileName: BannerAdapter
 * Author: MuChia
 * Date: 2022/6/13 14:06
 * Description:
 */
class BannerAdapter() :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    private var banners = emptyList<BannerData>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: AppCompatImageView = view.findViewById(R.id.home_banner_img)
    }

    fun setData(data: List<BannerData>){
        banners = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(banners[position].imagePath)
            .into(holder.img)
    }

    override fun getItemCount() = banners.size
}