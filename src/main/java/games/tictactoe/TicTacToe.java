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

package games.tictactoe;

import games.tictactoe.exceptions.CaseNotEmptyException;
import games.tictactoe.fsm.TurnTicTacToe;
import games.tictactoe.moves.PutSymbol;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Game;
import model.fsm.State;
import model.moves.Move;
import model.players.Ordered;
import model.players.Player;

/**
 * The TicTacToe game.
 * 
 * @author Celia Cacciatore - Raphael Bauduin
 */
public class TicTacToe extends Game {
	
	private Player[][] grid;
	
	/**
	 * A new TicTacToe game.
	 * @param size the size of the grid.
	 */
	public TicTacToe(List<Player> players, int size) {
		super(players);
		this.grid = new Player[size][size];
	}
	
	/**
	 * Initializes the TicTacToe game with an empty 3x3 grid.
	 */
	public TicTacToe(List<Player> players) {
		this(players, 3);
	}
	
	@Override
	public void initializeStates() {
		// Possible moves types for all states
		Set<Class<? extends Move<TicTacToe>>> possibleMoveTypes = new HashSet<Class<? extends Move<TicTacToe>>>();
		possibleMoveTypes.add(PutSymbol.class);
		
		// State creations
		for (Player player : this.getPlayers()) {
			this.states.add(new TurnTicTacToe(player,possibleMoveTypes));
		}
		
		// Possible destinations for each state
		Set<State<TicTacToe>> possibleDestinationsP1 = new HashSet<State<TicTacToe>>();
		Set<State<TicTacToe>> possibleDestinationsP2 = new HashSet<State<TicTacToe>>();
		Iterator<State<? extends Game>> it = this.states.iterator();
		State<TicTacToe> turnP1 = (State<TicTacToe>)it.next();
		State<TicTacToe> turnP2 = (State<TicTacToe>)it.next();
		possibleDestinationsP1.add(turnP2);
		turnP1.setPossibleDestinations(possibleDestinationsP1);
		possibleDestinationsP2.add(turnP1);
		turnP2.setPossibleDestinations(possibleDestinationsP2);
		
		// Initial state
		this.currentState = turnP1;
	}
	
	@Override
	public void initializeOrder(List<Player> players) {
		this.order = new Ordered(players);
	}

	/**
	 * @param tabSymbols an array of symbols
	 * @return true if the array contains always the same symbol, false if not
	 */
	private boolean checkArrayOfSymbols(Player[] tabSymbols){
		Player firstSymbol = tabSymbols[0];
		if (firstSymbol == null) return false;
		for (int i=1; i< tabSymbols.length; i++)
			if (tabSymbols[i] != firstSymbol)
				return false;
		this.winners.add(firstSymbol);
		return true;
	}
	
	/**
	 * Tests if the game was won by a player.
	 * @return true if the game was won by a player, else false.
	 */
	public boolean isWon(){	
		Player[] tabSymbols;
		
		// check every line
		for (int i=0; i<this.grid.length; i++)
			if (checkArrayOfSymbols(this.grid[i])) return true;
		
		//check every column
		for (int i=0; i<this.grid[0].length; i++){
			tabSymbols = new Player[this.grid.length];
			for (int j=0; j<this.grid.length; j++)
				tabSymbols[j] = this.grid[j][i];
			if (checkArrayOfSymbols(tabSymbols)) return true;
		}
		
		// check diagonal
		tabSymbols = new Player[this.grid.length];
		for (int i=0; i<this.grid.length; i++)
			tabSymbols[i] = this.grid[i][i];
		if (checkArrayOfSymbols(tabSymbols)) return true;
		
		// check antidiagonal
		tabSymbols = new Player[this.grid.length];
		for (int i=0; i < this.grid.length; i++)
			tabSymbols[i] = grid[this.grid.length-1-i][i];
		if (checkArrayOfSymbols(tabSymbols)) return true;
		
		return false;
	}
	
	/**
	 * Tests if the grid is full.
	 * @return true if the grid is full, else false.
	 */
	public boolean isFull() {
		for (int i=0; i<this.grid.length; i++)
			for(int j=0; j<this.grid[0].length; j++)
				if (grid[i][j] == null)
					return false;
		return true;
	}
	
	/**
	 * Tests if the game is finished.
	 * @return true if the game is finished, else false.
	 */
	public boolean isFinished(){
		return this.isWon() || this.isFull();
	}
	
	/** Puts the player's symbol in the case (x,y) of the TicTacToe grid.
	 * @param x the abscissa of the grid.
	 * @param y the ordinate of the grid.
	 * @param player the player putting a symbol
	 * @throws CaseNotEmptyException if the case you want to play in is not empty
	*/
	public void putSymbol(int x, int y, Player player) throws CaseNotEmptyException {
		if (this.grid[x][y] != null) throw new CaseNotEmptyException();
		this.grid[x][y] = player;
		this.isWon();
	}
}