package game.equipments;

import java.io.Serializable;

public abstract class Equipment implements Serializable {
    private final String name;
    private final EquipmentType equipmentType;
    private final int price;
    private final int attackModifier;
    private final int defenseModifier;
    private final int healthModifier;
    private final int speedModifier;

    public Equipment(String name, EquipmentType equipmentType, int price, int attackModifier, int defenseModifier, int healthModifier, int speedModifier) {
        this.name = name;
        this.equipmentType = equipmentType;
        this.price = price;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.healthModifier = healthModifier;
        this.speedModifier = speedModifier;
    }

    public String getName() {
        return name;
    }

    public EquipmentType getType() {
        return equipmentType;
    }

    public int getPrice() {
        return price;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public int getSpeedModifier() {
        return speedModifier;
    }

    @Override
    public String toString() {
        String str = String.format("%s(%s) | %dgc", name, equipmentType.name(), price);
        if (attackModifier != 0) str += String.format(" | attack: %+d", attackModifier);
        if (defenseModifier != 0) str += String.format(" | defence: %+d", defenseModifier);
        if (healthModifier != 0) str += String.format(" | health: %+d", healthModifier);
        if (speedModifier != 0) str += String.format(" | speed: %+d", speedModifier);
        return str;
    }
}
