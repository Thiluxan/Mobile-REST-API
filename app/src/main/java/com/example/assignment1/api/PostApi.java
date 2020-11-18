package com.example.assignment1.api;

import com.example.assignment1.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts();
}
