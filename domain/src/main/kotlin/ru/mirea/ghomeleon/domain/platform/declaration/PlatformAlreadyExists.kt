package ru.mirea.ghomeleon.domain.platform.declaration

import ru.mirea.ghomeleon.domain.platform.Platform

fun interface PlatformAlreadyExists {
    operator fun invoke(name: Platform.Name): Boolean
}