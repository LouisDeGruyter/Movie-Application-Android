package com.example.moviesandseries.model.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoApi(
    @Json(name = "id")
    var id: String = "",
    @Json(name = "iso_3166_1")
    var iso31661: String = "",
    @Json(name = "iso_639_1")
    var iso6391: String = "",
    @Json(name = "key")
    var key: String = "",
    @Json(name = "name")
    var name: String = "",
    @Json(name = "official")
    var official: Boolean = false,
    @Json(name = "published_at")
    var publishedAt: String = "",
    @Json(name = "site")
    var site: String = "",
    @Json(name = "size")
    var size: Int = 0,
    @Json(name = "type")
    var type: String = "",
)
fun VideoApi.asDomainObject(): Video {
    return Video(
        id = id,
        iso31661 = iso31661,
        iso6391 = iso6391,
        key = key,
        name = name,
        official = official,
        publishedAt = publishedAt,
        site = site,
        size = size,
        type = type,
    )
}
