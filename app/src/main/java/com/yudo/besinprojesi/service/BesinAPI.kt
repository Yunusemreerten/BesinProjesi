package com.yudo.besinprojesi.service

import com.yudo.besinprojesi.model.Besin
import retrofit2.http.GET

interface BesinAPI {

    // api.google.com/updatedb
    // api.google.com/readusers
    // api.google.com/createuser

    //BASE URL -> https://raw.githubusercontent.com/
    //ENDPOINT -> atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getBesin() : List<Besin>




}