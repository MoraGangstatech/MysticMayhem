package game.characters;

import game.Player;

public abstract class Archer extends Character {
    public Archer(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, Type.Archer, category, price, attack, defense, health, speed, 1, 3);
    }
}


