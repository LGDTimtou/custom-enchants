package be.timonc.customenchantments.engine.instructions.types.util;

import be.timonc.customenchantments.Main;
import be.timonc.customenchantments.engine.instructions.Instruction;
import be.timonc.customenchantments.engine.instructions.InstructionCall;
import be.timonc.customenchantments.util.Util;
import org.bukkit.Bukkit;

import java.text.DecimalFormat;

public class DelayInstruction extends Instruction {

    private String delayTime;


    @Override
    protected void setValue(Object value) {
        this.delayTime = String.valueOf(value);
    }


    @Override
    protected void execute(InstructionCall instructionCall, Runnable executeNextInstruction) {
        double delay = parseDouble(delayTime, instructionCall.getPlayer(), instructionCall.getParameters());
        DecimalFormat df = new DecimalFormat("#.00");
        Util.debug("Delaying for: " + df.format(delay) + " seconds");
        Bukkit.getScheduler()
              .runTaskLater(
                      Main.getMain(),
                      executeNextInstruction,
                      Double.valueOf(delay * 20).longValue()
              );
    }
}
