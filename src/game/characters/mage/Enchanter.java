package game.characters.mage;

import game.Player;
import game.characters.Category;
import game.characters.Mage;

public class Enchanter extends Mage {
    public Enchanter(Player owner) {
        super(owner, "Enchanter", Category.Highlanders, 160, 16, 10, 13, 16);
    }
}
