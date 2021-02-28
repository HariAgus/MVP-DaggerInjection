package com.hariagus.mvp.models

data class PhotosResponse(
    var albumId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var url: String = "",
    var thumbnailUrl: String = ""
)