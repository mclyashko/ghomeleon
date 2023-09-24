package ru.mirea.ghomeleon.usecase.platform.dto

import ru.mirea.ghomeleon.domain.platform.Platform

data class UpdatePlatformInfo(
    val name: Platform.Name,
    val releaseDate: Platform.ReleaseDate,
    val manufacturer: Platform.Manufacturer,
)
