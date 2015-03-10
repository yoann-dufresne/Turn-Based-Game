/*******************************************************************************
 * This application simulates turn-based games hosted on a server.
 *     Copyright (C) 2014 
 *     Initiators : Fabien Delecroix and Yoann Dufresne
 *     Developpers : Raphael Bauduin and Celia Cacciatore
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package model.fsm;

import java.util.Set;

import model.Game;
import model.exceptions.GameException;
import model.exceptions.IllegalMoveException;
import model.moves.Move;
import model.players.Player;

/**
 * Represents an abstraction of the different states of a game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin - Robin Lewandowicz - Guillaume Ferlin
 *
 * @param <G> the game of which this is a state.
 */
public abstract class SimpleState<G extends Game> extends State<G>{

	/**
	 * The player who has the right to play
	 */
	protected Player currentPlayer;

	/**
	 * the possible States accessible from this State
	 */
	protected Set<State<G>> possibleDestinations;

	/**
	 * the different moves that can be played in this State
	 */
	protected Set<Class<? extends Move<G>>> possibleMoveTypes;

	/**
	 * if it's true, the State is a final state, the game can over at this state 
	 * (multiple final state is possible)
	 */
	protected boolean isFinalState;



	/**
	 * @param currentPlayer the player who has the right to play
	 * @param possibleMoveTypes the different moves that can be played in this State
	 */
	public SimpleState(boolean isFinalState, Player currentPlayer, Set<Class<? extends Move<G>>> possibleMoveTypes) {
		super();
		this.isFinalState = isFinalState;
		this.currentPlayer = currentPlayer;
		this.possibleMoveTypes = possibleMoveTypes;
	}

	/**
	 * Returns the current player.
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}


	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Sets the new possible destinations.
	 * @param possibleDestinations the new possible destinations
	 */
	public void setPossibleDestinations(Set<State<G>> possibleDestinations) {
		this.possibleDestinations = possibleDestinations;
	}

	/**
	 * Checks if the move can be played and if this is this player's turn.
	 * @param move the move to check
	 * @return true if the move can be played and this is this player's turn, else false
	 */
	public boolean check(Move<G> move) {
		for (Class<? extends Move<G>> moveType : this.possibleMoveTypes) {
			if (move.getClass() == (moveType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Plays the move executed by player.
	 * @param game the game on which the move is played
	 * @param move the move to execute
	 * @param player the player who played the move
	 * @return the new current state of the game
	 * @throws Exception exceptions thrown when playing the move, depend of the game
	 */
	public State<G> play(Game game, Move<? extends Game> move, Player player) throws GameException{
		
		try {
			Move<G> newMove= (Move<G>) move;
			
			if (this.check(newMove)){

				
				newMove.execute((G)game, player, this.possibleDestinations);
				return newMove.getNext();
				
			} else {
				throw new IllegalMoveException();
			}
		} catch (ClassCastException e) {
			throw new IllegalMoveException();
		}
		
	}


	@Override
	public boolean isFinalState() {
		return isFinalState;
	}


}