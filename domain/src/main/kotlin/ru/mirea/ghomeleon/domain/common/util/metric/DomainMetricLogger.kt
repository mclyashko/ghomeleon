package ru.mirea.ghomeleon.domain.common.util.metric

interface DomainMetricLogger {
    fun registerCounter(name: String)

    companion object {
        const val GAME_ALREADY_EXISTS_VALIDATION_FAILED =
            "game.alreadyexistsvalidation.failed"
        const val PLATFORM_ALREADY_EXISTS_VALIDATION_FAILED =
            "platform.alreadyexistsvalidation.failed"
    }
}
