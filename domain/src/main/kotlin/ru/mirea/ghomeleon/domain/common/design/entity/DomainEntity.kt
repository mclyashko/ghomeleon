package ru.mirea.ghomeleon.domain.common.design.entity

abstract class DomainEntity<T> protected constructor(
    private val id: T,
): ValueObject {
    fun toIdValue(): T = id
}