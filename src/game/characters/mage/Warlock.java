package game.characters.mage;

import game.Player;
import game.characters.Category;
import game.characters.Mage;

public class Warlock extends Mage {
    public Warlock(Player owner) {
        super(owner, "Warlock", Category.Marshlanders, 100, 12, 7, 10, 12);
    }
}
