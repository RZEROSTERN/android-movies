package com.rzerocorp.androidmovies.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rzerocorp.androidmovies.R
import com.rzerocorp.androidmovies.SingleMovieActivity
import com.rzerocorp.androidmovies.adapters.MoviesAdapter.ViewHolder
import com.rzerocorp.androidmovies.models.MovieItem

class MoviesAdapter(items: ArrayList<MovieItem>): RecyclerView.Adapter<ViewHolder>() {
    private var items: ArrayList<MovieItem> = items

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.title.text = items[i].title
        viewHolder.movie_id = items[i].id
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var movie_id: Int = 0

        init {
            this.title = itemView.findViewById(R.id.txt_title)
            itemView.setOnClickListener {
                var i = Intent(itemView.context, SingleMovieActivity::class.java)
                i.putExtra("movie_id", movie_id)
                itemView.context.startActivity(i)
            }
        }
    }
}