package com.checkdate.helper.automatichelper.ind.dto

import java.time.LocalDate
import java.time.LocalTime

data class IndDataDto (
    val key: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime
)
