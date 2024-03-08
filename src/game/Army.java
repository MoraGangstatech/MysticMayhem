package game;

import exeptions.NotImplementedException;
import game.characters.Character;

import java.io.Serializable;
import java.util.ArrayList;

public class Army implements Serializable {
    private final ArrayList<Character> characters;
    private final Player owner;

    public Army(Player owner) {
        this.owner = owner;
        characters = new ArrayList<>();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void insertCharacter(Character character) {
        // TODO: implement insertCharacter. throw InvalidCharacterException if the character already in the army
        // Change the owner of the character
        throw new NotImplementedException();
    }

    public void removeCharacter(Character character) {
        // TODO: implement removeCharacter. throw InvalidCharacterException if the character not in the army
        throw new NotImplementedException();
    }

    public Character getHighestSpeedCharacter(HomeGround ground) {
        // TODO: implement getHighestSpeedCharacter
        throw new NotImplementedException();
    }

    public Character getLowestDefenceCharacter(HomeGround ground) {
        // TODO: implement getLowestDefenceCharacter
        throw new NotImplementedException();
    }

    public Character getLowestHealthCharacter(HomeGround ground) {
        // TODO: implement getLowestHealthCharacter
        throw new NotImplementedException();
    }

    public ArrayList<BattleRecord> battle(Army enemyArmy, HomeGround ground) {
        return getHighestSpeedCharacter(ground).engage(this, enemyArmy, ground);
    }

    public void restore() {
        // TODO: implement restore for Army. call restore for every character
        throw new NotImplementedException();
    }
}
