package com.example.quizapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizapp.R
import com.example.quizapp.data.Topic
import com.example.quizapp.databinding.TopicsItemBinding

class TopicListAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _isTopicSelected = MutableLiveData(false)
    val isTopicSelected: LiveData<Boolean> get() = _isTopicSelected

    private var selectView: View? = null

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
            binding.root.setOnClickListener {
                selectView?.setBackgroundColor(Color.WHITE)
                selectView = if (selectView != it) {
                    selectView?.setBackgroundColor(Color.WHITE)
                    it.setBackgroundColor(Color.LTGRAY)
                    _isTopicSelected.value = true
                    it
                } else {
                    _isTopicSelected.value = false
                    null
                }
            }
            with(binding)
            {
                topicTextView.text = item.title
                context?.let {
                    Glide.with(it)
                        .load(item.image)
                        .into(topicImageView)
                }
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