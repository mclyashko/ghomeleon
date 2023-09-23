package ru.mirea.ghomeleon.usecase.platform.declaration

import ru.mirea.ghomeleon.domain.platform.Platform

interface PlatformPersister {
    fun save(platform: Platform)
}
