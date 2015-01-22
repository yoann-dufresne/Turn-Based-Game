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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.players.Player;

/**
 * Manages the communication with the clients.
 * 
 * @author Cacciatore Celia - Bauduin Raphael
 */
public class ClientInterface {
	
	private ClientFactory clientFactory;
	private Map<Player, Client> playersToClients;

	/**
	 * @param network true if the clients are on network, else false
	 */
	public ClientInterface(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.playersToClients = new HashMap<Player,Client>();
	}

	/**
	 * Creates the players in the game and matches them with a client.
	 * @param n number of players
	 * @return the players corresponding to the clients
	 */
	public List<Player> waitForPlayers(int n) {
		for (int i = 0; i < n; i++) {
			this.playersToClients.put(new Player("P"+i), this.clientFactory.createClient());
		}
		List<Player> playersList = new ArrayList<Player>();
		for (Player player : this.playersToClients.keySet()) {
			playersList.add(player);
		}
		return playersList;
	}
	
	/**
	 * Receives the client's answer and transfers it to the game.
	 * @param player the player corresponding to the client to whom the game wants a answer.
	 * @return the client's answer
	 */
	public String receiveMessage(Player player) {
		Client client = this.playersToClients.get(player);
		return client.sendMessageWithAnswer("play");
	}
	
	/**
	 * Tells all the clients which player played which move.
	 * @param moveDescription description of the played move
	 * @param player the play who played this move
	 */
	public void sendPlayedMove(String moveDescription, Player player) {
		Collection<Client> clients = this.playersToClients.values();
		for (Client client : clients) {
			client.sendMessage("move " + player + " " + moveDescription);
		}
	}
	
	/**
	 * Warns a client that his move provoked an error.
	 * @param i the error code
	 * @param player the player who provoked this error
	 */
	public void sendError(int i, Player player) {
		Client client = this.playersToClients.get(player);
		client.sendMessage("error " + i);
	}
	
	/**
	 * Tells all the clients that the game is finished and who won it.
	 * @param winners all the players that won the game
	 */
	public void sendEnd(Set<Player> winners) {
		// Preparation of the list of winners
		StringBuilder winnersList = new StringBuilder();
		for (Player player : winners) {
			winnersList.append(player + " ");
		}
		// Message sent to each player
		Collection<Client> clients = this.playersToClients.values();
		for (Client client : clients) {
			if (winners.size() == 0) {
				// No winner
				client.sendMessage("Draw");
			} else {
				// List of winners
				client.sendMessage("Winners : " + winnersList.toString());
			}
		}
	}
	
	/**
	 * Closes the connection for all players.
	 */
	public void closeConnections(){
		for (Client client : this.playersToClients.values()){
			client.closeConnection();
		}
	}
}