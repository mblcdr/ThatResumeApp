package com.samsaz.thatresumeapp.skills

import androidx.lifecycle.ViewModel
import com.samsaz.shared.di.scope.FragmentScope
import com.samsaz.shared.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
internal abstract class SkillModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): SkillFragment

    @Binds
    @IntoMap
    @ViewModelKey(SkillViewModel::class)
    internal abstract fun bindViewModel(viewModel: SkillViewModel): ViewModel

    @Binds
    @Named("remote")
    internal abstract fun bindRemoteDataSource(dataSource: SkillRemoteDataSource):
            SkillDataSource

    @Binds
    @Named("assets")
    internal abstract fun bindAssetsDataSource(dataSource: SkillAssetsDataSource):
            SkillDataSource
}