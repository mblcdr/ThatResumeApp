package com.samsaz.thatresumeapp.test

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class FragmentTestActivity: AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var injector: AndroidInjector<Fragment>

    override fun supportFragmentInjector() = injector

    fun startFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        this.injector = injector
        supportFragmentManager. beginTransaction()
            .add(android.R.id.content, fragment, "SomeTag")
            .commit()
    }

    inline fun <reified T : Fragment> startFragment(fragment: T, crossinline injector: (T) -> Unit) {
        startFragment(fragment, AndroidInjector { if (it is T) injector(it) })
    }
}
