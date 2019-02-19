package devanir.soaresjunior.kotlinapidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val issService = retrofit.create(ISSService::class.java)
        val passesAdapter = PassesAdapter()

        rvData.layoutManager = LinearLayoutManager(this)
        rvData.adapter = passesAdapter

        btnGetResult.setOnClickListener {
            issService.getISSPasses(etLatitude.text.toString().toFloat(),
                etLongitude.text.toString().toFloat()).enqueue(object : Callback<ISSResponse>
            {
                override fun onFailure(call: Call<ISSResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<ISSResponse>, response: Response<ISSResponse>) {
                 passesAdapter.setData(response.body()?.response?: emptyList())

                    if ( response.body()?.message.equals("failure"))
                            Toast.makeText(this@MainActivity, "Latitude and Longitude must be numbers between -90.0 and 90.0", Toast.LENGTH_LONG).show()


                }
            })
        }



    }
}
