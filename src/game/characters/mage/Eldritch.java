package game.characters.mage;

import game.Player;
import game.characters.Category;
import game.characters.Mage;

public class Eldritch extends Mage {
    public Eldritch(Player owner) {
        super(owner, "Eldritch", Category.Mystics, 270, 19, 17, 18, 14);
    }
}
