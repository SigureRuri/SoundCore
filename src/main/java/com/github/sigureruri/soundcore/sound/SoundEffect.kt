package com.github.sigureruri.soundcore.sound

import org.bukkit.NamespacedKey
import org.bukkit.Sound
import org.bukkit.SoundCategory

/**
 * Minecraft内の音声を表す
 * 各々の音をリソースパックで書き換えた可能性も考慮するため、それぞれの音に適したtimeを設定する必要がある。
 */
class SoundEffect(
    val sound: NamespacedKey,
    val category: SoundCategory,
    val volume: Float,
    val pitch: Float
) {

    constructor(sound: String, category: SoundCategory, volume: Float, pitch: Float) : this(NamespacedKey.minecraft(sound), category, volume, pitch)

    constructor(sound: Sound, category: SoundCategory, volume: Float, pitch: Float) : this(sound.key, category, volume, pitch)

}