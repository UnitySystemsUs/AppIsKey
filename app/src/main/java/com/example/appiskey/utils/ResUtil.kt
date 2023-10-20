package com.example.appiskey.utils

import java.util.Locale

object ResUtil {

    fun charCount(input: String): Map<String, Int> {
        val wordCount = mutableMapOf<String, Int>()
        var totalWords = 0

        // Remove punctuation and split the string into words
        val words = input.replace(Regex("[^a-zA-Z ]"), "").lowercase(Locale.getDefault())
            .split("\\s+".toRegex())

        // Count the occurrences of each word
        for (word in words) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }
        for (word in wordCount.keys) totalWords += wordCount[word] ?: 0

        wordCount["total_words"] = totalWords

        return wordCount
    }

//    fun loadSingleImage(ivToLoad: ImageView, url: String?, progressBar: ProgressBar) {
//        Glide.with(ivToLoad.context)
//            .setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
//            .asBitmap().load(url).listener(object : RequestListener<Bitmap> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Bitmap>,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    ivToLoad.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    model: Any,
//                    target: Target<Bitmap>?,
//                    dataSource: DataSource,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    ivToLoad.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    return false
//                }
//            }).into(ivToLoad)
//    }

}