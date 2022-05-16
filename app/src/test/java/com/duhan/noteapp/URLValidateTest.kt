package com.duhan.noteapp

import com.duhan.noteapp.feature_note.domain.use_case.ValidateUrlUseCase

class URLValidateTest {
    private var validateUrlUseCase: ValidateUrlUseCase = ValidateUrlUseCase()

    @org.junit.Test
    fun can_not_validate_non_image_url() {
        val url = "https://www.google.com"
        assert(!validateUrlUseCase.execute(url))
    }

    @org.junit.Test
    fun can_validate_non_image_url() {
        listOf("png", "jpg", "jpeg").forEach() { it ->
            val url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.${it}"
            assert(validateUrlUseCase.execute(url))
        }
    }

    @org.junit.Test
    fun cant_validate_invalid_starting_image_url() {
        listOf("png", "jpg", "jpeg").forEach() { it ->
            val url = "google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.${it}"
            assert(!validateUrlUseCase.execute(url))
        }
    }
}