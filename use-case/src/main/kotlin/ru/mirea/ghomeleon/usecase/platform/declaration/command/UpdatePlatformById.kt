package ru.mirea.ghomeleon.usecase.platform.declaration.command

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.UpdatePlatformInfo
import kotlin.jvm.Throws

interface UpdatePlatformById {
    @Throws(DomainException.UpdatePlatformByIdNotFoundException::class)
    fun execute(id: Platform.Id, updatePlatformInfo: UpdatePlatformInfo): PlatformInfo
}
