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

package games.tictactoe.moves;

import games.tictactoe.TicTacToe;

import java.util.Set;

import model.exceptions.GameException;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;

/**
 * Puts a symbol in the grid of a TicTacToe game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */
public class PutSymbol extends Move<TicTacToe> {
	
	private int x;
	private int y;

	/**
	 * A move which puts a symbol in the grid.
	 * @param x the abscissa of the grid
	 * @param y the ordinate of the grid
	 */
	public PutSymbol(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(TicTacToe game, Player player, Set<State<TicTacToe>> possibleDestinations) throws GameException {
		game.putSymbol(this.x, this.y, player);
		for (State<TicTacToe> stateTicTacToe : possibleDestinations) {
			// There is only one possible state in TicTacToe.
			this.setNext(stateTicTacToe);
		}
	}

	@Override
	public String getDescription() {
		return "PutSymbol " + this.x + " " + this.y;
	}
}
