package game.characters.mythicalcreature;

import game.Player;
import game.characters.Category;
import game.characters.MythicalCreature;

public class Hydra extends MythicalCreature {
    public Hydra(Player owner) {
        super(owner, "Hydra", Category.Marshlanders, 205, 12, 16, 10, 11);
    }
}
