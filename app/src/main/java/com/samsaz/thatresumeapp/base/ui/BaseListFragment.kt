package com.samsaz.thatresumeapp.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.samsaz.thatresumeapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.fragment_base_list.view.*

abstract class BaseListFragment<T> : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: BaseRecyclerAdapter<T, out RecyclerView.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base_list, container, false)
        adapter = getAdapter()
        with(view) {
            rvList.layoutManager = getLayoutManager()
            rvList.adapter = adapter
            refreshLayout.setOnRefreshListener(this@BaseListFragment)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getListViewStateProvider()?.apply {
            listData.observe(viewLifecycleOwner, Observer {
                setData(it)
            })
            loadingState.observe(viewLifecycleOwner, Observer {
                setLoadingState(it)
            })
        }
    }

    private fun setData(data: List<T>?) {
        data ?: return

        adapter.setItems(data)
    }

    private fun setLoadingState(state: ViewLoadingState?) {
        state ?: return

        when (state) {
            is ViewLoadingState.Loading -> refreshLayout.isRefreshing = false
            is ViewLoadingState.Success -> refreshLayout.isRefreshing = false
            is ViewLoadingState.Error -> {
                refreshLayout.isRefreshing = false
                val view = view
                if (view != null && state.message != null)
                    Snackbar.make(view, state.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.tryAgain) {
                            onRefresh()
                        }.show()
            }
        }
    }

    override fun onRefresh() {
        getListViewStateProvider()?.refresh()
    }

    protected fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    abstract fun getListViewStateProvider(): ListViewStateProvider<T>?
    abstract fun getAdapter(): BaseRecyclerAdapter<T, out RecyclerView.ViewHolder>
}