package games.isola;

import games.isola.moves.MovePlayerFactory;
import games.isola.moves.PutCrossFactory;
import model.engine.GameHost;
import clients.local.LocalClientFactory;

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
