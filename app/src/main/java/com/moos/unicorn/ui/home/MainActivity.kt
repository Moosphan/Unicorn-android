package com.moos.unicorn.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.moos.unicorn.R
import com.moos.unicorn.adapter.RankListAdapter
import com.moos.unicorn.model.data.UnicornRankList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rankViewModel: RankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        unicornRankRefreshLayout.setOnRefreshListener {
            rankViewModel.fetchRankData()
        }

    }

    private fun initData() {
        rankViewModel = ViewModelProviders.of(this).get(RankViewModel::class.java)
        rankViewModel.fetchRankData()
        rankViewModel.mRankData.observe(this, Observer {
            Log.e("MainActivity", "该显示列表了》》》》")
            displayUI(it)
        })

        rankViewModel.mError.observe(this, Observer {
            displayError(it)
        })
    }

    private fun refresh() {
        //unicornRankRefreshLayout.isRefreshing = true

    }

    private fun displayError(error: String?) {

    }

    private fun displayUI(rankData: UnicornRankList) {
        unicornRankRefreshLayout.isRefreshing = false
        unicornRankRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RankListAdapter(rankData.entities)
        unicornRankRecyclerView.adapter = adapter
        adapter.scanUnicornDetail {
            // go detail
            Toast.makeText(this, "${it+1}", Toast.LENGTH_SHORT).show()
        }
    }
}
