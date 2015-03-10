/*******************************************************************************
 * This application simulates turn-based games hosted on a server.
 *     Copyright (C) 2014 
 *     Initiators : Fabien Delecroix and Yoann Dufresne
 *     Developpers :  Celia Cacciatore, Guillaume Ferlin, Raphael Bauduin, Robin Lewandowicz and Yassine Badache
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import model.Game;
import model.exceptions.GameException;
import model.moves.Move;
import model.players.Player;

/**
 * @author Robin Lewandowicz, Guillaume Ferlin
 *
 */
public abstract class SuperState<G extends Game> extends State<G> {

	
	private Set<SimpleState<G>> statesSet;
	
	private SimpleState<G> firstState;
	private SimpleState<G> lastState;
	
	private SimpleState<G> currentSimpleState;
	
	private LinkedList<Player> playerOrder;
	
	private Iterator<Player> orderIterator;

	
	
	/**
	 * @param firstState
	 * @param lastState
	 * @param currentState
	 */
	public SuperState(Set<SimpleState<G>> statesSet, SimpleState<G> firstState, SimpleState<G> lastState, LinkedList<Player> playerOrder) {
		
		this.statesSet = statesSet;
		
		this.firstState = firstState;
		this.lastState = lastState;
		
		this.currentSimpleState = firstState;
		
		this.playerOrder = playerOrder;
		
		this.initializeIterator();
	}
	
	private void initializeIterator(){
		if( ! playerOrder.isEmpty()) {
			this.orderIterator = playerOrder.iterator();
			this.setCurrentPlayer(orderIterator.next());
		}
	}

	/**
	 * Returns the current player.
	 * @return the current player
	 */
	public Player getCurrentPlayer(){
		return this.currentSimpleState.getCurrentPlayer();
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
		
		if( this.currentSimpleState == this.firstState && this.getCurrentPlayer().equals(playerOrder.getFirst()) ) {
			
			// at the first passage on the super state, it needs to initialize its iterator pointers
			this.initializeIterator();
			
		}
		
		// run the move
		State<G> nextState = currentSimpleState.play(game, move, player);
		
		// next, set the pointers (current state and player)
		if(this.currentSimpleState != this.lastState){
			
			this.currentSimpleState = (SimpleState<G>)nextState;
			return this;
			
		}else{
			
			// if the current state is the last state of this SuperState
			
			if(this.orderIterator.hasNext()){
				
				// in this case, the super state restart at its first state
				// with the new current player
				this.setCurrentPlayer( this.orderIterator.next() );
				this.currentSimpleState = this.firstState;
				return this;
				
			}else{
				
				// in this case, the next state is outside the super state
				this.currentSimpleState = this.firstState;
				this.initializeIterator();
				return nextState;
				
			}
			
			
			
		}
		
		
		
	}

	
	
	
	/* (non-Javadoc)
	 * @see model.fsm.State#setCurrentPlayer(model.players.Player)
	 */
	@Override
	public void setCurrentPlayer(Player player) {
		for(SimpleState<G> st : this.statesSet){
			st.setCurrentPlayer(player);
		}
	}

	/* (non-Javadoc)
	 * @see model.fsm.State#setPossibleDestinations(java.util.Set)
	 */
	@Override
	public void setPossibleDestinations(Set<State<G>> possibleDestinations) {
		// TODO Auto-generated method stub
		
	}

	

	/**
	 * For the moment, only Simple State can be final
	 */
	@Override
	public boolean isFinalState() {
		return false;
	}


	
	
	
	
	
}
