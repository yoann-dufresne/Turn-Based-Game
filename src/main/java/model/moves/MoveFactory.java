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

package model.moves;

import model.Game;
import model.exceptions.IllegalMoveException;

/**
 * Creates a move for a game.
 * 
 * @author Celia Cacciatore - Rapahel Bauduin
 *
 * @param <G> the game for which the move will be created.
 */
public abstract class MoveFactory<G extends Game> {
	
	protected MoveFactory<G> next;
	
	/**
	 * Creates a new move according to the message.
	 * @param message description of the move.
	 * @return a Move corresponding to the message.
	 * @throws different exceptions that can happen while creating a move.
	 */
	public abstract Move<G> create(String message) throws Exception;
	
	/**
	 * Returns true if the keyword of the message matches a move, else false.
	 * @param message description of the move.
	 * @return true if the keyword of the message matches a move, else false.
	 */
	public abstract boolean correctKeyword(String message);
	
	/**
	 * Receives and treats the message.
	 * @param message description of the move.
	 * @throws Exception exceptions that can be throw when creating a move, depend of the game.
	 */
	public Move<G> receive(String message) throws Exception {
		if (this.correctKeyword(message)) {
			return this.create(message);
		} else if (this.next != null) {
			return this.next.receive(message);
		} else {
			throw new IllegalMoveException();
		}
	}
	
	/**
	 * Places the new moveFactory at the end of the Chain of Responsibility
	 * @param moveFactory a moveFactory
	 */
	public void setNext(MoveFactory<G> moveFactory) {
		if (this.next != null) {
			this.next.setNext(moveFactory);
		} else {
			this.next = moveFactory;
		}
	}
}