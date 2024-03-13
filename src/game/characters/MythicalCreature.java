package game.characters;

public abstract class MythicalCreature extends Character {
    public MythicalCreature(String name, Category category, int price, double attack, double defense, double health, double speed) {
        super(name, Type.MythicalCreature, category, price, attack, defense, health, speed, 3, 2);
    }
}
