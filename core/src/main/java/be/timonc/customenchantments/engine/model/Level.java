package be.timonc.customenchantments.engine.model;

import be.timonc.customenchantments.engine.instructions.Instruction;

import java.util.List;
import java.util.Queue;

public record Level(double cooldown, String cooldownMessage, double chance, boolean cancelEvent,
                    Queue<Instruction> instructions, List<String> cleanupCommands) {
}
