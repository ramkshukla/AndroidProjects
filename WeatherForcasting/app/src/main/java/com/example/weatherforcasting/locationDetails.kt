package com.example.weatherforcasting

import java.text.DecimalFormat

data class locationDetails(val name: String, val region: String,
                           val country: String, val lat: DecimalFormat,
                           val lon: DecimalFormat, val tz_id: String) {

}
