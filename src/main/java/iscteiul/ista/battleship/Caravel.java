package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Caravela (Caravel) no jogo Batalha Naval.
 * <p>
 * A Caravela ocupa 2 posições consecutivas no tabuleiro, organizadas verticalmente 
 * (se a orientação for NORTE ou SUL) ou horizontalmente (se a orientação for ESTE ou OESTE) 
 * a partir da sua posição inicial.
 * 
 * @see Ship
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Constrói uma nova instância de Caravela, calculando as posições que irá ocupar 
     * com base na sua orientação e posição inicial.
     *
     * @param bearing a orientação (bússola) para onde a caravela aponta.
     * @param pos     o ponto inicial no tabuleiro para posicionar a caravela.
     * @throws NullPointerException     se a orientação ({@code bearing}) fornecida for nula.
     * @throws IllegalArgumentException se a orientação fornecida não for reconhecida (inválida).
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Obtém o tamanho total que a Caravela ocupa no tabuleiro.
     *
     * @return o tamanho fixo da Caravela (neste caso, 2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
