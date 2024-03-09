package game.characters;

import exeptions.NotImplementedException;
import game.Army;
import game.BattleRecord;
import game.HomeGround;
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
        }
        else if (category == Category.Sunchildren && ground == HomeGround.Marshland) {
            attack -= 1;
        }
        else if (category == Category.Sunchildren && ground == HomeGround.Desert) {
            attack += 1;
        }
        else if (category == Category.Mystics && ground == HomeGround.Arcane) {
            attack += 2;
        }
        return attack;
    }

    public int getDefense(HomeGround ground) {
        int defense = baseDefense;
        if (category == Category.Highlanders && ground == HomeGround.Hillcrest) {
            defense += 1;
        }
        else if (category == Category.Marshlanders && ground == HomeGround.Marshland) {
            defense += 2;
        }
        else if (category == Category.Highlanders && ground == HomeGround.Arcane) {
            defense -= 1;
        }
        else if (category == Category.Marshlanders && ground == HomeGround.Arcane) {
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

    public void takeDamage(int amount, HomeGround ground) {
        // TODO: implement takeDamage based on the ground and category. update previousHealth
        throw new NotImplementedException();
    }

    public int getSpeed(HomeGround ground) {
        int speed = baseSpeed;
        if (category == Category.Marshlanders && ground == HomeGround.Hillcrest) {
            speed -= 1;
        }
        else if (category == Category.Sunchildren && ground == HomeGround.Hillcrest) {
            speed -= 1;
        }
        else if (category == Category.Mystics && ground == HomeGround.Marshland) {
            speed -= 1;
        }
        else if (category == Category.Highlanders && ground == HomeGround.Arcane) {
            speed -= 1;
        }
        else if (category == Category.Marshlanders && ground == HomeGround.Arcane) {
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

    public double additionalTurnDamageMultiplier(HomeGround ground) {
        // TODO: implement additionalTurnDamageMultiplier. return 0 if not eligible for an additional turn
        throw new NotImplementedException();
    }

    public ArrayList<BattleRecord> engage(Army freiendlyArmy, Army enemyArmy, HomeGround ground) {
        // TODO: implement engage based on the ground, category and additionalTurnDamageMultiplier for non-healers.
        this.takeDamage(0, ground); // attacker might take damage based on the ground
        throw new NotImplementedException();
    }

    public void restore() {
        // TODO: implement restore for Character
        throw new NotImplementedException();
    }

}