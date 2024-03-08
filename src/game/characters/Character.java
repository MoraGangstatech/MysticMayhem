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


    public Character(Player owner, String name, Category category, double price, int baseAttack, int baseDefense, double baseHealth, int baseSpeed) {
        this.owner = owner;
        this.name = name;
        this.category = category;
        this.price = price;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.baseSpeed = baseSpeed;
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
        // TODO: implement getAttack based on the ground and category
        throw new NotImplementedException();
    }

    public int getDefense(HomeGround ground) {
        // TODO: implement getDefense based on the ground and category
        throw new NotImplementedException();
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
        // TODO: implement getSpeed based on the ground and category
        throw new NotImplementedException();
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
        // TODO: implement discardArmour
        throw new NotImplementedException();
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
        // TODO: implement discardArtefact
        throw new NotImplementedException();
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