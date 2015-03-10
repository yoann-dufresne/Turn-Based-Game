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

package games.isolaV2.fsm;

import games.isolaV2.IsolaV2;

import java.util.Set;

import model.fsm.SimpleState;
import model.moves.Move;
import model.players.Player;


/**
 * TurnIsolaPutCross : the state active when a player have to condamn a case
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class TurnIsolaPutCross extends SimpleState<IsolaV2> {

	public TurnIsolaPutCross(Player currentPlayer,
			Set<Class<? extends Move<IsolaV2>>> possibleMoveTypes) {
		super(false, currentPlayer, possibleMoveTypes);
	}

}
