package com.example.core.domain.usecase

import com.example.core.model.Comic

class ComicFactory {

    fun createList() = listOf(
        Comic(
            id = "1",
            title = "Marvel 1",
            thumbnail = ""
        ),
        Comic(
            id = "2",
            title = "Marvel 2",
            thumbnail = ""
        ),
        Comic(
            id = "3",
            title = "Marvel 3",
            thumbnail = ""
        ),
    )
}