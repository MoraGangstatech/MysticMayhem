package game.characters.mage;

import game.Player;
import game.characters.Category;
import game.characters.Mage;

public class Conjurer extends Mage {
    public Conjurer(Player owner) {
        super(owner, "Conjurer", Category.Highlanders, 195, 18, 15, 14, 12);
    }
}
