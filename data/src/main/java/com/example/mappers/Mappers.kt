package com.example.mappers

import com.example.domain.model.NewsDomainItem
import com.example.dto.dto.ItemDto

fun ItemDto.toDomain(): NewsDomainItem {
    return NewsDomainItem(
        title = this.title,
        description = this.description,
        link = this.link,
        pubDate = this.pubDate,
        guid = this.guid,
        imageUrl = this.contents.firstOrNull()?.url
    )
}