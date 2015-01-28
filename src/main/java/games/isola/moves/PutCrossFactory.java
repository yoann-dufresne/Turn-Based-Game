package games.isola.moves;

import games.isola.Isola;
import model.exceptions.IncorrectNumberOfArgumentsException;
import model.exceptions.IncorrectTypesOfArgumentException;
import model.moves.Move;
import model.moves.MoveFactory;


/**
 * Creates the move which condamn a case in the Isola game.
 * 
 * @author Guillaume Ferlin - Robin Lewandowicz - Yassine Badache
 */
public class PutCrossFactory extends MoveFactory<Isola> {
	
	
	
	@Override
	public boolean correctKeyword(String message) {
		return message.split(" ")[0].equals("put");
	}
	
	@Override
	public Move<Isola> create(String message) throws Exception {
		String[] args = message.split(" ");
		
		// Creation of PutCross move
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
		
		
		return new PutCross(x, y);
		
	}


}
