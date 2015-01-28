package games.isola.moves;

import java.util.Set;

import games.isola.Isola;
import model.exceptions.GameException;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;


/**
 * The move which condamn a case in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class PutCross extends Move<Isola> {

	private int x;
	private int y;
	
	/**
	 * constructor PutCross
	 * @param x : x coord to the destination
	 * @param y : y coord to the destination
	 */
	public PutCross(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public void execute(Isola game, Player player,
			Set<State<Isola>> possibleDestinations) throws GameException {
		
		// call the model method putCross()
		game.putCross( player, this.x,  this.y);
		
		for (State<Isola> stateIsola : possibleDestinations) {
			// There is only one possible state in TicTacToe.
			this.setNext(stateIsola);
		}
		
	}

	@Override
	public String getDescription() {
		return "PutCross "+this.x+" "+this.y;
	}

}
