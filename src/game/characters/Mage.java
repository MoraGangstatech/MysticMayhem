package game.characters;

import game.Player;

public abstract class Mage extends Character {
    public Mage(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, category, price, attack, defense, health, speed, 4, 5);
    }
}
