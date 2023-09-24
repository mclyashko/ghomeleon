package ru.mirea.ghomeleon.rest.platform.dto.response.extension

import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo

fun PlatformInfo.toResponseDto(): PlatformResponse = PlatformResponse(
    id = id.toLongValue(),
    name = name.toStringValue(),
    releaseDate = releaseDate.toLocalDateValue(),
    manufacturer = manufacturer.toStringValue(),
    removed = removed,
)
