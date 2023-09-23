package ru.mirea.ghomeleon.domain.common.design.entity

abstract class AggregateRoot<T> protected constructor(
    id: T,
) : DomainEntity<T>(id = id)
