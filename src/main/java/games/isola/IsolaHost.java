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

package games.isola;

import games.isola.moves.MovePlayerFactory;
import games.isola.moves.PutCrossFactory;
import model.engine.GameHost;
import clients.local.LocalClientFactory;

/**
 * IsolaHost : the host who can create an instance of the isola game
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class IsolaHost extends GameHost<Isola> {

	public IsolaHost() {
		super(2, new LocalClientFactory());
	}

	@Override
	protected void createGame() {
		this.game = new Isola(players);
	}

	@Override
	protected void createFactories() {
		
		// create and chain the differents factories
		
		MovePlayerFactory mpFact = new MovePlayerFactory();
		PutCrossFactory pcFact = new PutCrossFactory();
		
		mpFact.setNext(pcFact);
		pcFact.setNext(mpFact);
		
		this.firstMoveFactory = mpFact;
	
	}

}
