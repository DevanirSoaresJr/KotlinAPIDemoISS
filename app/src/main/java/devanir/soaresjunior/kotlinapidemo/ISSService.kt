package devanir.soaresjunior.kotlinapidemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISSService {

    @GET(ISS_ENDPOINT)
    fun getISSPasses(@Query("lat")latitude: Float, @Query("lon") longitude: Float):
            Call<ISSResponse>
}