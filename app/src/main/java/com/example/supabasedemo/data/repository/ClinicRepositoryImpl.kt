package com.example.supabasedemo.data.repository

import com.example.supabasedemo.data.ClinicDto
import com.example.supabasedemo.data.model.Clinic
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClinicRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : ClinicRepository {
    override suspend fun getClinics(): List<ClinicDto> {
        return postgrest["clinics"].select().decodeList()
    }
    override suspend fun getClinicById(id: Long): ClinicDto {
        return postgrest["clinics"].select {  filter {  eq("id", id) } }
            .decodeSingle()
    }
    override suspend fun createClinic(clinic: Clinic): Clinic {
        try {
            withContext(Dispatchers.IO){
                val clinicDto = ClinicDto(
                    id = clinic.id,
                    createdAt = clinic.createdAt,
                    province = clinic.province,
                    city = clinic.city,
                    clinicName = clinic.clinicName,
                    phoneNumber = clinic.phoneNumber,
                    category = clinic.category,
                    ratAvailable = clinic.ratAvailable,
                    address = clinic.address,
                )
                postgrest["clinics"].insert(clinicDto)

            }
            return clinic
        } catch (e: Exception) {
            throw e
        }
    }
    override suspend fun updateClinic(
        id: Long?,
        createdAt: String?,
        province: String,
        city: String,
        clinicName: String,
        phoneNumber: String,
        category: String,
        ratAvailable: Boolean,
        address: String,
    ) {
        try {
            withContext(Dispatchers.IO) {
                postgrest["clinics"].update({
                    ClinicDto::province setTo province
                    ClinicDto::city setTo city
                    ClinicDto::clinicName setTo clinicName
                    ClinicDto::phoneNumber setTo phoneNumber
                    ClinicDto::category setTo category
                    ClinicDto::ratAvailable setTo ratAvailable
                    ClinicDto::address setTo address
                }){
                    filter {  ClinicDto::id eq id }
                }
                postgrest["clinics"].select {
                    filter {   ClinicDto::id eq id }
                }
//                return@withContext
            }
//            return @withContext
        } catch (e: Exception) {
            throw Exception("Clinic not found")
        }
    }
    override suspend fun deleteClinic(id: Long) {
        return withContext(Dispatchers.IO){
            postgrest["clinics"].delete {
                filter { ClinicDto::id eq id }
            }
        }
    }

}