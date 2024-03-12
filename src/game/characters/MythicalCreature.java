package game.characters;

import game.Player;

public abstract class MythicalCreature extends Character {
    public MythicalCreature(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, Type.MythicalCreature, category, price, attack, defense, health, speed, 3, 2);
    }
}
