package game.characters;

public abstract class Archer extends Character {
    public Archer(String name, Category category, int price, double attack, double defense, double health, double speed) {
        super(name, Type.Archer, category, price, attack, defense, health, speed, 1, 3);
    }
}


