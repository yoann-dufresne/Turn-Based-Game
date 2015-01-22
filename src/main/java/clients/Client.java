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

package clients;

/**
 * Represents an abstraction of the clients in the game.
 * 
 * @author Cacciatore Celia - Bauduin Raphael
 */
public interface Client {
	
	/**
	 * Tranfers a message from the game to the client, and then returns the client's answer to the game.
	 * @param receivedMessage message received from the game
	 * @return the client's answer
	 */
	public String sendMessageWithAnswer(String receivedMessage);
	
	/**
	 * Tranfers a message from the game to the client.
	 * @param message message received from the game
	 */
	public void sendMessage(String message);
	
	/**
	 * Closes the connection of the client.
	 */
	public void closeConnection();
}