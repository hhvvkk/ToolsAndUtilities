package com.checkdate.helper.automatichelper.ind

import LoggerDelegate
import com.checkdate.helper.automatichelper.ind.dto.IndDataDto
import com.checkdate.helper.automatichelper.ind.dto.IndDto
import com.checkdate.helper.automatichelper.sound.SoundService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@Service
class IndService @Autowired constructor(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
    private val soundService: SoundService
) {

    private val logger by LoggerDelegate()

    fun invokeRestRequestIND(): String {
        var response = restTemplate.getForObject(WEBSITE_URL, String::class.java) ?: throw Exception("help!!")

        // remove broken first line
        // because see ")]}'," found in file 'ind_raw.txt'
        val indexOfNewLine = response.indexOf('\n')
        response = response.substring(indexOfNewLine + 1)

        return response
    }

    fun checkInd() {
        val responseAsString = invokeRestRequestIND()

        val indResponse: IndDto = objectMapper.readValue(responseAsString, IndDto::class.java)

        val indData = indResponse
            .data
            .sortedBy { it.date }
            .getOrNull(0)

        invokeNotification(indData)
    }

    fun invokeNotification(indData: IndDataDto?) {
        indData?.let {
            val appointmentDate = indData.date
            logger.info("Date for IND $appointmentDate")

            if (appointmentDate.isBefore(MY_CURRENT_BOOKING)) {
                logger.info("WE CAN MAKE A BOOKING")
                logger.info("BOOK DATE -->  $appointmentDate")
                soundService.playBeepSound(3)
            }

        }
    }

    companion object {
        // The request used by IND to find booking timeslots.
        val WEBSITE_URL = "https://oap.ind.nl/oap/api/desks/AM/slots/?productKey=DOC&persons=1"
        val MY_CURRENT_BOOKING = LocalDate.of(2022, 6, 1)
    }
}