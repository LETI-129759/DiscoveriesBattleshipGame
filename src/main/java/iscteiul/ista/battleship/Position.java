package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Implementação da interface {@link IPosition} que representa uma posição
 * coordenada (linha, coluna) na grelha do jogo da Batalha Naval.
 * Guarda também o estado sobre se a célula está ocupada por um navio e/ou se foi atingida.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public class Position implements IPosition {

    /**
     * Índice da linha da posição no tabuleiro.
     */
    private int row;

    /**
     * Índice da coluna da posição no tabuleiro.
     */
    private int column;

    /**
     * Estado que indica se a posição está ocupada por um navio.
     */
    private boolean isOccupied;

    /**
     * Estado que indica se a posição já recebeu um disparo.
     */
    private boolean isHit;

    /**
     * Constrói uma nova posição coordenada no tabuleiro.
     * Por omissão, a posição inicia não ocupada e não atingida.
     * 
     * @param row    Índice da linha.
     * @param column Índice da coluna.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#getRow()
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#getColumn()
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera o código hash baseado na linha, coluna, estado de ocupação e estado de disparo.
     * 
     * @return O valor hash calculado para o objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#isAdjacentTo(iscteiul.ista.battleship.IPosition)
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#occupy()
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#shoot()
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#isOccupied()
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     * 
     * @see iscteiul.ista.battleship.IPosition#isHit()
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação em texto da posição.
     * 
     * @return String formatada com os valores da linha e coluna (ex: "Linha = 1 Coluna = 2").
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
