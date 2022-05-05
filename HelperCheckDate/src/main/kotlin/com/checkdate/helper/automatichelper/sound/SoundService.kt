package com.checkdate.helper.automatichelper.sound

import org.springframework.stereotype.Service
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

@Service
class SoundService {

    fun playBeepSound(amountOfRepetitions: Int) {
        repeat(amountOfRepetitions) {
            val f = File(BEEP_FILE_LOCATION)
            val audioIn: AudioInputStream = AudioSystem.getAudioInputStream(f.toURI().toURL())
            val clip = AudioSystem.getClip()
            clip.open(audioIn)
            clip.start()
            Thread.sleep(1000)
        }
    }

    companion object {
        private val BEEP_FILE_LOCATION = "/home/henko/Dev/Avoid_World/HelperCheckDate/beep.wav"
    }
}