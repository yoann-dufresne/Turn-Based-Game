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

package games.isolaV2.fsm;

import games.isolaV2.IsolaV2;

import java.util.LinkedList;
import java.util.Set;

import model.fsm.SimpleState;
import model.fsm.SuperState;
import model.players.Player;

/**
 * @author LIVEBOX52
 *
 */
public class IsolaPlayerTurn extends SuperState<IsolaV2> {

	/**
	 * @param statesSet
	 * @param firstState
	 * @param lastState
	 * @param playerOrder
	 */
	public IsolaPlayerTurn(Set<SimpleState<IsolaV2>> statesSet,
			SimpleState<IsolaV2> firstState, SimpleState<IsolaV2> lastState,
			LinkedList<Player> playerOrder) {
		super(statesSet, firstState, lastState, playerOrder);
	}

}
