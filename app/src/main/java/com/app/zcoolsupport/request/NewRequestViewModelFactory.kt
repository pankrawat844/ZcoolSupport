package com.app.zcoolsupport.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.zcoolsupport.repo.Repository

class NewRequestViewModelFactory(val repository: Repository):ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewRequestViewModel(repository) as T
    }
}