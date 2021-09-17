package com.github.sigureruri.soundcore

import com.github.sigureruri.soundcore.sound.BukkitSoundManager
import com.github.sigureruri.soundcore.sound.SoundManager
import org.bukkit.plugin.java.JavaPlugin

class SoundCore : JavaPlugin() {

    override fun onEnable() {
        plugin = this

        soundManager = BukkitSoundManager()
    }

    companion object {

        lateinit var plugin: JavaPlugin
            private set

        lateinit var soundManager: SoundManager
            private set

    }

}