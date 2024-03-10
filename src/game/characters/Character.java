package game.characters;

import exeptions.NotImplementedException;
import game.Army;
import game.HomeGround;
import game.PartialBattleRecord;
import game.Player;
import game.equipments.Equipment;
import game.exeptions.DuplicateEquipmentException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Character implements Serializable {
    private final String name;
    private final Category category;
    protected Player owner;
    private double price;
    private int baseAttack;
    private int baseDefense;
    private double baseHealth;
    private double currentHealth;
    private double previousHealth;
    private int baseSpeed;
    private Equipment armour;
    private Equipment artefact;
    private int attackPriority;
    private int defendPriority;


    public Character(Player owner, String name, Category category, double price, int baseAttack, int baseDefense, double baseHealth, int baseSpeed, int attackPriority, int defendPriority) {
        this.owner = owner;
        this.name = name;
        this.category = category;
        this.price = price;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.baseSpeed = baseSpeed;
        this.attackPriority = attackPriority;
        this.defendPriority = defendPriority;
        currentHealth = baseHealth;
        previousHealth = baseHealth;
        armour = null;
        artefact = null;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAttack(HomeGround ground) {
        int attack = baseAttack;
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

    public int getDefense(HomeGround ground) {
        int defense = baseDefense;
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

    public double getPreviousHealth() {
        return previousHealth;
    }

    public void takeDamage(double amount, HomeGround ground) {
        double healthReduction = 0.5 * amount - 0.1 * getDefense(ground);
        if (healthReduction > 0) currentHealth -= healthReduction;
        if (currentHealth > 0) {
            takeGroundEffectOnHealth(ground, false);
        }
        if (currentHealth < 0) currentHealth = 0;
    }

    public void takeHeal(double amount, HomeGround ground) {
        currentHealth += 0.1 * amount;
        if (currentHealth > 0) {
            takeGroundEffectOnHealth(ground, false);
        }
        if (currentHealth < 0) currentHealth = 0;
    }

    void takeGroundEffectOnHealth(HomeGround ground, boolean isAttacker) {
        if (category == Category.Marshlanders && ground == HomeGround.Desert) currentHealth -= 1;
        if (isAttacker) {
            if (category == Category.Mystics && ground == HomeGround.Arcane) currentHealth *= 1.1;
        }
    }

    public int getSpeed(HomeGround ground) {
        int speed = baseSpeed;
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
        this.price += armour.getPrice() * (0.2);
        this.baseAttack += armour.getAttackModifier();
        this.baseDefense += armour.getDefenseModifier();
        this.baseHealth += armour.getHealthModifier();
        this.baseSpeed += armour.getSpeedModifier();
    }

    public void discardArmour() {
        if (armour != null) {
            this.price -= armour.getPrice() * (0.2);
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
        this.price += artefact.getPrice() * (0.2);
        this.baseAttack += artefact.getAttackModifier();
        this.baseDefense += artefact.getDefenseModifier();
        this.baseHealth += artefact.getHealthModifier();
        this.baseSpeed += artefact.getSpeedModifier();
    }

    public void discardArtefact() {
        if (artefact != null) {
            this.price -= artefact.getPrice() * (0.2);
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

    public double getAdditionalTurnDamageMultiplier(HomeGround ground) {
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

}