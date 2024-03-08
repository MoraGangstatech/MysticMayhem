package game.characters;

import game.Player;

public abstract class Knight extends Character {
    public Knight(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, category, price, attack, defense, health, speed);
    }
}

class Squire extends Knight {
    public Squire(Player owner) {
        super(owner, "Squire", Category.Marshlanders, 85, 8, 9, 7, 8);
    }
}

class Cavalier extends Knight {
    public Cavalier(Player owner) {
        super(owner, "Cavalier", Category.Highlanders, 110, 10, 12, 7, 10);
    }
}

class Templar extends Knight {
    public Templar(Player owner) {
        super(owner, "Templar", Category.Sunchildren, 155, 14, 16, 12, 12);
    }
}

class Zoro extends Knight {
    public Zoro(Player owner) {
        super(owner, "Zoro", Category.Highlanders, 180, 17, 16, 13, 14);
    }
}

class Swiftblade extends Knight {
    public Swiftblade(Player owner) {
        super(owner, "Swiftblade", Category.Marshlanders, 250, 18, 20, 17, 13);
    }
}