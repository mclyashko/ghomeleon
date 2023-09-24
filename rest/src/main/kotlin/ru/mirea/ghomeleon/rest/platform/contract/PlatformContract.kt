package ru.mirea.ghomeleon.rest.platform.contract

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import ru.mirea.ghomeleon.rest.platform.dto.request.AddPlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.request.UpdatePlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse

@OpenAPIDefinition(
    info = Info(
        title = "ghomeleon",
        version = "1.0.0"
    )
)
interface PlatformContract {
    @Operation(
        summary = "Get all platforms"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAllPlatforms(): ResponseEntity<List<PlatformResponse>>

    @Operation(
        summary = "Get platform by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Platform not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getPlatformById(id: Long): ResponseEntity<PlatformResponse>

    @Operation(
        summary = "Get platform by name"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Platform not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getPlatformByName(name: String): ResponseEntity<PlatformResponse>

    @Operation(
        summary = "Add new platform"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewPlatform(addPlatformRequest: AddPlatformRequest): ResponseEntity<PlatformResponse>

    @Operation(
        summary = "Update information about the platform by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Platform not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun updatePlatform(
        id: Long,
        updatePlatformRequest: UpdatePlatformRequest,
    ): ResponseEntity<PlatformResponse>

    @Operation(
        summary = "Mark platform as removed"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Platform not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun removePlatform(
        id: Long,
    ): ResponseEntity<PlatformResponse>
}
