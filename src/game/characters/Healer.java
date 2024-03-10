package game.characters;

import game.Army;
import game.HomeGround;
import game.PartialBattleRecord;
import game.Player;

import java.util.ArrayList;

public abstract class Healer extends Character {
    public Healer(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, category, price, attack, defense, health, speed, 5, 1);
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

class Soother extends Healer {
    public Soother(Player owner) {
        super(owner, "Soother", Category.Sunchildren, 95, 10, 8, 9, 8);
    }
}

class Medic extends Healer {
    public Medic(Player owner) {
        super(owner, "Medic", Category.Highlanders, 125, 12, 9, 10, 7);
    }
}

class Alchemist extends Healer {
    public Alchemist(Player owner) {
        super(owner, "Alchemist", Category.Marshlanders, 150, 13, 13, 13, 13);
    }
}

class Saint extends Healer {
    public Saint(Player owner) {
        super(owner, "Saint", Category.Mystics, 200, 16, 14, 17, 9);
    }
}

class Lightbringer extends Healer {
    public Lightbringer(Player owner) {
        super(owner, "Lightbringer", Category.Sunchildren, 260, 17, 15, 19, 12);
    }
}