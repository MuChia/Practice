package priv.muchia.practice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.R
import priv.muchia.practice.model.ArticleData

/**
 * FileName: ArticleAdapter
 * Author: MuChia
 * Date: 2022/6/13 21:10
 * Description:
 */
class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val data = mutableListOf<ArticleData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTv: TextView = itemView.findViewById(R.id.home_article_tv)
    }

    fun setData(articleList: List<ArticleData>){
        data.clear()
        data.addAll(articleList)
        notifyDataSetChanged()
    }

    fun addData(articleList: List<ArticleData>){
        val position = data.size
        data.addAll(articleList)
        notifyItemRangeInserted(position,articleList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.articleTv.text = data[position].title
    }

    override fun getItemCount() = data.size
}
