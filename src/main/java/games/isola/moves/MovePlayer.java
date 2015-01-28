package games.isola.moves;

import games.isola.Isola;

import java.util.Set;

import model.exceptions.GameException;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;

/**
 * The move which move the token of the player in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class MovePlayer extends Move<Isola> {

	private int x;
	private int y;

	/**
	 * constructor MovePlayer
	 * @param x : x coord to the destination
	 * @param y : y coord to the destination
	 */
	public MovePlayer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(Isola game, Player player,
			Set<State<Isola>> possibleDestinations) throws GameException {


		game.movePlayer(player, this.x, this.y);

		for (State<Isola> stateIsola : possibleDestinations) {
			// There is only one possible state in Isola.
			this.setNext(stateIsola);
		}

	}

	@Override
	public String getDescription() {
		return "Move "+this.x+" "+this.y;
	}

}
