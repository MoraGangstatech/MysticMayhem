package game;

import game.characters.Character;
import game.equipments.Equipment;
import game.equipments.EquipmentType;
import game.exeptions.NotEnoughGoldException;

import java.io.Serializable;


public class Player implements Serializable {
    private static int nextUserID = 1;

    private final int userID;
    private final String username;
    private final Army army;
    private String name;
    private int xp;
    private double goldCoins;

    public Player(String name, String username) {
        this.userID = nextUserID++;
        this.name = name;
        this.username = username;
        this.xp = 0;
        this.goldCoins = 500;
        this.army = new Army(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getUsername() {
        return this.username;
    }

    public int getUserID() {
        return this.userID;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public double getGoldCoins() {
        return goldCoins;
    }

    public Army getArmy() {
        return army;
    }

    public void buyCharacter(Character character) {
        if (character.getPrice() > goldCoins) throw new NotEnoughGoldException();
        army.insertCharacter(character);
        goldCoins -= character.getPrice();
    }

    public void sellCharacter(Character character) {
        army.removeCharacter(character);
        goldCoins += character.getPrice() * (0.9);
    }

    public void buyEquipment(Equipment equipment, Character character) {
        if (equipment.getPrice() > goldCoins) throw new NotEnoughGoldException();
        if (equipment.getType() == EquipmentType.Armour) {
            character.addArmour(equipment);
        } else if (equipment.getType() == EquipmentType.Artefact) {
            character.addArtefact(equipment);
        }
        goldCoins -= equipment.getPrice();
    }

}