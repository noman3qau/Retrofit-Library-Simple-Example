package com.noman.retrofit

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.noman.retrofit.adapter.CustomAdapter
import com.noman.retrofit.models.DataPhoto
import com.noman.retrofit.retrofit.GetDataService
import com.noman.retrofit.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var recyclerViewList: RecyclerView? = null
    private var customerAdapter: CustomAdapter? = null

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()


        // Create handler for Retrofit instance
        var retrofitService: GetDataService = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService::class.java)

        var call: Call<ArrayList<DataPhoto>> = retrofitService.getAllPhotos()

        call.enqueue(object : Callback<ArrayList<DataPhoto>> {
            override fun onResponse(call: Call<ArrayList<DataPhoto>>, response: Response<ArrayList<DataPhoto>>) {
                if (response != null) {
                    if (progressDialog != null && progressDialog.isShowing) {
                        progressDialog.dismiss()
                    }
                    generateDataList(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<DataPhoto>>, t: Throwable) {
                if (progressDialog != null && progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
                Toast.makeText(this@MainActivity, "Something went wrong... Please try again later!", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun generateDataList(dataList: ArrayList<DataPhoto>?) {
        if (dataList == null) return

        recyclerViewList = findViewById<RecyclerView>(R.id.recyclerPhotoList)

        customerAdapter = CustomAdapter(dataList!!, this)

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewList?.layoutManager = layoutManager

        recyclerViewList?.adapter = customerAdapter

    }


}


