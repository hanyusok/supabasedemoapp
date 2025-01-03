package com.example.supabasedemo.data.repository

import com.example.supabasedemo.data.ClinicDto
import com.example.supabasedemo.data.model.Clinic

interface ClinicRepository {
    suspend fun getClinics(): List<ClinicDto>?
    suspend fun getClinicById(id: Long): ClinicDto?
    suspend fun createClinic(clinic: Clinic): Clinic
    suspend fun updateClinic(
        id: Long?,
        createdAt: String?,
        province: String,
        city: String,
        clinicName: String,
        phoneNumber: String,
        category: String,
        ratAvailable: Boolean,
        address: String,
    )
    suspend fun deleteClinic(id: Long)
}