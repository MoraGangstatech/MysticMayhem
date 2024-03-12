package game.characters.mage;

import game.Player;
import game.characters.Category;
import game.characters.Mage;

public class Illusionist extends Mage {
    public Illusionist(Player owner) {
        super(owner, "Illusionist", Category.Mystics, 120, 13, 8, 12, 14);
    }
}
