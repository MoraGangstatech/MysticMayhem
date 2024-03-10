package game.characters.mythicalcreature;

import game.Player;
import game.characters.Category;
import game.characters.MythicalCreature;

public class Basilisk extends MythicalCreature {
    public Basilisk(Player owner) {
        super(owner, "Basilisk", Category.Marshlanders, 165, 15, 11, 10, 12);
    }
}
