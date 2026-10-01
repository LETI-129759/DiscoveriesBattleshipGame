package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Barca (Barge) no jogo Batalha Naval.
 * <p>
 * A Barca é o navio mais pequeno do jogo, ocupando apenas uma posição no tabuleiro (tamanho 1).
 * 
 * @see Ship
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Constrói uma nova instância de Barca com a orientação e posição inicial especificadas.
     * Como tem tamanho 1, a lista de posições do navio é inicializada apenas com esta posição exata.
     *
     * @param bearing a orientação (bússola) da barca.
     * @param pos     a posição inicial (linha e coluna) da barca.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Obtém o tamanho total que a Barca ocupa no tabuleiro.
     *
     * @return o tamanho fixo da Barca (neste caso, 1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
