package com.mangabible.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertServerTimeToLocal(serverTimeString: String): String {
        val instant = Instant.parse(serverTimeString)
        val utcDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"))
        val localDateTime = utcDateTime.withZoneSameInstant(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy",
            Locale.current.platformLocale)
        return localDateTime.format(formatter)
    }
}
