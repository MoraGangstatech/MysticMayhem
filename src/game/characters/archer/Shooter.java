package game.characters.archer;

import game.Player;
import game.characters.Archer;
import game.characters.Category;

public class Shooter extends Archer {
    public Shooter(Player owner) {
        super(owner, "Shooter", Category.Highlanders, 80, 11, 4, 6, 9);
    }
}