package com.example.quizapp.adapters

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizapp.R
import com.example.quizapp.data.Topic
import com.example.quizapp.databinding.TopicsItemBinding
import org.w3c.dom.Text

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

        private val defaultTextColor = binding.topicTextView.currentTextColor
        private val selectTextColor = context!!.getColor(R.color.primary_light)

        fun bind(item: Topic) {
            with(binding)
            {
                root.setOnClickListener {
                    viewClicked(topicTextView, item.id)
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
                createUnselectAnimation(selectedView)
                createSelectAnimation(view)
                _isTopicSelected.value = true
                _selectedTopicId = topicId
                view
            } else {
                createUnselectAnimation(selectedView)
                _isTopicSelected.value = false
                _selectedTopicId = null
                null
            }

        }

        private fun createSelectAnimation(view: View?) {
            view?.let {
                ObjectAnimator.ofFloat(
                    getItemView(view),
                    View.SCALE_X,
                    1f, 1.1f).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
                ObjectAnimator.ofFloat(
                    getItemView(view),
                    View.SCALE_Y,
                    1f, 1.1f).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
                ObjectAnimator.ofArgb(
                    view as TextView,
                    "textColor",
                    defaultTextColor,
                    selectTextColor).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
            }
        }

        private fun createUnselectAnimation(view: View?) {
            view?.let {
                ObjectAnimator.ofFloat(
                    getItemView(view),
                    View.SCALE_X,
                    1.1f, 1f).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
                ObjectAnimator.ofFloat(
                    getItemView(view),
                    View.SCALE_Y,
                    1.1f, 1f).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
                ObjectAnimator.ofArgb(
                    view as TextView,
                    "textColor",
                    selectTextColor,
                    defaultTextColor).apply {
                    duration = 100
                    interpolator = LinearInterpolator()
                    start()
                }
            }
        }

        private fun getItemView(childView: View): View = childView.parent.parent.parent as View

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