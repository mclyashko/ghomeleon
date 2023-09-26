package ru.mirea.ghomeleon.metricslogger

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.util.metric.MetricLogger

@Component
class MetricLoggerImpl(
    private val meterRegistry: MeterRegistry
) : MetricLogger {
    override fun registerCounter(name: String) {
        meterRegistry
            .counter(name)
            .increment()
    }
}
