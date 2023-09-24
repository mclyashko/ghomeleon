package ru.mirea.ghomeleon.usecase.platform.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.domain.platform.command.markAsRemoved
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformPersister
import ru.mirea.ghomeleon.usecase.platform.declaration.command.RemovePlatformById
import ru.mirea.ghomeleon.usecase.platform.dto.PlatformInfo
import ru.mirea.ghomeleon.usecase.platform.dto.extension.toPlatformInfoDto

@Component
class RemovePlatformByIdUseCase(
    private val platformExtractor: PlatformExtractor,
    private val platformPersister: PlatformPersister,
) : RemovePlatformById {
    @Transactional
    override fun execute(id: Platform.Id): PlatformInfo {
        val platform = platformExtractor
            .getById(id = id)
            ?: throw DomainException.RemovePlatformByIdNotFoundException(
                "Platform with id '${id.toLongValue()}' doesn't exist!"
            )

        platform.markAsRemoved()

        return platform.let {
            platformPersister.save(it)
            it.toPlatformInfoDto()
        }
    }
}
