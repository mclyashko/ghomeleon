package ru.mirea.ghomeleon.usecase.platform.dto.extension

import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo

internal fun Platform.toPlatformInfoDto(): PlatformInfo = PlatformInfo(
    id = id,
    name = name,
    releaseDate = releaseDate,
    manufacturer = manufacturer,
)
