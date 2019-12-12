package com.example.articles_kotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.articles_kotlin.R
import com.example.articles_kotlin.models.Article


class ArticlesListAdapter (private val list: List<Article>): RecyclerView.Adapter<ArticlesListAdapter.ArticleViewHolder>() {

    var onItemClick: ((Article) -> Int)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = list[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.article_cell, parent, false)) {

        private var mTitleView: TextView? = null
        private var mDescriptionView: TextView? = null
        private var mAuthorView: TextView? = null
        private var mDateView: TextView? = null

        init {
            mTitleView = itemView.findViewById(R.id.list_title)
            mDescriptionView = itemView.findViewById(R.id.list_description)
            mAuthorView = itemView.findViewById(R.id.list_author)
            mDateView = itemView.findViewById(R.id.list_date)

            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }

        fun bind(article: Article) {
            mTitleView?.text = article.title
            mDescriptionView?.text = article.description
            mAuthorView?.text = article.author
            mDateView?.text = article.article_date
        }
    }
}

