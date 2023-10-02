package ru.mirea.ghomeleon.rest.platform.controller

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.rest.platform.contract.PlatformContract
import ru.mirea.ghomeleon.rest.platform.dto.request.AddPlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.request.UpdatePlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.request.extension.toAddPlatformInfo
import ru.mirea.ghomeleon.rest.platform.dto.request.extension.toUpdatePlatformInfo
import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse
import ru.mirea.ghomeleon.rest.platform.dto.response.extension.toResponseDto
import ru.mirea.ghomeleon.usecase.platform.declaration.command.AddNewPlatform
import ru.mirea.ghomeleon.usecase.platform.declaration.command.RemovePlatformById
import ru.mirea.ghomeleon.usecase.platform.declaration.command.UpdatePlatformById
import ru.mirea.ghomeleon.usecase.platform.declaration.query.GetAllPlatforms
import ru.mirea.ghomeleon.usecase.platform.declaration.query.GetPlatformById
import ru.mirea.ghomeleon.usecase.platform.declaration.query.GetPlatformByName

private val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/api")
class PlatformController(
    private val getAllPlatforms: GetAllPlatforms,
    private val getPlatformById: GetPlatformById,
    private val getPlatformByName: GetPlatformByName,
    private val addNewPlatform: AddNewPlatform,
    private val updatePlatformById: UpdatePlatformById,
    private val removePlatformById: RemovePlatformById,
) : PlatformContract {
    @GetMapping("/platform")
    override fun getAllPlatforms(): ResponseEntity<List<PlatformResponse>> {
        return ResponseEntity.ok(
            getAllPlatforms
                .execute()
                .map { platformInfo ->
                    platformInfo.toResponseDto()
                }
        )
    }

    @GetMapping("/platform/id")
    override fun getPlatformById(
        @RequestParam id: Long
    ): ResponseEntity<PlatformResponse> {
        log.info {
            "getPlatformById with id $id"
        }
        return ResponseEntity.ok(
            getPlatformById
                .execute(id = Platform.Id(id))
                .toResponseDto()
        )
    }

    @GetMapping("/platform/name")
    override fun getPlatformByName(
        @RequestParam name: String
    ): ResponseEntity<PlatformResponse> {
        log.info {
            "getPlatformByName with name $name"
        }
        return ResponseEntity.ok(
            getPlatformByName
                .execute(name = Platform.Name(name))
                .toResponseDto()
        )
    }

    @PostMapping("/platform")
    override fun addNewPlatform(
        @RequestBody addPlatformRequest: AddPlatformRequest
    ): ResponseEntity<PlatformResponse> {
        log.info {
            "addNewPlatform with addPlatformRequest $addPlatformRequest"
        }
        return ResponseEntity.ok(
            addNewPlatform
                .execute(addPlatformInfo = addPlatformRequest.toAddPlatformInfo())
                .toResponseDto()
        )
    }

    @PutMapping("/platform/id")
    override fun updatePlatform(
        @RequestParam id: Long,
        @RequestBody updatePlatformRequest: UpdatePlatformRequest
    ): ResponseEntity<PlatformResponse> {
        log.info {
            "updatePlatform with id $id updatePlatformRequest $updatePlatformRequest"
        }
        return ResponseEntity.ok(
            updatePlatformById
                .execute(
                    id = Platform.Id(id),
                    updatePlatformInfo = updatePlatformRequest.toUpdatePlatformInfo()
                )
                .toResponseDto()
        )
    }

    @DeleteMapping("/platform/id")
    override fun removePlatform(
        @RequestParam id: Long
    ): ResponseEntity<PlatformResponse> {
        log.info {
            "removePlatform with id $id"
        }
        return ResponseEntity.ok(
            removePlatformById
                .execute(id = Platform.Id(id))
                .toResponseDto()
        )
    }
}
