package com.samsaz.thatresumeapp.skills

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.base.ui.BaseListFragment
import com.samsaz.thatresumeapp.base.ui.BaseRecyclerAdapter
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.model.Skill
import javax.inject.Inject

class SkillFragment : BaseListFragment<Skill>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SkillViewModel
    private val adapter = SkillAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SkillViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
    }

    override fun getListViewStateProvider(): ListViewStateProvider<Skill>? {
        return viewModel
    }

    override fun getAdapter(): BaseRecyclerAdapter<Skill, out RecyclerView.ViewHolder> {
        return adapter
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menu == null || inflater == null) {
            return
        }

        inflater.inflate(R.menu.skill_menu, menu)
        val searchView = menu.findItem(R.id.item_search).actionView as? SearchView ?: return
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQuery(newText)
                return true
            }
        })
    }
}