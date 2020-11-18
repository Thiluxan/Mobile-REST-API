package com.example.assignment1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.assignment1.api.PostApi
import com.example.assignment1.models.Post
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gson = GsonBuilder().create()
        var retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        var postApi = retrofit.create(PostApi::class.java)
        var postCall = postApi.posts

        postCall.enqueue(object : Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(activity,"Failed to load titles",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var list = response.body()
                var titleArray = ArrayList<String>()
                var bodyArray = ArrayList<String>()
                list?.map { post ->
                    Log.d("Item: ",post.title)
                    titleArray.add(post.title.toString())
                    bodyArray.add(post.body.toString())
                }

                var myListAdapter = activity?.let { MyListAdapter(it,titleArray!!,bodyArray!!) }
                view.findViewById<ListView>(R.id.post_list)?.adapter = myListAdapter
            }

        })

    }
}

