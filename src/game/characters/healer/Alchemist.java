package game.characters.healer;

import game.Player;
import game.characters.Category;
import game.characters.Healer;

public class Alchemist extends Healer {
    public Alchemist(Player owner) {
        super(owner, "Alchemist", Category.Marshlanders, 150, 13, 13, 13, 13);
    }
}
