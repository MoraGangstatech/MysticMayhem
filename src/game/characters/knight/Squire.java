package game.characters.knight;

import game.Player;
import game.characters.Category;
import game.characters.Knight;

public class Squire extends Knight {
    public Squire(Player owner) {
        super(owner, "Squire", Category.Marshlanders, 85, 8, 9, 7, 8);
    }
}
