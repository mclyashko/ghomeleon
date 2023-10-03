package ru.mirea.ghomeleon.testint.platform

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import ru.mirea.ghomeleon.rest.platform.dto.request.UpdatePlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse
import ru.mirea.ghomeleon.testint.BaseIntTest
import java.time.LocalDate

class UpdatePlatformTest : BaseIntTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldUpdateFirstPlatformFromMigration() {
        val updatePlatformRequest = UpdatePlatformRequest(
            name = "Nintendo DS",
            releaseDate = LocalDate.now(),
            manufacturer = "Nintendo"
        )

        val updatedPlatformAsString = mockMvc
            .put("/api/platform/id") {
                param("id", "1")
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(updatePlatformRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val updatedPlatform = jacksonObjectMapper
            .readValue<PlatformResponse>(updatedPlatformAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(updatePlatformRequest.name, updatedPlatform.name) },
            { Assertions.assertEquals(updatePlatformRequest.releaseDate, updatedPlatform.releaseDate) },
            { Assertions.assertEquals(updatePlatformRequest.manufacturer, updatedPlatform.manufacturer) },
        )
    }

    @Test
    fun shouldMarkFirstPlatformFromMigrationAsRemoved() {
        val platformAsString = mockMvc
            .get("/api/platform/name") {
                param("name", "Nintendo DS")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val platform = jacksonObjectMapper
            .readValue<PlatformResponse>(platformAsString)

        val removedPlatformeAsString = mockMvc
            .delete("/api/platform/id") {
                param("id", "1")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val removedPlatform = jacksonObjectMapper
            .readValue<PlatformResponse>(removedPlatformeAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(platform.id, removedPlatform.id) },
            { Assertions.assertFalse(platform.removed) },
            { Assertions.assertTrue(removedPlatform.removed) },
        )
    }
}
