package com.example.supabasedemo.data.model

data class Clinic(
    val id: Long?,
    val createdAt: String?,
    val province: String,
    val city: String,
    val clinicName: String,
    val phoneNumber: String,
    val category: String,
    val ratAvailable: Boolean,
    val address: String,
)
