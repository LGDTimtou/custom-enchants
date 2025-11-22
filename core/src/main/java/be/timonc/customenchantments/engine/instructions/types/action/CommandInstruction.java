package be.timonc.customenchantments.engine.instructions.types.action;

import be.timonc.customenchantments.engine.instructions.Instruction;
import be.timonc.customenchantments.engine.instructions.InstructionCall;
import be.timonc.customenchantments.util.CustomEnchantLogFilter;
import be.timonc.customenchantments.util.Util;
import org.bukkit.Bukkit;

public class CommandInstruction extends Instruction {

    private String command;


    @Override
    protected void setValue(Object value) {
        this.command = String.valueOf(value);
    }

    @Override
    protected void execute(InstructionCall instructionCall, Runnable executeNextInstruction) {
        String executableCommand = parseNestedExpression(
                command,
                instructionCall.getPlayer(),
                instructionCall.getParameters()
        );
        Util.debug("Running command: " + executableCommand);

        CustomEnchantLogFilter.addFilter();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), executableCommand);
        CustomEnchantLogFilter.removeFilter();

        executeNextInstruction.run();
    }
}
