package ru.mirea.ghomeleon.usecase.platform.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformAlreadyExists
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformIdGenerator
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformPersister
import ru.mirea.ghomeleon.usecase.platform.declaration.command.AddNewPlatform
import ru.mirea.ghomeleon.usecase.platform.dto.AddPlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto

@Component
class AddNewPlatformUseCase(
    private val platformIdGenerator: PlatformIdGenerator,
    private val platformAlreadyExists: PlatformAlreadyExists,
    private val platformPersister: PlatformPersister,
) : AddNewPlatform {
    @Transactional
    override fun execute(addPlatformInfo: AddPlatformInfo): PlatformInfo {
        return Platform.addPlatform(
            platformIdGenerator = platformIdGenerator,
            platformAlreadyExists = platformAlreadyExists,
            name = addPlatformInfo.name,
            releaseDate = addPlatformInfo.releaseDate,
            manufacturer = addPlatformInfo.manufacturer,
        ).let { platform ->
            platformPersister.save(platform)
            platform.toPlatformInfoDto()
        }
    }
}
