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

class TopicListAdapter(private val context: Context?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _isTopicSelected = MutableLiveData(false)
    private var _selectedTopicId: Long? = 0
    private var selectedView: View? = null

    val isTopicSelected: LiveData<Boolean> get() = _isTopicSelected
    val selectedTopicId: Long? get() = _selectedTopicId

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
                root.setOnClickListener {
                    viewClicked(it, item.id)
                }
                topicTextView.text = item.title
                context?.let {
                    Glide.with(it)
                        .load(item.image)
                        .placeholder(R.drawable.error_img)
                        .into(topicImageView)
                }
            }
        }

        private fun viewClicked(view: View?, topicId: Long) {
            selectedView = if (selectedView != view) {
                selectedView?.setBackgroundColor(Color.WHITE)
                view?.setBackgroundColor(Color.LTGRAY)
                _isTopicSelected.value = true
                _selectedTopicId = topicId
                view
            } else {
                view?.setBackgroundColor(Color.WHITE)
                _isTopicSelected.value = false
                _selectedTopicId = null
                null
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