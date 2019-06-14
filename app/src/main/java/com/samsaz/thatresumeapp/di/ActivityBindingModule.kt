package com.samsaz.thatresumeapp.di

import com.samsaz.shared.di.scope.ActivityScope
import com.samsaz.thatresumeapp.MainActivity
import com.samsaz.thatresumeapp.MainModule
import com.samsaz.thatresumeapp.aboutme.AboutMeModule
import com.samsaz.thatresumeapp.experience.ExperienceModule
import com.samsaz.thatresumeapp.skills.SkillModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, ExperienceModule::class,
            AboutMeModule::class, SkillModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}