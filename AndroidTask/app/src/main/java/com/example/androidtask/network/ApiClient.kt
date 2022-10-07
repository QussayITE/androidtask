package com.example.androidtask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        val BASE_URL = "https://my-json-server.typicode.com/SharminSirajudeen/test_resources/"
        private var retrofit: Retrofit? = null
       // val IMAGE_BASE_URL = "https://dummyimage.com/150/"

        /**
         * This method returns retrofit client instance
         *
         * @return Retrofit object
         */


        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}