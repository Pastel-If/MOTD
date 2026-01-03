package io.github.pastelifx.motd.plugin

import io.github.pastelifx.motd.plugin.MotdListener
import io.github.pastelifx.motd.plugin.MotdCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.CachedServerIcon
import java.io.File
import javax.imageio.ImageIO

class MotdPlugin : JavaPlugin() {

    var serverIcon: CachedServerIcon? = null
        private set

    override fun onEnable() {
        saveDefaultConfig()

        setupIconFile()
        if (config.getBoolean("use-server-icon")) {
            loadServerIcon()
        }

        server.pluginManager.registerEvents(MotdListener(this), this)
        getCommand("motd")?.setExecutor(MotdCommand(this))
        getCommand("motd")?.tabCompleter = this
    }


    fun setupIconFile() {
        val iconDir = File(dataFolder, "server-icon")
        val iconFile = File(iconDir, "server-icon.png")
        val copiedFlag = File(iconDir, ".icon_copied")

        if (copiedFlag.exists()) {
            return
        }

        if (!iconFile.exists()) {
            val input = getResource("server-icon/server-icon.png")
            if (input != null) {
                iconDir.mkdirs()
                iconFile.outputStream().use { output -> input.copyTo(output) }
                copiedFlag.createNewFile()
            }
        } else {
            copiedFlag.createNewFile()
        }
    }

    fun loadServerIcon() {
        val iconPath = config.getString("server-icon-path") ?: "server-icon.png"
        val iconFile = File(dataFolder, "server-icon/$iconPath")

        if (!config.getBoolean("use-server-icon")) {
            serverIcon = null
            return
        }

        if (iconFile.exists()) {
            try {
                val image = ImageIO.read(iconFile)
                serverIcon = Bukkit.loadServerIcon(image)
            } catch (_: Exception) {

            }
        } else {
            logger.warning("서버 아이콘 파일을 찾을 수 없습니다: server-icon/$iconPath")
        }
    }
}