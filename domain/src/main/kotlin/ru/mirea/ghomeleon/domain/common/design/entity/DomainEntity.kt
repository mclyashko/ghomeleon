package ru.mirea.ghomeleon.domain.common.design.entity

abstract class DomainEntity<T> protected constructor(
    val id: T,
) : ValueObject
