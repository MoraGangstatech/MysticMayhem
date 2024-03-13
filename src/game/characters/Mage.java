package game.characters;

public abstract class Mage extends Character {
    public Mage(String name, Category category, int price, double attack, double defense, double health, double speed) {
        super(name, Type.Mage, category, price, attack, defense, health, speed, 4, 5);
    }
}
