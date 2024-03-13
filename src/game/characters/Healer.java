package game.characters;

import game.Army;
import game.HomeGround;
import game.PartialBattleRecord;

import java.util.ArrayList;

public abstract class Healer extends Character {
    public Healer(String name, Category category, int price, double attack, double defense, double health, double speed) {
        super(name, Type.Healer, category, price, attack, defense, health, speed, 5, 1);
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









