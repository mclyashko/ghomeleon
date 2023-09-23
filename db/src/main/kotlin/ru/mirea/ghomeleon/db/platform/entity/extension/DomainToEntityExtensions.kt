package ru.mirea.ghomeleon.db.platform.entity.extension

import ru.mirea.ghomeleon.db.platform.entity.PlatformEntity
import ru.mirea.ghomeleon.domain.platform.Platform

fun Platform.toPlatformEntity(): PlatformEntity = PlatformEntity(
    id = id.toLongValue(),
    name = name.toStringValue(),
    releaseDate = releaseDate.toLocalDateValue(),
    manufacturer = manufacturer.toStringValue(),
    removed = removed,
)
