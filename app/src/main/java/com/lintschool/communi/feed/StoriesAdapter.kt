package com.lintschool.communi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lintschool.communi.R
import com.lintschool.communi.stories.UserStories
import kotlinx.android.synthetic.main.add_story_rv_item.view.*
import kotlinx.android.synthetic.main.stories_rv_item.view.storyContainer
import kotlinx.android.synthetic.main.stories_rv_item.view.storyImage

class StoriesAdapter : ListAdapter<UserStories, RecyclerView.ViewHolder>(DiffUtilsCallback()) {

    lateinit var onItmClick: ((position: Int) -> Unit)
    lateinit var onAddMyStoryItmClick: ((view: View) -> Unit)

    companion object {
        var ADD_STORY_TYPE = 0
        var STORY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).addStory) ADD_STORY_TYPE else STORY
    }

    class DiffUtilsCallback : DiffUtil.ItemCallback<UserStories>() {
        override fun areItemsTheSame(oldItem: UserStories, newItem: UserStories): Boolean {
            return if (oldItem.addStory && newItem.addStory) {
                oldItem.userId == newItem.userId
            } else if (!oldItem.addStory && !newItem.addStory) {
                oldItem.userId == newItem.userId
            } else false
        }

        override fun areContentsTheSame(oldItem: UserStories, newItem: UserStories): Boolean {
            return if (oldItem.addStory && newItem.addStory) {
                oldItem == newItem
            } else if (!oldItem.addStory && !newItem.addStory) {
                oldItem == newItem
            } else false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView: View
        lateinit var viewHolder: RecyclerView.ViewHolder

        when (viewType) {
            ADD_STORY_TYPE -> {
                itemView = layoutInflater.inflate(R.layout.add_story_rv_item, parent, false)
                viewHolder = AddStoryViewHolder(itemView, onAddMyStoryItmClick)
            }
            STORY -> {
                itemView = layoutInflater.inflate(R.layout.stories_rv_item, parent, false)
                viewHolder = StoryViewHolder(itemView, onItmClick)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddStoryViewHolder -> holder.bind(getItem(position))
            is StoryViewHolder -> holder.bind(getItem(position))
        }
    }
}

class StoryViewHolder(itemView: View, var onItemClicked: ((position: Int) -> Unit)? = null) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: UserStories) {
        itemView.storyImage.setImageResource(itemData.stories[0].storyRes)

        itemView.storyContainer.setOnClickListener { onItemClicked?.invoke(adapterPosition) }
    }
}

class AddStoryViewHolder(
    itemView: View,
    var onAddStoryItemClicked: ((view: View) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: UserStories) {
        itemView.addStoryBtn.visibility = View.VISIBLE
        if (itemData.stories[0].storyRes != null) {
            itemView.storyImage.setImageResource(itemData.stories[0].storyRes)
        }
        itemView.storyContainer.setOnClickListener { onAddStoryItemClicked?.invoke(itemView) }
    }
}