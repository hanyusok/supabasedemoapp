package com.example.supabasedemo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabasedemo.data.ClinicDto
import com.example.supabasedemo.data.model.Clinic
import com.example.supabasedemo.data.repository.ClinicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClinicListViewModel @Inject constructor(
    private val clinicRepository: ClinicRepository
) : ViewModel() {
    private val _clinicList = MutableStateFlow<List<ClinicDto>?>(listOf())
    val clinicList: Flow<List<ClinicDto>?> = _clinicList
    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getClinics()
    }
     fun getClinics() {
        viewModelScope.launch {
            val clinics = clinicRepository.getClinics()
            _clinicList.emit(clinics?.map { it.copy() })
        }
    }
    fun removeItem(clinic: ClinicDto) {
        viewModelScope.launch {
            val newClinicList = mutableListOf<ClinicDto>().apply { _clinicList.value?.let {  it -> addAll(it) } }
            newClinicList.remove(clinic)
            _clinicList.emit(newClinicList.toList())
            clinicRepository.deleteClinic(id = clinic.id!!)
            getClinics()
        }
    }
}