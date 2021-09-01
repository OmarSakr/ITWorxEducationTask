package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ItemCategoriesBinding
import java.util.ArrayList

class OnBoardingAdapter(
    context: Context, var chosenList: MutableList<String>
) : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    var itemsList: ArrayList<String>? = null
    var layoutInflater: LayoutInflater? = null

    init {
        itemsList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategory.text = itemsList?.get(position)
        holder.binding.cbCategory.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.e("listBeforeAdd",chosenList.toString())
                chosenList.add(itemsList!![position])
                Log.e("listAfterAdd",chosenList.toString())

            } else if (!isChecked) {
                Log.e("listBeforeRemove",chosenList.toString())
                chosenList.remove(itemsList!![position])
                Log.e("listAfterRemove",chosenList.toString())

            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList!!.size
    }

    fun addAll(data: List<String>) {
        itemsList!!.clear()
        itemsList!!.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)


}