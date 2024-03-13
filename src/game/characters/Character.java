package game.characters;

import game.Army;
import game.Helpers;
import game.HomeGround;
import game.PartialBattleRecord;
import game.equipments.Equipment;
import game.exeptions.DuplicateEquipmentException;
import game.exeptions.NotEnoughGoldException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Character implements Serializable {
    private final String name;
    private final Type type;
    private final Category category;
    private final int attackPriority;
    private final int defendPriority;
    private int price;
    private double baseHealth;
    private double currentHealth;
    private double baseAttack;
    private Equipment armour;
    private Equipment artefact;
    private double baseDefense;
    private double baseSpeed;


    public Character(String name, Type type, Category category, int price, double baseAttack, double baseDefense, double baseHealth, double baseSpeed, int attackPriority, int defendPriority) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.price = price;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.baseSpeed = baseSpeed;
        this.attackPriority = attackPriority;
        this.defendPriority = defendPriority;
        currentHealth = baseHealth;
        armour = null;
        artefact = null;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private void changePrice(double delta) {
        delta = Helpers.round(delta, 0);
        if (price + delta < 0) throw new NotEnoughGoldException();
        price += (int) delta;
    }

    public double getAttack(HomeGround ground) {
        double attack = baseAttack;
        if (category == Category.Highlanders && ground == HomeGround.Hillcrest) {
            attack += 1;
        } else if (category == Category.Sunchildren && ground == HomeGround.Marshland) {
            attack -= 1;
        } else if (category == Category.Sunchildren && ground == HomeGround.Desert) {
            attack += 1;
        } else if (category == Category.Mystics && ground == HomeGround.Arcane) {
            attack += 2;
        }
        return attack;
    }

    public double getDefense(HomeGround ground) {
        double defense = baseDefense;
        if (category == Category.Highlanders && ground == HomeGround.Hillcrest) {
            defense += 1;
        } else if (category == Category.Marshlanders && ground == HomeGround.Marshland) {
            defense += 2;
        } else if (category == Category.Highlanders && ground == HomeGround.Arcane) {
            defense -= 1;
        } else if (category == Category.Marshlanders && ground == HomeGround.Arcane) {
            defense -= 1;
        }
        return defense;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    private void changeHealth(double delta) {
        currentHealth += Helpers.round(delta, 1);
        if (currentHealth < 0) currentHealth = 0;
        if (currentHealth > 100) currentHealth = 100;
    }

    public void takeDamage(double amount, HomeGround ground) {
        double healthReduction = 0.5 * amount - 0.1 * getDefense(ground);
        if (healthReduction > 0) changeHealth(-healthReduction);
        takeGroundEffectOnHealth(ground, false);
    }

    public void takeHeal(double amount, HomeGround ground) {
        changeHealth(0.1 * amount);
        takeGroundEffectOnHealth(ground, false);
    }

    void takeGroundEffectOnHealth(HomeGround ground, boolean isAttacker) {
        if (currentHealth == 0) return;
        if (category == Category.Marshlanders && ground == HomeGround.Desert) changeHealth(-1);
        if (isAttacker) {
            if (category == Category.Mystics && ground == HomeGround.Arcane) changeHealth(currentHealth * 0.1);
        }
    }

    public double getSpeed(HomeGround ground) {
        double speed = baseSpeed;
        if (category == Category.Marshlanders && ground == HomeGround.Hillcrest) {
            speed -= 1;
        } else if (category == Category.Sunchildren && ground == HomeGround.Hillcrest) {
            speed -= 1;
        } else if (category == Category.Mystics && ground == HomeGround.Marshland) {
            speed -= 1;
        } else if (category == Category.Highlanders && ground == HomeGround.Arcane) {
            speed -= 1;
        } else if (category == Category.Marshlanders && ground == HomeGround.Arcane) {
            speed -= 1;
        }
        return speed;
    }

    public Equipment getArmour() {
        return armour;
    }

    public void addArmour(Equipment armour) {
        if (this.armour != null && Objects.equals(this.armour.getName(), armour.getName())) {
            throw new DuplicateEquipmentException();
        }
        discardArmour();
        this.armour = armour;
        changePrice(armour.getPrice() * (0.2));
        this.baseAttack += armour.getAttackModifier();
        this.baseDefense += armour.getDefenseModifier();
        this.baseHealth += armour.getHealthModifier();
        this.baseSpeed += armour.getSpeedModifier();
    }

    public void discardArmour() {
        if (armour != null) {
            changePrice(-armour.getPrice() * (0.2));
            this.baseAttack -= armour.getAttackModifier();
            this.baseDefense -= armour.getDefenseModifier();
            this.baseHealth -= armour.getHealthModifier();
            this.baseSpeed -= armour.getSpeedModifier();
            this.armour = null;
        }
    }

    public Equipment getArtefact() {
        return artefact;
    }

    public void addArtefact(Equipment artefact) {
        if (this.artefact != null && Objects.equals(this.artefact.getName(), artefact.getName())) {
            throw new DuplicateEquipmentException();
        }
        discardArtefact();
        this.artefact = artefact;
        changePrice(artefact.getPrice() * (0.2));
        this.baseAttack += artefact.getAttackModifier();
        this.baseDefense += artefact.getDefenseModifier();
        this.baseHealth += artefact.getHealthModifier();
        this.baseSpeed += artefact.getSpeedModifier();
    }

    public void discardArtefact() {
        if (artefact != null) {
            changePrice(-artefact.getPrice() * (0.2));
            this.baseAttack -= artefact.getAttackModifier();
            this.baseDefense -= artefact.getDefenseModifier();
            this.baseHealth -= artefact.getHealthModifier();
            this.baseSpeed -= artefact.getSpeedModifier();
            this.artefact = null;
        }
    }

    public int getAttackPriority() {
        return attackPriority;
    }

    public int getDefendPriority() {
        return defendPriority;
    }

    protected double getAdditionalTurnDamageMultiplier(HomeGround ground) {
        if (category == Category.Highlanders && ground == HomeGround.Hillcrest) {
            return 0.2;
        }
        return 0;
    }

    public ArrayList<PartialBattleRecord> engage(Army freiendlyArmy, Army enemyArmy, HomeGround ground) {
        ArrayList<PartialBattleRecord> battleRecords = new ArrayList<>();
        Character defender = enemyArmy.getDefender(ground);
        defender.takeDamage(this.getAttack(ground), ground);
        this.takeGroundEffectOnHealth(ground, true);
        battleRecords.add(new PartialBattleRecord(this, defender, this.getCurrentHealth(), defender.getCurrentHealth()));
        if (this.getCurrentHealth() > 0) {
            double damageMultiplier = this.getAdditionalTurnDamageMultiplier(ground);
            defender = enemyArmy.getDefender(ground);
            defender.takeDamage(this.getAttack(ground) * damageMultiplier, ground);
            this.takeGroundEffectOnHealth(ground, true);
            battleRecords.add(new PartialBattleRecord(this, defender, this.getCurrentHealth(), defender.getCurrentHealth()));
        }
        return battleRecords;
    }

    public void restore() {
        this.currentHealth = this.baseHealth;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        String str = String.format("%s(%s)[%s] | %dgc | attack: %.1f | defence: %.1f | health: %.1f | speed: %.1f", name, type.name(), category.name(), price, baseAttack, baseDefense, baseHealth, baseSpeed);
        if (getArmour() != null) {
            str += "\n    └─" + getArmour().toString();
        }
        if (getArtefact() != null) {
            str += "\n    └─" + getArtefact().toString();
        }
        return str;
    }
}