package be.timonc.customenchantments.engine.instructions.types.data;

import be.timonc.customenchantments.Main;
import be.timonc.customenchantments.engine.instructions.Instruction;
import be.timonc.customenchantments.engine.instructions.InstructionCall;
import be.timonc.customenchantments.util.Util;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.function.Supplier;

public class LoadInstruction extends Instruction {

    private String identifier;
    private String defaultValue;

    @Override
    protected void setValue(Object value) {
        try {
            Map<String, String> values = (Map<String, String>) value;
            this.identifier = values.get("identifier");
            this.defaultValue = values.getOrDefault("default_value", "");
        } catch (Exception e) {
            Util.error("Error while parsing 'load' instruction: " + value);
            Util.error(e.getMessage());
        }
    }

    @Override
    protected void execute(InstructionCall instructionCall, Runnable executeNextInstruction) {
        Player player = instructionCall.getPlayer();
        Map<String, Supplier<String>> parameters = instructionCall.getParameters();
        String parsedIdentifier = parseNestedExpression(identifier, player, parameters);

        Main.getValueManager().setDefaultValue(parsedIdentifier, defaultValue);
        parameters.putIfAbsent(parsedIdentifier, () -> defaultValue);

        executeNextInstruction.run();
    }
}
