package com.app.zcoolsupport.raiseticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.zcoolsupport.repo.Repository

class RaiseTicketViewmodelFactory(val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RaiseTicketViewModel(repository) as T
    }

}
