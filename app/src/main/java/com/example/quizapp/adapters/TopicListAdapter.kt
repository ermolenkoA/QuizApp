package com.example.quizapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizapp.data.Topic
import com.example.quizapp.databinding.TopicsItemBinding

class TopicListAdapter(
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Topic>(){
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class TopicItemViewHolder(private val binding: TopicsItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Topic) {
            with(binding)
            {
                topicTextView.text = item.title
                Glide.with(context)
                    .load(item.image.toList())
                    .into(topicImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopicItemViewHolder(
            TopicsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TopicItemViewHolder).bind(differ.currentList[position])
    }

}