package game.characters.archer;

import game.Player;
import game.characters.Archer;
import game.characters.Category;

public class Ranger extends Archer {
    public Ranger(Player owner) {
        super(owner, "Ranger", Category.Highlanders, 115, 14, 5, 8, 10);
    }
}
