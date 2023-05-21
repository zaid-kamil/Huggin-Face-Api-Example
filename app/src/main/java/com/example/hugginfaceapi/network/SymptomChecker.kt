package com.example.hugginfaceapi.network


// curl https://api-inference.huggingface.co/models/abhirajeshbhai/symptom-2-disease-net \
//	-X POST \
//	-d '{"inputs": "I like you. I love you"}' \
//	-H "Authorization: Bearer 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"


import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


data class RequestInp(
    @field:Json(name = "inputs") val inputs: String
)

data class Prediction(
    @field:Json(name = "label") val label: String,
    @field:Json(name = "score") val score: Double
)

data class Disease(
    val label: String,
    val score: Double
)
const val key = "xxxxxxxxxxx"
interface ApiService {

    @Headers("Authorization: Bearer $key")
    @POST("models/abhirajeshbhai/symptom-2-disease-net")
    suspend fun getDiseasePrediction(@Body request: RequestInp): List<List<Disease>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://api-inference.huggingface.co/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

