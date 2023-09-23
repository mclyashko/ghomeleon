package ru.mirea.ghomeleon.usecase.platform.declaration

import ru.mirea.ghomeleon.domain.platform.Platform

interface PlatformExtractor {
    fun getById(id: Platform.Id): Platform?

    fun getByName(name: Platform.Name): Platform?

    fun getAll(): List<Platform>
}
