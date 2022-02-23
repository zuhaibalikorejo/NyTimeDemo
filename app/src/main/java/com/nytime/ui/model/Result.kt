package com.nytime.ui.model

/**
 * <p>
 * @author zuhaib ahmed
 */
import com.google.gson.annotations.SerializedName


data  class Result(var title:String?,var url:String?,var abstract:String?,var published_date:String?, var media: MutableList<media>?)
data  class media(
        @SerializedName("media-metadata")
        var mediaMetadata: MutableList<MediaMetadat>
)

data class MediaMetadat(val url:String?)