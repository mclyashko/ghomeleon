package ru.mirea.ghomeleon.usecase.platform.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor
import ru.mirea.ghomeleon.usecase.platform.declaration.query.GetAllPlatforms
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto

@Component
class GetAllPlatformsUseCase(
    private val platformExtractor: PlatformExtractor,
) : GetAllPlatforms {
    override fun execute(): List<PlatformInfo> {
        return platformExtractor
            .getAll()
            .map { platform ->
                platform.toPlatformInfoDto()
            }
    }
}
