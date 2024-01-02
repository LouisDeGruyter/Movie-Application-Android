package com.example.moviesandseries.model.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a video associated with a movie or series.
 *
 * @property id The unique identifier of the video.
 * @property iso31661 The ISO 3166-1 country code for the video.
 * @property iso6391 The ISO 639-1 language code for the video.
 * @property key The unique key for accessing the video on its respective platform.
 * @property name The name or title of the video.
 * @property official Indicates if the video is official.
 * @property publishedAt The date when the video was published.
 * @property site The platform or site where the video is hosted.
 * @property size The size of the video.
 * @property type The type or category of the video.
 */
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

/**
 * Extension function to convert [VideoApi] to [Video] domain object.
 */
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
