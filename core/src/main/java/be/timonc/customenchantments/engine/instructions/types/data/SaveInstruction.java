package be.timonc.customenchantments.engine.instructions.types.data;

import be.timonc.customenchantments.Main;
import be.timonc.customenchantments.engine.instructions.Instruction;
import be.timonc.customenchantments.engine.instructions.InstructionCall;
import be.timonc.customenchantments.util.Util;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.function.Supplier;

public class SaveInstruction extends Instruction {

    private boolean persistent;
    private String identifier;
    private String value;


    @Override
    protected void setValue(Object value) {
        try {
            Map<String, Object> values = (Map<String, Object>) value;
            this.persistent = (boolean) values.get("persistent");
            this.identifier = (String) values.get("identifier");
            this.value = (String) values.get("value");
        } catch (Exception e) {
            Util.error("Error while parsing 'save' instruction: " + value);
            Util.error(e.getMessage());
        }
    }

    @Override
    protected void execute(InstructionCall instructionCall, Runnable executeNextInstruction) {
        Player player = instructionCall.getPlayer();
        Map<String, Supplier<String>> parameters = instructionCall.getParameters();

        String parsedIdentifier = parseNestedExpression(identifier, player, parameters);
        String parsedValue = parseNestedExpression(value, player, parameters);
        
        Main.getValueManager().setValue(parsedIdentifier, parsedValue, persistent);
        parameters.put(parsedIdentifier, () -> parsedValue);

        executeNextInstruction.run();
    }
}
