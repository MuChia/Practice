package priv.muchia.practice.adapter

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.MyApplication.Companion.context
import priv.muchia.practice.R
import priv.muchia.practice.WebActivity
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.model.SearchResultData
import retrofit2.http.Url

/**
 * FileName: ArticleAdapter
 * Author: MuChia
 * Date: 2022/6/13 21:10
 * Description:
 */
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val data = mutableListOf<SearchResultData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTv: TextView = itemView.findViewById(R.id.home_article_tv)
    }

    fun setData(resultList: List<SearchResultData>) {
        data.clear()
        data.addAll(resultList)
        notifyDataSetChanged()
    }

    fun addData(resultList: List<SearchResultData>) {
        val position = data.size
        data.addAll(resultList)
        notifyItemRangeInserted(position, resultList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val holder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_article, parent, false))

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val position = holder.adapterPosition
//            val intent = Intent(context, WebActivity::class.java)
//            intent.putExtra("url", data[position].link)

            val url = Uri.parse(data[position].link)
            val intent = Intent(Intent.ACTION_VIEW, url)

            context.startActivity(intent)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.articleTv.text = Html.fromHtml(data[position].title, Html.FROM_HTML_OPTION_USE_CSS_COLORS)
    }

    override fun getItemCount() = data.size
}
