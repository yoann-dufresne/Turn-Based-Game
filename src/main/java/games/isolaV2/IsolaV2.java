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

package games.isolaV2;

import games.isolaV2.exceptions.CaseNotEmptyException;
import games.isolaV2.exceptions.FarAwayCaseException;
import games.isolaV2.exceptions.GridIndexOutOfBoundsException;
import games.isolaV2.fsm.IsolaPlayerTurn;
import games.isolaV2.fsm.TurnIsolaMove;
import games.isolaV2.fsm.TurnIsolaPutCross;
import games.isolaV2.moves.MovePlayer;
import games.isolaV2.moves.PutCross;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Game;
import model.fsm.SimpleState;
import model.fsm.State;
import model.moves.Move;
import model.players.Ordered;
import model.players.Player;




/**
 * The Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz
 */
public class IsolaV2 extends Game {

	public static final int EMPTY = 0; 


	/**
	 * the grid of the Isola game, each case is int corresponding to
	 *  0	 	: the case is Empty
	 *  > 0		: represent the token of the player (1 for Player1, 2 for Player2)
	 *  < 0 	: the case is condamned
	 */
	private int[][] grid;


	/**
	 * constructor of the Isola game able to set the width and height of the grid
	 * @param players : the list of players
	 * @param width : the witdh of the grid
	 * @param height : the height of the grid
	 */
	public IsolaV2(List<Player> players, int width, int height) {
		super(players);
		this.grid = new int[width][height];

		// initialisation of the grid
		for(int x=0; x<width ; ++x){
			for(int y=0; y<height ; ++y){

				this.grid[x][y] = EMPTY;

			}
		}

		// put the player's token on the grid
		this.grid[0][height/2 - 1] = 1;
		this.grid[width-1][height/2] = 2;

	}


	/**
	 * default constructor of the Isola game, create a 8x6 grid
	 * @param players : the list of players
	 */
	public IsolaV2(List<Player> players){
		this(players, 8, 6);
	}



	@Override
	public void initializeStates() {

		// the first player is the first of the list
		LinkedList<Player> playersOrder = new LinkedList<Player>();
		playersOrder.addAll(this.getPlayers());

		// Possible moves types for all states
		Set<Class<? extends Move<IsolaV2>>> possibleMoveTypesForMovePl = new HashSet<Class<? extends Move<IsolaV2>>>();
		Set<Class<? extends Move<IsolaV2>>> possibleMoveTypesForPutCross = new HashSet<Class<? extends Move<IsolaV2>>>();
		possibleMoveTypesForMovePl.add(MovePlayer.class);
		possibleMoveTypesForPutCross.add(PutCross.class);


		// state pointers
		IsolaPlayerTurn superState;
		TurnIsolaMove turnMove;
		TurnIsolaPutCross turnPutCross;



		Set<SimpleState<IsolaV2>> superStateComposition = new HashSet<SimpleState<IsolaV2>>();




		// State creation and link

		// State creations
		turnMove = new TurnIsolaMove(null,possibleMoveTypesForMovePl) ;
		turnPutCross = new TurnIsolaPutCross(null, possibleMoveTypesForPutCross) ;
		
		superStateComposition.add(turnMove);
		superStateComposition.add(turnPutCross);
		
		superState = new IsolaPlayerTurn(superStateComposition, turnMove, turnPutCross, playersOrder);

		// add the pointers to the set of differents states
		this.states.add(turnMove);
		this.states.add(turnPutCross);

		
		Set<State<IsolaV2>> possibleDest;
		
		// In Isola game, possible destinations for state TurnMovePlayer is TurnPutCross
		possibleDest = new HashSet<State<IsolaV2>>();
		possibleDest.add(turnPutCross);
		turnMove.setPossibleDestinations(possibleDest);

		// link the previous player to the current if this is not the first
		possibleDest = new HashSet<State<IsolaV2>>();
		possibleDest.add(superState);
		turnPutCross.setPossibleDestinations(possibleDest);



		// Initial state
		this.currentState = superState;

	}

