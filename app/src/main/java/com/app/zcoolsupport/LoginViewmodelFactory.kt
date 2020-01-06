package com.app.zcoolsupport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.zcoolsupport.repo.Repository

class LoginViewmodelFactory(val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewmodel(repository) as T
    }
}