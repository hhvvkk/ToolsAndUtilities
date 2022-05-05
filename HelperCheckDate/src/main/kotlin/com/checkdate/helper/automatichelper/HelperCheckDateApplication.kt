package com.checkdate.helper.automatichelper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class HelperCheckDateApplication

fun main(args: Array<String>) {
	runApplication<HelperCheckDateApplication>(*args)
}
