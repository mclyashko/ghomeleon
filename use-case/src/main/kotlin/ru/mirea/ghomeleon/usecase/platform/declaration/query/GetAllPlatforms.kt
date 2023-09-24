package ru.mirea.ghomeleon.usecase.platform.declaration.query

import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo

interface GetAllPlatforms {
    fun execute(): List<PlatformInfo>
}
