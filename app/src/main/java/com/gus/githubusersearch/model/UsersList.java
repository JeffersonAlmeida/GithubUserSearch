package com.gus.githubusersearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UsersList {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("items")
    @Expose
    private List<User> items = new ArrayList<User>();

}
