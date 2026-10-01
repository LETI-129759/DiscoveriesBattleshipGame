package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que serve de base para a implementação de navios na Batalha Naval.
 * Implementa a interface {@link IShip} com a lógica comum a todas as embarcações,
 * como o cálculo de posições, validações de proximidade e registo de danos.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public abstract class Ship implements IShip {

    /** Constante para identificação da categoria Galeão. */
    private static final String GALEAO = "galeao";

    /** Constante para identificação da categoria Fragata. */
    private static final String FRAGATA = "fragata";

    /** Constante para identificação da categoria Nau. */
    private static final String NAU = "nau";

    /** Constante para identificação da categoria Caravela. */
    private static final String CARAVELA = "caravela";

    /** Constante para identificação da categoria Barca. */
    private static final String BARCA = "barca";

    /**
     * Factory Method estático para instanciar navios com base no tipo indicado.
     * 
     * @param shipKind O tipo/categoria do navio (ex: "galeao", "caravela").
     * @param bearing  A orientação/rumo do navio ({@link Compass}).
     * @param pos      A posição inicial ({@link Position}) de referência do navio.
     * @return Uma instância da subclasse concreta de {@link Ship}, ou {@code null} se o tipo for desconhecido.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** Categoria/tipo do navio. */
    private String category;

    /** Orientação/rumo do navio no tabuleiro. */
    private Compass bearing;

    /** Posição inicial/âncora do navio. */
    private IPosition pos;

    /** Lista de todas as posições ocupadas pelo navio no tabuleiro. */
    protected List<IPosition> positions;

    /**
     * Construtor base para os navios.
     * 
     * @param category Categoria ou nome da classe do navio.
     * @param bearing  Rumo/orientação geográfica do navio.
     * @param pos      Posição inicial de referência do navio.
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getCategory()
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista das posições ({@link IPosition}) ocupadas pelo navio.
     * 
     * @return Lista de posições do navio.
     * @see iscteiul.ista.battleship.IShip#getPositions()
     */
    @Override
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getPosition()
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getBearing()
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#stillFloating()
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getTopMostPos()
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getBottomMostPos()
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getLeftMostPos()
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#getRightMostPos()
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#occupies(iscteiul.ista.battleship.IPosition)
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#tooCloseTo(iscteiul.ista.battleship.IShip)
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#tooCloseTo(iscteiul.ista.battleship.IPosition)
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IShip#shoot(iscteiul.ista.battleship.IPosition)
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Devolve a representação em texto da instância do navio.
     * 
     * @return String formatada contendo a categoria, a orientação e a posição inicial.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
