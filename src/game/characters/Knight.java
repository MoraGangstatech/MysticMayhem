package game.characters;

public abstract class Knight extends Character {
    public Knight(String name, Category category, int price, double attack, double defense, double health, double speed) {
        super(name, Type.Knight, category, price, attack, defense, health, speed, 2, 4);
    }
}









