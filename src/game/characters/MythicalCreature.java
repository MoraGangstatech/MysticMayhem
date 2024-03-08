package game.characters;

import game.Player;

public abstract class MythicalCreature extends Character {
    public MythicalCreature(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, category, price, attack, defense, health, speed);
    }
}

class Dragon extends MythicalCreature {
    public Dragon(Player owner) {
        super(owner, "Dragon", Category.Sunchildren, 120, 12, 14, 15, 8);
    }
}

class Basilisk extends MythicalCreature {
    public Basilisk(Player owner) {
        super(owner, "Basilisk", Category.Marshlanders, 165, 15, 11, 10, 12);
    }
}

class Hydra extends MythicalCreature {
    public Hydra(Player owner) {
        super(owner, "Hydra", Category.Marshlanders, 205, 12, 16, 10, 11);
    }
}

class Phoenix extends MythicalCreature {
    public Phoenix(Player owner) {
        super(owner, "Phoenix", Category.Sunchildren, 275, 17, 13, 17, 19);
    }
}

class Pegasus extends MythicalCreature {
    public Pegasus(Player owner) {
        super(owner, "Pegasus", Category.Mystics, 340, 14, 18, 20, 20);
    }
}