	@Override
	public void initializeOrder(List<Player> players) {
		this.order = new Ordered(players);
	}

	@Override
	public boolean isFinished() {

		if(currentState instanceof TurnIsolaPutCross){
			// the state TurnIsolaPutCross is not a final state
			return false;
		}

		int playerTag = this.getPlayerTag(this.getCurrentPlayer());
		int xToken = -10, yToken = -10;

		// find the token of the current player 
		for(int i=0; i<grid.length && xToken == -10; ++i){
			for(int j=0; j<grid[0].length && xToken == -10 ; ++j){
				if(this.grid[i][j] == playerTag){
					xToken = i;
					yToken = j;
				}
			}
		}

		// check the case accessible for this token
		for(int decX = -1 ; decX <= 1 ; ++decX){
			for(int decY = -1 ; decY <= 1 ; ++decY){

				// if the target is in the grid and is not the token itself
				if( (xToken+decX >= 0 && xToken+decX < grid.length &&
						yToken+decY >= 0 && yToken+decY < grid[0].length) 
						&& !(decX == decY && decX == 0) )
				{

					// if the target case is empty, the game is not finished
					if(this.grid[ xToken + decX ][ yToken + decY ] == EMPTY){

						return false;

					}

				}

			}
		}

		// if there is no case to move for the player, the game is finished
		// the current player loose
		for (Player pl : this.getPlayers()){
			if( !pl.equals(this.getCurrentPlayer()) )
				this.winners.add(pl);
		}
		return true;
	}


	/**
	 * method movePlayer : move the token of the player p on the grid, at coord x,y
	 * @param p : the player doing the move
	 * @param x : the coord x on the grid for the target case
	 * @param y : the coord y on the grid for the target case
	 * @throws CaseNotEmptyException
	 * @throws GridIndexOutOfBoundsException
	 * @throws FarAwayCaseException 
	 */
	public void movePlayer(Player p, int x, int y) throws CaseNotEmptyException, GridIndexOutOfBoundsException, FarAwayCaseException {

		if(x<0 || x>=grid.length || y<0 || y>=grid[0].length){
			throw new GridIndexOutOfBoundsException();
		}

		if(this.grid[x][y] != 0){
			throw new CaseNotEmptyException();
		}

		int playerTag = this.getPlayerTag(p);
		int xToken = -10, yToken = -10;

		for(int i=0; i<grid.length && xToken == -10; ++i){
			for(int j=0; j<grid[0].length && xToken == -10 ; ++j){
				if(this.grid[i][j] == playerTag){
					xToken = i;
					yToken = j;
				}
			}
		}


		if( (xToken - x) < -1 || (xToken - x) > 1  || (yToken - y) < -1 || (yToken - y) > 1 ){
			throw new FarAwayCaseException();
		}


		freeGridCase(xToken, yToken);
		putPlayerToken(playerTag, x, y);

	}


	private int getPlayerTag(Player p){
		return this.getPlayers().get(0).equals(p) ? 1 : 2;
	}

	private void putPlayerToken(int playerTag, int x, int y){
		this.grid[x][y] = playerTag;
	}


	private void freeGridCase(int x, int y) {
		this.grid[x][y] = EMPTY;
	}


	/**
	 * method putCross : condamn the case corresponding to x,y coord
	 * @param player
	 * @param x : the coord x on the grid for the target case
	 * @param y : the coord y on the grid for the target case
	 * @throws GridIndexOutOfBoundsException
	 * @throws CaseNotEmptyException
	 */
	public void putCross(Player player, int x, int y) throws GridIndexOutOfBoundsException, CaseNotEmptyException {
		int playerTag = this.getPlayerTag(player);

		if(x<0 || x>=grid.length || y<0 || y>=grid[0].length){
			throw new GridIndexOutOfBoundsException();
		}

		if(this.grid[x][y] != 0){
			throw new CaseNotEmptyException();
		}

		this.grid[x][y] = -playerTag;

	}






}
