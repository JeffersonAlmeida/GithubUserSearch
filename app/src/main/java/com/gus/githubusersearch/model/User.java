package com.gus.githubusersearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("followers")
    @Expose
    private Integer followers;

    @SerializedName("following")
    @Expose
    private Integer following;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

}
