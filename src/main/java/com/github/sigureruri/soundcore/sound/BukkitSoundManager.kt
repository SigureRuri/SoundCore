package com.github.sigureruri.soundcore.sound

import com.github.sigureruri.soundcore.SoundCore
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class BukkitSoundManager : SoundManager {

    private val playerBgmTasks = mutableMapOf<UUID, BgmRunnable>()

    override fun playSoundEffect(player: Player, location: Location, soundEffect: SoundEffect) {
        player.playSound(location, soundEffect.sound.toString(), soundEffect.category, soundEffect.volume, soundEffect.pitch)
    }

    override fun playBackgroundMusic(player: Player, location: Location, bgm: BackgroundMusic) {
        val currentTask = playerBgmTasks[player.uniqueId]
        if (currentTask != null && !currentTask.isCancelled) {
            // 優先度が同じもしくは低い場合は再生しない
            if (bgm.priority.ordinal <= currentTask.bgm.priority.ordinal) return

            player.stopSound(currentTask.bgm.sound.toString())
        }

        val runnable = if (bgm.loop) {
            LoopBgmRunnable(player, location, bgm).apply { startRunnable() }
        } else {
            OneTimeBgmRunnable(player, location, bgm).apply { startRunnable() }
        }
        playerBgmTasks[player.uniqueId] = runnable
    }

    override fun stopBackgroundMusic(player: Player, bgm: BackgroundMusic) {
        val currentTask = playerBgmTasks[player.uniqueId]
        if (currentTask != null && !currentTask.isCancelled) {
            if (bgm.sound == currentTask.bgm.sound) {
                player.stopSound(bgm.sound.toString())
                currentTask.cancel()
            }
        }
    }

    sealed class BgmRunnable(val player: Player, val location: Location, val bgm: BackgroundMusic) : BukkitRunnable() {
        open fun startRunnable() {}
    }

    class OneTimeBgmRunnable(player: Player, location: Location, bgm: BackgroundMusic) : BgmRunnable(player, location, bgm) {
        override fun run() {
            cancel()
        }

        override fun startRunnable() {
            player.playSound(location, bgm.sound.toString(), bgm.category, Float.MAX_VALUE, bgm.pitch)
            runTaskLater(SoundCore.plugin, bgm.tickLength)
        }
    }

    class LoopBgmRunnable(player: Player, location: Location, bgm: BackgroundMusic) : BgmRunnable(player, location, bgm) {
        override fun run() {
            player.playSound(location, bgm.sound.toString(), bgm.category, Float.MAX_VALUE, bgm.pitch)
        }

        override fun startRunnable() {
            runTaskTimer(SoundCore.plugin, 0, bgm.tickLength)
        }
    }

}