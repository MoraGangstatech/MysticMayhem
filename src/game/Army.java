package game;

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
        for (Character includedCharacter : characters) {
            if (character.getType() == includedCharacter.getType())
                throw new InvalidCharacterException();
        }
        characters.add(character);


    }

    public void removeCharacter(Character character) {
        if (!characters.contains(character)) {
            throw new InvalidCharacterException();
        } else {
            characters.remove(character);
        }

    }

    public Character getAttacker(HomeGround ground) {
        Character attacker = null;
        for (Character character : characters) {
            if (character.getCurrentHealth() == 0) { // check whether the character is dead.
                continue;
            }
            if (attacker == null || character.getSpeed(ground) > attacker.getSpeed(ground)) { // getting Character with the highest speed
                attacker = character;
            } else if (character.getSpeed(ground) == attacker.getSpeed(ground)) { // getting Characters has same speed then attaker will be selected base on priority
                if (character.getAttackPriority() > attacker.getAttackPriority()) {
                    attacker = character;
                }
            }
        }
        return attacker; // if this function return null, then all characters are dead.

    }

    public Character getDefender(HomeGround ground) {
        Character defender = null;
        for (Character character : characters) {
            if (character.getCurrentHealth() == 0) { // check whether the character is dead.
                continue;
            }
            if (defender == null || character.getDefense(ground) < defender.getDefense(ground)) { // getting Character with the lowest defence
                defender = character;
            } else if (character.getDefense(ground) == defender.getDefense(ground)) {// getting Characters has same defence then defender will be selected base on priority
                if (character.getDefendPriority() > defender.getDefendPriority()) {
                    defender = character;
                }
            }
        }
        return defender; // if this function return null, then all characters are dead.
    }

    public Character getLowestHealthCharacter(HomeGround ground) {
        Character lowestHealthCharacter = null;
        for (Character character : characters) {
            if (character.getCurrentHealth() == 0) { // check whether the character is dead.
                continue;
            }
            if (lowestHealthCharacter == null || character.getCurrentHealth() < lowestHealthCharacter.getCurrentHealth()) {
                lowestHealthCharacter = character;
            }
        }
        return lowestHealthCharacter; // if this function return null, then all characters are dead.
    }

    public ArrayList<PartialBattleRecord> battle(Army enemyArmy, HomeGround ground) {
        return getAttacker(ground).engage(this, enemyArmy, ground);
    }

    public void restore() {
        for (Character character : characters) {
            character.restore();
        }

    }
}
