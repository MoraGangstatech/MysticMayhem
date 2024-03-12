package game.characters;

import game.Army;
import game.HomeGround;
import game.PartialBattleRecord;
import game.Player;

import java.util.ArrayList;

public abstract class Healer extends Character {
    public Healer(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, Type.Healer, category, price, attack, defense, health, speed, 5, 1);
    }

    @Override
    public ArrayList<PartialBattleRecord> engage(Army freiendlyArmy, Army enemyArmy, HomeGround ground) {
        ArrayList<PartialBattleRecord> battleRecords = new ArrayList<>();
        Character lowestHealthAlly = freiendlyArmy.getLowestHealthCharacter(ground);
        lowestHealthAlly.takeHeal(this.getAttack(ground), ground);
        this.takeGroundEffectOnHealth(ground, true);
        battleRecords.add(new PartialBattleRecord(this, lowestHealthAlly, this.getCurrentHealth(), lowestHealthAlly.getCurrentHealth()));
        if (this.getCurrentHealth() > 0) {
            double damageMultiplier = this.getAdditionalTurnDamageMultiplier(ground);
            lowestHealthAlly = enemyArmy.getDefender(ground);
            lowestHealthAlly.takeHeal(this.getAttack(ground) * damageMultiplier, ground);
            this.takeGroundEffectOnHealth(ground, true);
            battleRecords.add(new PartialBattleRecord(this, lowestHealthAlly, this.getCurrentHealth(), lowestHealthAlly.getCurrentHealth()));
        }
        return battleRecords;
    }
}









