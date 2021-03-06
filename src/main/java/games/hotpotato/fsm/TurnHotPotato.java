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

package games.hotpotato.fsm;

import games.hotpotato.HotPotato;

import java.util.Set;

import model.fsm.SimpleState;
import model.moves.Move;
import model.players.Player;

/**
 * Represents a state of the HotPotato game in which it's a player's turn.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */
public class TurnHotPotato extends SimpleState <HotPotato> {

	public TurnHotPotato(Player currentPlayer, Set<Class<? extends Move<HotPotato>>> possibleMoveTypes) {
		super(true, currentPlayer, possibleMoveTypes);
	}
}
