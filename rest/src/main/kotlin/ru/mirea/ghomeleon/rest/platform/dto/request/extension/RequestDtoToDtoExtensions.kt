package ru.mirea.ghomeleon.rest.platform.dto.request.extension

import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.rest.platform.dto.request.AddPlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.request.UpdatePlatformRequest
import ru.mirea.ghomeleon.usecase.platform.dto.AddPlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.UpdatePlatformInfo

fun AddPlatformRequest.toAddPlatformInfo(): AddPlatformInfo = AddPlatformInfo(
    name = Platform.Name(name),
    releaseDate = Platform.ReleaseDate(releaseDate),
    manufacturer = Platform.Manufacturer.from(manufacturer),
)

fun UpdatePlatformRequest.toUpdatePlatformInfo(): UpdatePlatformInfo = UpdatePlatformInfo(
    name = Platform.Name(name),
    releaseDate = Platform.ReleaseDate(releaseDate),
    manufacturer = Platform.Manufacturer.from(manufacturer),
)
