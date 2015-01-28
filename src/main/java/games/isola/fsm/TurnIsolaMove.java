package games.isola.fsm;

import java.util.Set;

import games.isola.Isola;
import model.fsm.State;
import model.moves.Move;
import model.players.Player;

public class TurnIsolaMove extends State<Isola> {

	public TurnIsolaMove(Player currentPlayer,
			Set<Class<? extends Move<Isola>>> possibleMoveTypes) {
		super(currentPlayer, possibleMoveTypes);
	}

}
