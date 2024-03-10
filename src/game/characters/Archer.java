package game.characters;

import game.Player;

public abstract class Archer extends Character {
    public Archer(Player owner, String name, Category category, double price, int attack, int defense, double health, int speed) {
        super(owner, name, Type.Archer, category, price, attack, defense, health, speed, 1, 3);
    }
}

class Shooter extends Archer {
    public Shooter(Player owner) {
        super(owner, "Shooter", Category.Highlanders, 80, 11, 4, 6, 9);
    }
}

class Ranger extends Archer {
    public Ranger(Player owner) {
        super(owner, "Ranger", Category.Highlanders, 115, 14, 5, 8, 10);
    }
}

class Sunfire extends Archer {
    public Sunfire(Player owner) {
        super(owner, "Sunfire", Category.Sunchildren, 160, 15, 5, 7, 10);
    }
}

class Zing extends Archer {
    public Zing(Player owner) {
        super(owner, "Zing", Category.Sunchildren, 200, 16, 9, 11, 14);
    }
}

class Saggitarius extends Archer {
    public Saggitarius(Player owner) {
        super(owner, "Saggitarius", Category.Mystics, 230, 18, 7, 12, 17);
    }
}