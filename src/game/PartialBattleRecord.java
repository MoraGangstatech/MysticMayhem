package game;

import game.characters.Character;

import java.io.Serializable;

public class PartialBattleRecord implements Serializable {
    private final Character attacker;

    private final Character defender;
    private final double attackerHealth;
    private final double defenderHealth;

    public PartialBattleRecord(Character attacker, Character defender, double attackerHealth, double defenderHealth) {
        this.attacker = attacker;
        this.defender = defender;
        this.attackerHealth = attackerHealth;
        this.defenderHealth = defenderHealth;
    }

    public BattleRecord getComplete(int turnNo, Player player) {
        return new BattleRecord(turnNo, player, this.attacker, this.defender, this.attackerHealth, this.defenderHealth);
    }
}
