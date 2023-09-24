package ru.mirea.ghomeleon.usecase.platform.declaration.command

import ru.mirea.ghomeleon.usecase.platform.dto.AddPlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo

interface AddNewPlatform {
    fun execute(addPlatformInfo: AddPlatformInfo): PlatformInfo
}
