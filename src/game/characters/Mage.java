package game.characters;

import game.Player;

public abstract class Mage extends Character {
    public Mage(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, category, price, attack, defense, health, speed, 4, 5);
    }
}

class Warlock extends Mage {
    public Warlock(Player owner) {
        super(owner, "Warlock", Category.Marshlanders, 100, 12, 7, 10, 12);
    }
}

class Illusionist extends Mage {
    public Illusionist(Player owner) {
        super(owner, "Illusionist", Category.Mystics, 120, 13, 8, 12, 14);
    }
}

class Enchanter extends Mage {
    public Enchanter(Player owner) {
        super(owner, "Enchanter", Category.Highlanders, 160, 16, 10, 13, 16);
    }
}

class Conjurer extends Mage {
    public Conjurer(Player owner) {
        super(owner, "Conjurer", Category.Highlanders, 195, 18, 15, 14, 12);
    }
}

class Eldritch extends Mage {
    public Eldritch(Player owner) {
        super(owner, "Eldritch", Category.Mystics, 270, 19, 17, 18, 14);
    }
}