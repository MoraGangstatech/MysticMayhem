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
    private int goldCoins;
    private HomeGround homeGround;

    public Player(String name, String username) {
        this.userID = nextUserID++;
        this.name = name;
        this.username = username;
        this.xp = 0;
        this.goldCoins = 500;
        this.army = new Army();
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

    public void changeXp(int delta) {
        this.xp += delta;
    }

    public int getGoldCoins() {
        return this.goldCoins;
    }

    public void changeGoldCoins(double delta) {
        delta = Helpers.round(delta, 0);
        if (goldCoins + delta < 0) throw new NotEnoughGoldException();
        goldCoins += (int) delta;
    }

    public Army getArmy() {
        return army;
    }

    public HomeGround getHomeGround() {
        return homeGround;
    }

    public void setHomeGround(HomeGround ground) {
        this.homeGround = ground;
    }

    public void buyCharacter(Character character) {
        if (character.getPrice() > goldCoins) throw new NotEnoughGoldException();
        army.insertCharacter(character);
        changeGoldCoins(-character.getPrice());
    }

    public void sellCharacter(Character character) {
        army.removeCharacter(character);
        changeGoldCoins(character.getPrice() * (0.9));
    }

    public void buyEquipment(Equipment equipment, Character character) {
        if (equipment.getPrice() > goldCoins) throw new NotEnoughGoldException();
        if (equipment.getType() == EquipmentType.Armour) {
            character.addArmour(equipment);
        } else if (equipment.getType() == EquipmentType.Artefact) {
            character.addArtefact(equipment);
        }
        changeGoldCoins(-equipment.getPrice());
    }

    public void discardEquipment(EquipmentType equipmentType, Character character) {
        if (equipmentType == EquipmentType.Armour) {
            character.discardArmour();
        } else if (equipmentType == EquipmentType.Artefact) {
            character.discardArtefact();
        }
    }

}