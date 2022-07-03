package priv.muchia.practice.adapter

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import priv.muchia.practice.MyApplication.Companion.context
import priv.muchia.practice.R
import priv.muchia.practice.WebActivity
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.toast
import retrofit2.http.Url

/**
 * FileName: ArticleAdapter
 * Author: MuChia
 * Date: 2022/6/13 21:10
 * Description:
 */
typealias ItemClickListener = (view: View, position: Int) -> Unit

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val data = mutableListOf<ArticleData>()
    private var itemClickListener: ItemClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTv: TextView = itemView.findViewById(R.id.home_article_tv)
        val articleCollect: ImageView = itemView.findViewById(R.id.home_collect_img)
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    fun getData() = data

    fun setData(articleList: List<ArticleData>) {
        data.clear()
        data.addAll(articleList)
        notifyDataSetChanged()
    }

    fun addData(articleList: List<ArticleData>) {
        val position = data.size
        data.addAll(articleList)
        notifyItemRangeInserted(position, articleList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_article, parent, false))

        holder.articleTv.setOnClickListener {
            val context = holder.itemView.context
            val position = holder.adapterPosition
//            val intent = Intent(context, WebActivity::class.java)
//            intent.putExtra("url", data[position].link)
            val url = Uri.parse(data[position].link)
            val intent = Intent(Intent.ACTION_VIEW, url)

            context.startActivity(intent)
        }

        holder.articleCollect.setOnClickListener { view ->
            itemClickListener?.let { it(view, holder.adapterPosition) }
        }


        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.articleTv.text = data[position].title
        holder.articleCollect.isSelected = data[position].collect
    }

    override fun getItemCount() = data.size


}
