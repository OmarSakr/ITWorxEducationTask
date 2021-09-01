package com.codevalley.itworxeducationtask.main.home.adapter

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ItemHomeBinding
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import com.codevalley.itworxeducationtask.utils.ParentClass

class HomeAdapter(var context: Context) :
    PagingDataAdapter<Article, HomeAdapter.ViewHolder>(DataDifferntiator) {
    var layoutInflater: LayoutInflater? = null

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum = getItem(position)
        holder.binding.tvTitle.text = datum?.title
        holder.binding.tvDate.text = datum?.publishedAt
        holder.binding.tvSourceNewsPaper.text = datum?.source?.name
        holder.binding.tvDescription.text = datum?.description
        ParentClass.LoadImageWithPicasso(datum?.urlToImage, context, holder.binding.ivProductImage)
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(datum?.url))
            context.startActivity(browserIntent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    class ViewHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)
    object DataDifferntiator : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }


}