package game.characters.archer;

import game.Player;
import game.characters.Archer;
import game.characters.Category;

public class Sunfire extends Archer {
    public Sunfire(Player owner) {
        super(owner, "Sunfire", Category.Sunchildren, 160, 15, 5, 7, 10);
    }
}