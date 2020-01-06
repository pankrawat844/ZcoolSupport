package com.app.zcoolsupport

import android.app.Application
import com.app.zcoolsupport.repo.Repository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication :Application(),KodeinAware{
    override val kodein= Kodein.lazy {
        import(androidXModule(this@MainApplication))
        bind() from singleton { LoginViewmodel(instance()) }
        bind() from singleton { LoginViewmodelFactory(instance()) }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { MyApi() }
    }


}