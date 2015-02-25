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

package games.hotpotatoV2.moves;

import games.hotpotatoV2.HotPotatoV2;
import model.exceptions.IncorrectNumberOfArgumentsException;
import model.exceptions.IncorrectTypesOfArgumentException;
import model.moves.Move;
import model.moves.MoveFactory;

/**
 * Creates the move which passes the ball to another player in the HotPotato game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */
public class PassFactory extends MoveFactory<HotPotatoV2> {

	@Override
	public boolean correctKeyword(String message) {
		return message.split(" ")[0].equals("pass");
	}
	
	@Override
	public Move<HotPotatoV2> create(String message) throws Exception {
		String[] args = message.split(" ");
		// Creation of Pass move
		String player;
		try {
			// The player who will get the ball.
			player = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IncorrectNumberOfArgumentsException();
		} catch (ClassCastException e) {
			throw new IncorrectTypesOfArgumentException();
		}
		return new Pass(player);
	}
}