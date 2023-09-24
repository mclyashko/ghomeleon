package ru.mirea.ghomeleon.usecase.platform.dto.extension

import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.dto.UpdatePlatformInfo

fun Platform.updateFromDto(dto: UpdatePlatformInfo): Platform = Platform.restorePlatform(
    id = id,
    name = dto.name,
    releaseDate = dto.releaseDate,
    manufacturer = dto.manufacturer,
    removed = removed
)
