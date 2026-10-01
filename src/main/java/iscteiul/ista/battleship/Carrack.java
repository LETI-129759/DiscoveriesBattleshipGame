package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Nau (Carrack) no jogo Batalha Naval.
 * <p>
 * A Nau ocupa 3 posições consecutivas no tabuleiro, organizadas verticalmente 
 * (se a orientação for NORTE ou SUL) ou horizontalmente (se a orientação for ESTE ou OESTE) 
 * a partir da sua posição inicial.
 * 
 * @see Ship
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Constrói uma nova instância de Nau, calculando as posições que irá ocupar 
     * com base na sua orientação e posição inicial.
     *
     * @param bearing a orientação (bússola) para onde a nau aponta.
     * @param pos     o ponto inicial no tabuleiro para posicionar a nau.
     * @throws IllegalArgumentException se a orientação fornecida não for reconhecida ou for inválida.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém o tamanho total que a Nau ocupa no tabuleiro.
     *
     * @return o tamanho fixo da Nau (neste caso, 3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
