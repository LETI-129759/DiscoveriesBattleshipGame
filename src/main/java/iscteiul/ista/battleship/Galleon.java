/**
 * 
 */
package iscteiul.ista.battleship;

/**
 * Represents a Galleon ship in the Battleship game.
 * A Galleon has a fixed size of 5 units and occupies positions
 * based on complex formations depending on its compass bearing (North, South, East, or West).
 * 
 * @author iscteiul.ista.battleship
 * @version 1.0
 */
public class Galleon extends Ship {
    /** The fixed size of the galleon in terms of grid positions. */
    private static final Integer SIZE = 5;
    
    /** The standard display name of the galleon. */
    private static final String NAME = "Galeao";

    /**
     * Constructs a new Galleon instance with a specific orientation (bearing)
     * and starting position.
     * 
     * @param bearing the compass direction the galleon is facing (NORTH, SOUTH, EAST, or WEST)
     * @param pos the starting position of the galleon on the board
     * @throws IllegalArgumentException if the provided bearing is invalid
     * @throws NullPointerException if the provided bearing is null
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Populates the positions for the Galleon when facing North.
     * 
     * @param pos the reference starting position
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Populates the positions for the Galleon when facing South.
     * 
     * @param pos the reference starting position
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Populates the positions for the Galleon when facing East.
     * 
     * @param pos the reference starting position
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Populates the positions for the Galleon when facing West.
     * 
     * @param pos the reference starting position
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
