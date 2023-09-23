package ru.mirea.ghomeleon.usecase.platform.scenario

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.declaration.GetPlatformById
import ru.mirea.ghomeleon.usecase.platform.declaration.PlatformExtractor
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto

@Component
class GetPlatformByIdUseCase(
    private val platformExtractor: PlatformExtractor,
) : GetPlatformById {
    override fun execute(id: Platform.Id): PlatformInfo {
        return platformExtractor
            .getById(id = id)
            ?.toPlatformInfoDto()
            ?: throw DomainException.GetPlatformByIdNotFoundException(
                "Platform with id '${id.toLongValue()}' doesn't exist!"
            )
    }
}
