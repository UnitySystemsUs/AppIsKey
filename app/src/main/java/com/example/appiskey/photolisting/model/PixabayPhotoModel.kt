package com.example.appiskey.photolisting.model


import com.google.gson.annotations.SerializedName

data class PixabayPhotoModel(
    @SerializedName("collections")
    var collections: Int?,
    @SerializedName("comments")
    var comments: Int?,
    @SerializedName("downloads")
    var downloads: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imageHeight")
    var imageHeight: Int?,
    @SerializedName("imageSize")
    var imageSize: Int?,
    @SerializedName("imageWidth")
    var imageWidth: Int?,
    @SerializedName("largeImageURL")
    var largeImageURL: String?,
    @SerializedName("likes")
    var likes: Int?,
    @SerializedName("pageURL")
    var pageURL: String?,
    @SerializedName("previewHeight")
    var previewHeight: Int?,
    @SerializedName("previewURL")
    var previewURL: String?,
    @SerializedName("previewWidth")
    var previewWidth: Int?,
    @SerializedName("tags")
    var tags: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("user")
    var user: String?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("userImageURL")
    var userImageURL: String?,
    @SerializedName("views")
    var views: Int?,
    @SerializedName("webformatHeight")
    var webFormatHeight: Int?,
    @SerializedName("webformatURL")
    var webFormatURL: String?,
    @SerializedName("webformatWidth")
    var webFormatWidth: Int?
)