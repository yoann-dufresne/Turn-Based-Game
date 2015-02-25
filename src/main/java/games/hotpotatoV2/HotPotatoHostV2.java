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

package games.hotpotatoV2;

import clients.local.LocalClientFactory;
import model.engine.GameHost;
import games.hotpotatoV2.moves.PassFactory;

/**
 * Hosts the HotPotato game.
 * 
 * @author Cacciatore Celia - Bauduin Raphael
 */
public class HotPotatoHostV2 extends GameHost<HotPotatoV2> {

	public HotPotatoHostV2() {
		super(4, new LocalClientFactory());
	}

	@Override
	protected void createGame() {
		this.game = new HotPotatoV2(this.players);
	}

	@Override
	protected void createFactories() {
		this.firstMoveFactory = new PassFactory();
	}
}