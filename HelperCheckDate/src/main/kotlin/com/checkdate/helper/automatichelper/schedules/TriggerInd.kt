package com.checkdate.helper.automatichelper.schedules

import LoggerDelegate
import com.checkdate.helper.automatichelper.ind.IndService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TriggerInd @Autowired constructor(
    val indService: IndService
){
    private val logger by LoggerDelegate()

    //every few seconds (*/15 * * * * *) -- every 15 seconds
    //every few minutes (* */5 * * * *) -- every 5 seconds
    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "*/15 * * * * *")
    fun run() {
        logger.info("asdf")
        //Start beep
        indService.checkInd()
    }
}