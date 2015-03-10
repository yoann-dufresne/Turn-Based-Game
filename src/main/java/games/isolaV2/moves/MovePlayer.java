/*******************************************************************************
 * This application simulates turn-based games hosted on a server.
 *     Copyright (C) 2014 
 *     Initiators : Fabien Delecroix and Yoann Dufresne
 *     Developpers :  Celia Cacciatore and Guillaume Ferlin and Raphael Bauduin and Robin Lewandowicz and Yassine Badache
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

package games.isolaV2.moves;

import games.isolaV2.IsolaV2;

import java.util.Set;

import model.exceptions.GameException;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;

/**
 * MovePlayer : The move which move the token of the player in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class MovePlayer extends Move<IsolaV2> {

	private int x;
	private int y;

	/**
	 * constructor MovePlayer
	 * @param x : x coord to the destination
	 * @param y : y coord to the destination
	 */
	public MovePlayer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(IsolaV2 game, Player player,
			Set<State<IsolaV2>> possibleDestinations) throws GameException {


		game.movePlayer(player, this.x, this.y);

		for (State<IsolaV2> stateIsola : possibleDestinations) {
			// There is only one possible state in Isola.
			this.setNext(stateIsola);
		}

	}

	@Override
	public String getDescription() {
		return "Move "+this.x+" "+this.y;
	}

}
