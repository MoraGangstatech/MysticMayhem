package game;

import exeptions.NotImplementedException;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final ArrayList<Player> players;
    private final Random randomGen;
    private Player activePlayer;
    private Player currentOpponent;

    public Game(ArrayList<Player> players) {
        this.players = players;
        activePlayer = null;
        currentOpponent = null;
        randomGen = new Random();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addNewPlayer(Player player) {
        // TODO: implement addNewPlayer. Throw an exception if the player is a duplicate
        throw new NotImplementedException();
    }

    public ArrayList<BattleRecord> battle() {
        // TODO: implement battle in Game.
        // count turns, call battle method of the respective armies, and, track the winner.
        // update xp & gold after the battle.
        // restore both armies
        throw new NotImplementedException();
    }

    public Player findOpponent() {
        int previousOpponentID = currentOpponent.getUserID();
        currentOpponent = null;
        int playerCount = players.size();
        if (playerCount > 2) {
            while (currentOpponent == null) {
                currentOpponent = players.get(randomGen.nextInt() % playerCount);
                if (currentOpponent.getUserID() == previousOpponentID || currentOpponent.getUserID() == activePlayer.getUserID()) {
                    currentOpponent = null;
                }
            }
        }
        return currentOpponent;
    }

    public void clearOpponent() {
        currentOpponent = null;
    }
}