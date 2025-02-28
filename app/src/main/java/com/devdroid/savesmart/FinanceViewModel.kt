package com.devdroid.savesmart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinanceViewModel : ViewModel() {
    private val _income = MutableLiveData(0)
    val income: LiveData<Int> get() = _income

    fun updateIncome(newIncome: Int) {
        _income.value = newIncome
    }
}
