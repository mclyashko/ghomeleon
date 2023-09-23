package ru.mirea.ghomeleon.domain.platform.declaration

import ru.mirea.ghomeleon.domain.platform.Platform

interface PlatformIdGenerator {
    fun generate(): Platform.Id
}
