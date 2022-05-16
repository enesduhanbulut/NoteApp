package com.duhan.noteapp.feature_note.domain.use_case

class ValidateUrlUseCase {

    fun execute(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://") ||
                url.startsWith("www.") || url.startsWith("ftp://") ||
                url.startsWith("ftps://") || url.startsWith("file://")) &&
                (url.endsWith(".png") || url.endsWith(".jpg")
                || url.endsWith(".jpeg"))
    }
}