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

package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.fsm.State;
import model.moves.Move;
import model.players.Player;
import model.players.PlayersOrder;

/**
 * Represents an abstraction of a game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */
public abstract class Game {
	
	protected Set<Player> winners;
	protected State<? extends Game> currentState;
	protected Set<State<? extends Game>> states;
	protected PlayersOrder order;
	
	/**
	 * Initializes the game.
	 * @param players all the players involved in the games.
	 */
	public Game(List<Player> players) {
		this.states = new HashSet<State<? extends Game>>();
		this.winners = new HashSet<Player>();
		this.initializeOrder(players);
		this.initializeStates();
	}
	
	/**
	 * Returns the current state of the game.
	 * @return the current state
	 */
	public State<? extends Game> getCurrentState() {
		return this.currentState;
	}

	/**
	 * Sets the new state.
	 * @param state the new state
	 */
	public void setCurrentState(State<? extends Game> state){
		this.currentState = state;
	}
	
	/**
	 * Returns the player whose turn it is.
	 * @return the current player
	 */
	public Player getCurrentPlayer(){
		return this.currentState.getCurrentPlayer();
	}
	
	/**
	 * Returns the list of all the players in the game.
	 * @return the list of all of the players.
	 */
	public List<Player> getPlayers() {
		return this.order.getPlayers();
	}
	
	/**
	 * Initializes the different states of the game's automaton. 
	 */
	public abstract void initializeStates();
	
	/**
	 * Initializes the players' order.
	 * @param players the list of players
	 */
	public abstract void initializeOrder(List<Player> players);
	
	/**
	 * Test if the game is over (the game is over when the current 
	 * state is a final state, and the game rules (isFinished) say
	 * that the end of the game)
	 * 
	 * @return true if the game is over
	 */
	public boolean isOver(){
		if(currentState.isFinalState())
			return isFinished();
		else
			return false;
	}
	
	/**
	 * Tests if the game is finished or not.
	 * @return true if the game is finished, else false.
	 */
	protected abstract boolean isFinished();
	
	/**
	 * Returns the players who won the game.
	 * @return a set of players
	 */
	public Set<Player> getWinners() {
		return this.winners;
	}

	/**
	 * Gives a new order of players.
	 * By default, the order doesn't change.
	 * @return
	 */
	public List<Player> newOrder() {
		return this.order.getPlayers();
	}
	
	/**
	 * Plays a move in the game.
	 * @param move the move to play in the game
	 * @param player the player whose turn it is
	 * @throws Exception depending of the game and the move
	 */
	public void play(Move<? extends Game> move, Player player) throws Exception{
		State<? extends Game> nextState = this.currentState.play(this, move, player);
		this.setCurrentState(nextState);
	}
}