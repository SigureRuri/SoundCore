package com.github.sigureruri.soundcore.sound

import org.bukkit.NamespacedKey
import org.bukkit.Sound
import org.bukkit.SoundCategory

/**
 * Minecraft内の音声を表す
 * 各々の音をリソースパックで書き換えた可能性も考慮するため、それぞれの音に適したtimeを設定する必要がある。
 */
class BackgroundMusic(
    val sound: NamespacedKey,
    val category: SoundCategory,
    val pitch: Float,
    val priority: BgmPriority,
    val tickLength: Long,
    val loop: Boolean
) {

    constructor(
        sound: String,
        category: SoundCategory,
        pitch: Float,
        priority: BgmPriority,
        tickLength: Long,
        loop: Boolean
    ) : this(NamespacedKey.minecraft(sound), category, pitch, priority, tickLength, loop)

    constructor(
        sound: Sound,
        category: SoundCategory,
        pitch: Float,
        priority: BgmPriority,
        tickLength: Long,
        loop: Boolean
    ) : this(sound.key, category, pitch, priority, tickLength, loop)

    enum class BgmPriority {
        LOWEST,
        LOW,
        NORMAL,
        HIGH,
        HIGHEST
    }

}