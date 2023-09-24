package ru.mirea.ghomeleon.usecase.platform.declaration.acess

import ru.mirea.ghomeleon.domain.platform.Platform

interface PlatformPersister {
    fun save(platform: Platform)
}
