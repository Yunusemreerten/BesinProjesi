package com.yudo.besinprojesi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yudo.besinprojesi.model.Besin
import com.yudo.besinprojesi.roomdb.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : AndroidViewModel(application) {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){
        viewModelScope.launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }

    }
}