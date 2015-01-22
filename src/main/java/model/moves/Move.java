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

package model.moves;

import java.util.Set;

import model.Game;
import model.exceptions.GameException;
import model.fsm.State;
import model.fsm.Transition;
import model.players.Player;

/**
 * Represents an abstraction of a move.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 *
 * @param <G> the game on which the move can be played.
 */
public abstract class Move<G extends Game> extends Transition<G>{
	
	/**
	 * Executes the move in the game in parameter.
	 * @param game the Game on which the Move is done
	 * @throws Exception depends of the move and the game
	 */
	public abstract void execute(G game, Player player, Set<State<G>> possibleDestinations) throws GameException;
	
	/**
	 * Describes the move.
	 * @return a description of the move
	 */
	public abstract String getDescription();
}