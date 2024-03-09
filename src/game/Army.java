package game;

import exeptions.NotImplementedException;
import game.characters.Character;
import game.exeptions.InvalidCharacterException;

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
        if (characters.contains(character)) {
            throw new InvalidCharacterException();
        }
        else{
            characters.add(character);
        }

    }

    public void removeCharacter(Character character) {
        if (!characters.contains(character)) {
            throw new InvalidCharacterException();
        }
        else{
            characters.remove(character);
        }

    }

    public Character getHighestSpeedCharacter(HomeGround ground) {
        Character highestSpeedCharacter = characters.get(0);
        for (Character character : characters) {
            if (character.getSpeed(ground) > highestSpeedCharacter.getSpeed(ground)) {
                highestSpeedCharacter = character;
            }
        }
        return highestSpeedCharacter;

    }

    public Character getLowestDefenceCharacter(HomeGround ground) {
        Character lowestDefenceCharacter = characters.get(0);
        for (Character character : characters) {
            if (character.getDefense(ground) < lowestDefenceCharacter.getDefense(ground)) {
                lowestDefenceCharacter = character;
            }
        }
        return lowestDefenceCharacter;
    }

    public Character getLowestHealthCharacter(HomeGround ground) {
        Character lowestHealthCharacter = characters.get(0);
        for (Character character : characters) {
            if (character.getCurrentHealth() < lowestHealthCharacter.getCurrentHealth()) {
                lowestHealthCharacter = character;
            }
        }
        return lowestHealthCharacter;
    }

    public ArrayList<BattleRecord> battle(Army enemyArmy, HomeGround ground) {
        return getHighestSpeedCharacter(ground).engage(this, enemyArmy, ground);
    }

    public void restore() {
        for (Character character : characters) {
            character.restore();
        }

    }
}
