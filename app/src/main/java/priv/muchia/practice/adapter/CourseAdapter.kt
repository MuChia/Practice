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
import com.bumptech.glide.Glide
import priv.muchia.practice.MyApplication.Companion.context
import priv.muchia.practice.R
import priv.muchia.practice.WebActivity
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.model.TreeData
import priv.muchia.practice.toast
import retrofit2.http.Url

/**
 * FileName: CourseAdapter
 * Author: MuChia
 * Date: 2022/6/13 21:10
 * Description:
 */
class CourseAdapter : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    private val data = mutableListOf<TreeData>()
    private var itemClickListener: ItemClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImg: ImageView = itemView.findViewById(R.id.course_cover_img)
        val titleTv: TextView = itemView.findViewById(R.id.course_title_tv)
        val authorTv: TextView = itemView.findViewById(R.id.course_author_tv)
        val introTv: TextView = itemView.findViewById(R.id.course_intro_tv)
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    fun getData() = data

    fun setData(courseList: List<TreeData>) {
        data.clear()
        data.addAll(courseList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false))

        holder.itemView.setOnClickListener { view ->
            itemClickListener?.let { it(view, holder.adapterPosition) }
        }


        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = data[position]

        holder.titleTv.text = course.name
        holder.authorTv.text = course.author
        holder.introTv.text = course.desc
        Glide.with(holder.itemView.context)
            .load(course.cover)
            .into(holder.coverImg)
    }

    override fun getItemCount() = data.size


}
