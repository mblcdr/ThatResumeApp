package com.samsaz.thatresumeapp.skills

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
}