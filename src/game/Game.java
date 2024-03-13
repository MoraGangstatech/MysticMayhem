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
        if (players.stream().anyMatch(p -> p.getUsername().equals(player.getUsername()))) {
            throw new RuntimeException("Player with the same username already exists.");
        }
        else {
            players.add(player);
        }
    }

    public ArrayList<BattleRecord> battle() {
        // TODO: implement battle in Game.
        // count turns, call battle method of the respective armies, and, track the winner.
        // update xp & gold after the battle.
        // restore both armies
        throw new NotImplementedException();
    }


    public Player findOpponent() {
        int previousOpponentID;
        if(currentOpponent == null) {
            previousOpponentID = -1;
        }
        else{
            previousOpponentID = currentOpponent.getUserID();
        }
        currentOpponent = null;
        int playerCount = players.size();
        if (playerCount >= 2) {
            while (currentOpponent == null) {
                Player potentialOpponent = players.get(randomGen.nextInt(playerCount));
                if (potentialOpponent.getUserID() != previousOpponentID && potentialOpponent.getUserID() != activePlayer.getUserID()) {
                    currentOpponent = potentialOpponent;
                }
            }
        }
        return currentOpponent;
    }

    public void clearOpponent() {
        currentOpponent = null;
    }
}
