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
import model.exceptions.IncorrectNumberOfArgumentsException;
import model.exceptions.IncorrectTypesOfArgumentException;
import model.moves.Move;
import model.moves.MoveFactory;



/**
 * Creates the move which condamn a case in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class PutCrossFactory extends MoveFactory<IsolaV2> {
	
	
	
	@Override
	public boolean correctKeyword(String message) {
		return message.split(" ")[0].equals("put");
	}
	
	@Override
	public Move<IsolaV2> create(String message) throws Exception {
		String[] args = message.split(" ");
		
		// Creation of PutCross move
		int x, y;
		try {
			// Coordinates in the grid
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IncorrectNumberOfArgumentsException();
		} catch (NumberFormatException e) {
			throw new IncorrectTypesOfArgumentException();
		}
		
		
		return new PutCross(x, y);
		
	}


}
