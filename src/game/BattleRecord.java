package game;

import game.characters.Character;

import java.io.Serializable;

public class BattleRecord implements Serializable {

    private final int turnNo;
    private final Player player;

    private final Character attacker;

    private final Character defender;
    private final double attackerHealth;
    private final double defenderHealth;

    public BattleRecord(int turnNo, Player player, Character attacker, Character defender) {
        this.turnNo = turnNo;
        this.player = player;
        this.attacker = attacker;
        this.defender = defender;
        this.attackerHealth = attacker.getCurrentHealth();
        this.defenderHealth = defender.getCurrentHealth();
    }

    @Override
    public String toString() {
        String str = String.format(
                "Turn %1$d: %2$s\n%3$s attacks %4$s\n%3$s's health: %5$.1f\n%4$s's health: %6$.1f",
                turnNo, player.getName(), defender.getName(), attacker.getName(), defenderHealth, attackerHealth
        );
        if (defenderHealth == 0) {
            str += String.format("%s died!", defender.getName());
        }
        if (attackerHealth == 0) {
            str += String.format("%s died!", attacker.getName());
        }
        return str;
    }
}
