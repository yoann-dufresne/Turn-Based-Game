package games.isola.moves;

import games.isola.Isola;
import model.exceptions.IncorrectNumberOfArgumentsException;
import model.exceptions.IncorrectTypesOfArgumentException;
import model.moves.Move;
import model.moves.MoveFactory;

/**
 * Creates the move which move the token of the player in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class MovePlayerFactory extends MoveFactory<Isola> {

	
	@Override
	public boolean correctKeyword(String message) {
		return message.split(" ")[0].equals("move");
	}
	
	
	@Override
	public Move<Isola> create(String message) throws Exception {
		String[] args = message.split(" ");
		
		// Creation of MovePlayer move
		int x, y;
		try {
			// Coordinates in the grid
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IncorrectNumberOfArgumentsException();
		} catch (NumberFormatException e) {
			throw new IncorrectTypesOfArgumentException();
		}
		
		return new MovePlayer(x, y);
		
	}
	

}
