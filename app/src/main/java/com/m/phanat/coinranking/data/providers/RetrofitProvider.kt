package com.m.phanat.coinranking.data.providers


import com.m.phanat.coinranking.data.models.ApiException
import com.m.phanat.coinranking.data.models.ApiExceptionBody
import com.m.phanat.coinranking.data.models.ErrorJson
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitProvider(
    private val moshi: Moshi,
    private val apiUrl: String
) {
    fun provide(): Retrofit {
        val client = OkHttpClient.Builder()
            .handleErrors()
            .setLogger(HttpLoggingInterceptor.Level.BODY)
            .setApiKey()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .setConverters()
            .baseUrl(apiUrl)
            .build()
    }

    private fun Retrofit.Builder.setConverters() =
        addConverterFactory(MoshiConverterFactory.create(moshi))

    private fun OkHttpClient.Builder.setLogger(
        logLevel: HttpLoggingInterceptor.Level
    ): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = logLevel
            })
        }
        return this
    }

    private fun OkHttpClient.Builder.setApiKey() = addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Accept", "application/json")
            .addHeader("X-CMC_PRO_API_KEY", "7e7a66f7-20b5-497d-b325-5dc019f4bf1a")
        chain.proceed(request.build())
    }

    private fun OkHttpClient.Builder.handleErrors() = addInterceptor { chain ->
        val response = chain.proceed(chain.request())

        if (response.isSuccessful) {
            response
        } else {
            val code = response.code
            val body = response.body

            throw if (body == null) {
                ApiException(ApiExceptionBody(code, "Server response is empty"))
            } else {
                try {
                    val error = moshi.adapter(ErrorJson::class.java).fromJson(body.string())?.status
                    ApiException(error)
                } catch (ex: Exception) {
                    ApiException(ApiExceptionBody(code, "Cannot parse error: ${ex.message}"))
                } finally {
                    body.close()
                }
            }
        }
    }
}