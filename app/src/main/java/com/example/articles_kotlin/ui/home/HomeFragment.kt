package com.example.articles_kotlin.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articles_kotlin.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAllArticles()
        homeViewModel.articlesLiveData.observe(this, Observer { articles ->
            list_recycler_view.apply {
                layoutManager = LinearLayoutManager(activity)
                val customAdapter = ArticlesListAdapter(articles)
                customAdapter.onItemClick = {article ->
                    openURL(article.link)
                    Log.d("TAG", article.link)
                }
                adapter = customAdapter
                textView.text = ""


            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        textView = root.findViewById(R.id.text_home)


        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }

    private fun openURL(url: String) {
        val uris = Uri.parse(url)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        context?.startActivity(intents)
    }
}