package ru.mirea.ghomeleon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GhomeleonApp

fun main(args: Array<String>) {
    runApplication<GhomeleonApp>(args = args)
}
