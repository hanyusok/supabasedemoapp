package com.example.supabasedemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    @SerialName("id") val id: Long?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("province") val province: String,
    @SerialName("city") val city: String,
    @SerialName("clinic_name") val clinicName: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("category") val category: String,
    @SerialName("rat_available") val ratAvailable: Boolean,
    @SerialName("address") val address: String,
)
