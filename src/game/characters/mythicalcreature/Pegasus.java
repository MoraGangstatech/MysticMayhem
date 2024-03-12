package game.characters.mythicalcreature;

import game.Player;
import game.characters.Category;
import game.characters.MythicalCreature;

public class Pegasus extends MythicalCreature {
    public Pegasus(Player owner) {
        super(owner, "Pegasus", Category.Mystics, 340, 14, 18, 20, 20);
    }
}