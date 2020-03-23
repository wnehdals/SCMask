package com.example.scmask

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var context: Context? = this
    private lateinit var recyclerviewAdapter : recyclerAdapter
    private var storeArrayList : ArrayList<Stores>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    init{
        context = this
        storeArrayList = ArrayList()
        //mainInteractor = MainInteractor()
        //mainPresenter = MainPresenter(this, mainInteractor)
    }

    fun get(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(coronaAPI::class.java)
        val callGetStoreSaleResult = api.getMaskData("37.451438","129.1711713", 5000)
        callGetStoreSaleResult.enqueue(object : Callback<StoreSaleResult>{
            override fun onFailure(call: Call<StoreSaleResult>, t: Throwable) {
            }

            override fun onResponse(call: Call<StoreSaleResult>, response: Response<StoreSaleResult>
            ) {
                var storeSaleResult : StoreSaleResult? = response.body()
                Log.d("response",(storeSaleResult?.stores?.size).toString())
                if(storeSaleResult?.stores?.size != 0){
                    for( i in storeSaleResult!!.stores){
                        storeArrayList.add(i)
                    }
                }
                if(storeArrayList.size != 0){
                    recyclerviewAdapter = recyclerAdapter(storeArrayList, context)
                    recycler_view.adapter = recyclerviewAdapter
                    var storeGridLayoutManager = GridLayoutManager(context,2)
                    recycler_view.layoutManager = storeGridLayoutManager
                    recycler_view.setNestedScrollingEnabled(false)
                    recycler_view.setHasFixedSize(false)

                }
            }
        })
    }



    override fun onStart() {
        //mainPresenter.getStoreSaleResultData("삼척시 교동")
        get()
        super.onStart()
    }


}
