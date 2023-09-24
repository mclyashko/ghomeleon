package ru.mirea.ghomeleon.usecase.platform.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor
import ru.mirea.ghomeleon.usecase.platform.declaration.query.GetPlatformByName
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto

@Component
class GetPlatformByNameUseCase(
    private val platformExtractor: PlatformExtractor,
) : GetPlatformByName {
    override fun execute(name: Platform.Name): PlatformInfo {
        return platformExtractor
            .getByName(name = name)
            ?.toPlatformInfoDto()
            ?: throw DomainException.GetPlatformByNameNotFoundException(
                "Platform with name '${name.toStringValue()}' doesn't exist!"
            )
    }
}
