package ru.mirea.ghomeleon.usecase.platform.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformPersister
import ru.mirea.ghomeleon.usecase.platform.declaration.command.UpdatePlatformById
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.UpdatePlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto
import ru.mirea.ghomeleon.usecase.platform.dto.extension.updateFromDto

@Component
class UpdatePlatformByIdUseCase(
    private val platformExtractor: PlatformExtractor,
    private val platformPersister: PlatformPersister,
) : UpdatePlatformById {
    @Transactional
    override fun execute(id: Platform.Id, updatePlatformInfo: UpdatePlatformInfo): PlatformInfo {
        val platform = platformExtractor
            .getById(id = id)
            ?: throw DomainException.UpdatePlatformByIdNotFoundException(
                "Platform with id '${id.toLongValue()}' doesn't exist!"
            )

        return platform.updateFromDto(updatePlatformInfo).let {
            platformPersister.save(it)
            it.toPlatformInfoDto()
        }
    }
}
