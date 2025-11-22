package be.timonc.customenchantments.engine.instructions.types.util;

import be.timonc.customenchantments.engine.instructions.Instruction;
import be.timonc.customenchantments.engine.instructions.InstructionCall;

public class DummyInstruction extends Instruction {

    @Override
    protected void setValue(Object value) {
    }

    @Override
    protected void execute(InstructionCall instructionCall, Runnable executeNextInstruction) {
        executeNextInstruction.run();
    }
}
