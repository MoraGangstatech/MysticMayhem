package game.characters;

import game.Player;

public abstract class Knight extends Character {
    public Knight(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, Type.Knight, category, price, attack, defense, health, speed, 2, 4);
    }
}









