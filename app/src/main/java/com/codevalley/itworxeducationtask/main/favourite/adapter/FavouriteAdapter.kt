package com.codevalley.itworxeducationtask.main.favourite.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ItemHomeBinding
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import com.codevalley.itworxeducationtask.utils.ParentClass
import java.util.ArrayList

class FavouriteAdapter(val context: Context) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    var itemList: ArrayList<Article>? = null
    var layoutInflater: LayoutInflater? = null

    init {
        itemList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = itemList!![position].title
        holder.binding.tvDate.text = itemList!![position].publishedAt
        holder.binding.tvSourceNewsPaper.text = itemList!![position].source.name
        holder.binding.tvDescription.text = itemList!![position].description
        ParentClass.LoadImageWithPicasso(
            itemList!![position].urlToImage,
            context,
            holder.binding.ivProductImage
        )
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(itemList!![position].url))
            context.startActivity(browserIntent)
        }
    }


    override fun getItemCount(): Int {
        return itemList!!.size
    }

    fun addAll(data: List<Article>?) {
        itemList!!.clear()
        itemList!!.addAll(data!!)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)


}