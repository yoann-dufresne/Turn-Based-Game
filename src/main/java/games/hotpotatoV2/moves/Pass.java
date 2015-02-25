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
import games.hotpotatoV2.exceptions.AlreadyGotPotatoException;
import games.hotpotatoV2.exceptions.UnknownPlayerException;

import java.util.Set;

import model.exceptions.GameException;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;

/**
 * Pass the ball to another player in the HotPotato game.
 * 
 * @author Cacciatore Celia - Bauduin Raphael
 */
public class Pass extends Move<HotPotatoV2>{

	private String player;
	
	/**
	 * A move which passes the ball to another player.
	 * @param player the player who will get the ball
	 */
	public Pass (String player){
		super();
		this.player = player;
	}
	
	@Override
	public void execute(HotPotatoV2 game, Player player, Set<State<HotPotatoV2>> possibleDestinations) throws GameException {
		if (player.getName().equals(this.player)) {
			// the player tried to give the ball to himself.
			throw new AlreadyGotPotatoException();
		}
		// Test if the player exists.
		boolean playerExists = false;
		Player targetPlayer = null;
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(this.player)) {
				playerExists = true;
				targetPlayer = p;
			}
		}
		if (!playerExists) {
			// The player tried to give the ball to a player that doesn't exist.
			throw new UnknownPlayerException();
		}
		game.passPotato(player);
		
		for (State<HotPotatoV2> stateHotPotato : possibleDestinations) {
				// It's the other player's turn. (only one state un the V2)
				stateHotPotato.setCurrentPlayer(targetPlayer);
				this.setNext(stateHotPotato);
		}
	}

	@Override
	public String getDescription() {
		return "Pass " + this.player;
	}
}
