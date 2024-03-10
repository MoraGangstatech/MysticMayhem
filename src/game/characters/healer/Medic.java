package game.characters.healer;

import game.Player;
import game.characters.Category;
import game.characters.Healer;

public class Medic extends Healer {
    public Medic(Player owner) {
        super(owner, "Medic", Category.Highlanders, 125, 12, 9, 10, 7);
    }
}


