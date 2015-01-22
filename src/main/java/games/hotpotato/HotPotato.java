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

package games.hotpotato;

import games.hotpotato.fsm.TurnHotPotato;
import games.hotpotato.moves.Pass;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Game;
import model.fsm.State;
import model.moves.Move;
import model.players.NotOrdered;
import model.players.Player;

/**
 * The HotPotato game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */

public class HotPotato extends Game {

	private static final int LOWER = 5;
	private static final int HIGHER = 10;
	
	private int counter;
	
	/**
	 * A new HotPotato game.
	 * @param players the players involved in the game
	 */
	public HotPotato(List<Player> players) {
		super(players);
		this.counter = (int)(Math.random() * (HIGHER-LOWER + 1)) + LOWER;
	}

	@Override
	public void initializeStates() {
		// Possible moves types for all states
		Set<Class<? extends Move<HotPotato>>> possibleMoveTypes = new HashSet<Class<? extends Move<HotPotato>>>();
		possibleMoveTypes.add(Pass.class);
		
		// State creations
		for (Player player : this.getPlayers()) {
			this.states.add(new TurnHotPotato(player,possibleMoveTypes));
		}
		
		// Possible destinations for each state
		Iterator<State<? extends Game>> it = this.states.iterator();
		while(it.hasNext()){
			State<HotPotato> state = (State<HotPotato>)it.next();
			Set<State<HotPotato>> possibleDestinations = new HashSet<State<HotPotato>>();
			Iterator<State<? extends Game>> itPossible = this.states.iterator();
			while(itPossible.hasNext()){
				State<HotPotato> statePossible = (State<HotPotato>)itPossible.next();
				if (state != statePossible){
					possibleDestinations.add(statePossible);
				}
			}
			state.setPossibleDestinations(possibleDestinations);
		}
		
		// Initial state
		this.currentState = (State<? extends Game>) this.states.toArray()[(int)(Math.random() * this.states.size())];
	}
	
	@Override
	public void initializeOrder(List<Player> players) {
		this.order = new NotOrdered(players, this);
	}
	
	/**
	 * Tests if the game is finished.
	 * @return true if the game is finished, else false
	 */
	public boolean isFinished(){
		if (this.counter == 0){
			this.winners.addAll(this.getPlayers());
			this.winners.remove(this.getCurrentPlayer());
			return true;
		}
		return false;
	}
	
	/**
	 * Passes the ball to player.
	 * @param player the player who will get the ball.
	 */
	public void passPotato(Player player) {
		this.counter--;
	}
}