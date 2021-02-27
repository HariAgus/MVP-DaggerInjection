package com.hariagus.mvp.util

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.Exception
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DateType : JsonDeserializer<Date> {

    companion object {
        private val DATE_FORMATS = arrayOf("yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "EEE MMM dd HH:mm:ss z yyyy", "HH:mm:ss", "MM/dd/yyyy HH:mm:ss aaa", "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", "MMM d',' yyyy H:mm:ss a")
        const val TAG = "TAG"
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        for (format in DATE_FORMATS) {
            try {
                return SimpleDateFormat(format, Locale.US).parse(json?.asString)
            } catch (e: Exception) {
                Log.e(TAG, "deserialize: $e", )
            }
        }
        throw JsonParseException("Unparseable date: \"" + json?.asString
                + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS))
    }
}