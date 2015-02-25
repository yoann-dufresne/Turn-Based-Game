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

import model.Game;
import model.exceptions.GameException;
import model.moves.Move;
import model.players.Player;

/**
 * @author Robin Lewandowicz, Guillaume Ferlin
 *
 */
public abstract class SuperState<G extends Game> implements State<G> {

	


	
	
	/**
	 * Returns the current player.
	 * @return the current player
	 */
	public Player getCurrentPlayer(){
		// TODO NYI
		return null;
	}
	
	/**
	 * Plays the move executed by player.
	 * @param game the game on which the move is played
	 * @param move the move to execute
	 * @param player the player who played the move
	 * @throws Exception exceptions thrown when playing the move, depend of the game
	 */
	public void play(Game game, Move<? extends Game> move, Player player) throws GameException{
		// TODO NYI
	}


	
	
	
}
