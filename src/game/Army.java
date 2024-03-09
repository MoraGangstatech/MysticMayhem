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

    public Character getAttacker(HomeGround ground) {
        Character attacker = characters.get(0);
        for (Character character : characters) {
            if (character.getSpeed(ground) >= attacker.getSpeed(ground)) {
                if (character.getAttackPriority() < attacker.getAttackPriority()) continue;
                attacker = character;
            }
        }
        return attacker;

    }

    public Character getDefender(HomeGround ground) {
        Character defender = characters.get(0);
        for (Character character : characters) {
            if (character.getDefense(ground) <= defender.getDefense(ground)) {
                if (character.getDefendPriority() < defender.getDefendPriority()) continue;
                defender = character;
            }
        }
        return defender;
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
        return getAttacker(ground).engage(this, enemyArmy, ground);
    }

    public void restore() {
        for (Character character : characters) {
            character.restore();
        }

    }
}
