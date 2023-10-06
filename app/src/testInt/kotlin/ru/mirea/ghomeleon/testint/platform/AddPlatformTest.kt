package ru.mirea.ghomeleon.testint.platform

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import ru.mirea.ghomeleon.rest.platform.dto.request.AddPlatformRequest
import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse
import ru.mirea.ghomeleon.testint.BaseIntTest
import java.time.LocalDate

class AddPlatformTest : BaseIntTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldAddNewPlatform() {
        val addPlatformRequest = AddPlatformRequest(
            name = "Nintendo 4DS",
            releaseDate = LocalDate.now(),
            manufacturer = "Nintendo",
        )

        val createdPlatformAsString = mockMvc
            .post("/api/platform") {
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(addPlatformRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val createdPlatform = jacksonObjectMapper
            .readValue<PlatformResponse>(createdPlatformAsString)

        val createdPlatformId = createdPlatform.id

        val platformAsString = mockMvc
            .get("/api/platform/id") {
                param("id", createdPlatformId.toString())
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

        Assertions.assertAll(
            { Assertions.assertEquals(addPlatformRequest.name, createdPlatform.name) },
            { Assertions.assertEquals(addPlatformRequest.releaseDate, createdPlatform.releaseDate) },
            { Assertions.assertEquals(addPlatformRequest.manufacturer, createdPlatform.manufacturer) },
            { Assertions.assertEquals(createdPlatform.id, platform.id) },
            { Assertions.assertEquals(createdPlatform.name, platform.name) },
            { Assertions.assertEquals(createdPlatform.releaseDate, platform.releaseDate) },
            { Assertions.assertEquals(createdPlatform.manufacturer, platform.manufacturer) },
        )
    }
}
