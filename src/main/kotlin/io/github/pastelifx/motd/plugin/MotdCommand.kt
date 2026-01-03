package io.github.pastelifx.motd.plugin

import io.github.pastelifx.motd.plugin.MotdPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class MotdCommand(private val plugin: MotdPlugin) : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("motd.reload")) {
            sender.sendMessage("§c이 명령어를 사용할 권한이 없습니다.")
            return true
        }
        if (args.isNotEmpty() && args[0].equals("reload", ignoreCase = true)) {
            plugin.reloadConfig()
            plugin.loadServerIcon()

            sender.sendMessage("§aMOTD 설정과 서버 아이콘이 리로드되었습니다!")
            return true
        }
        sender.sendMessage("§c잘못된 명령어입니다. /motd reload")
        return false
    }
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        if (args.size == 1) {
            return listOf("reload")
                .filter { it.startsWith(args[0], ignoreCase = true) }
        }
        return emptyList()
    }
}
