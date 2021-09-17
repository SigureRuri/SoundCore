package com.github.sigureruri.soundcore.sound

import org.bukkit.Location
import org.bukkit.entity.Player

interface SoundManager {

    fun playSoundEffect(player: Player, location: Location, soundEffect: SoundEffect)

    fun playBackgroundMusic(player: Player, location: Location, bgm: BackgroundMusic)

    fun stopBackgroundMusic(player: Player, bgm: BackgroundMusic)

}