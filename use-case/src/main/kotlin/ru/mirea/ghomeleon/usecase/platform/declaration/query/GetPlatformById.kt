package ru.mirea.ghomeleon.usecase.platform.declaration.query

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import kotlin.jvm.Throws

interface GetPlatformById {
    @Throws(DomainException.GetPlatformByIdNotFoundException::class)
    fun execute(id: Platform.Id): PlatformInfo
}
