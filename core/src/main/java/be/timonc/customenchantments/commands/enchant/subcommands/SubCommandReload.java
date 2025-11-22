package be.timonc.customenchantments.commands.enchant.subcommands;

import be.timonc.customenchantments.commands.Command;
import be.timonc.customenchantments.commands.SubCommand;
import be.timonc.customenchantments.util.enums.File;
import be.timonc.customenchantments.util.enums.Message;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class SubCommandReload extends SubCommand {
    public SubCommandReload(Command command) {
        super(command, "reload", 0, Message.COMMANDS__RELOAD__USAGE.get());
    }

    @Override
    public List<String> getTabValues(CommandSender commandSender, String[] args) {
        return List.of();
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Arrays.stream(File.values()).forEach(File::reloadConfig);
        commandSender.sendMessage(Message.COMMANDS__RELOAD__SUCCESS.get());
    }
}
