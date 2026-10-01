package iscteiul.ista.battleship;

/**
 * Interface que representa uma posição ou coordenada no tabuleiro do jogo da Batalha Naval.
 * Define métodos para obter as coordenadas, gerir o estado da célula (ocupada/atingida)
 * e validar adjacências entre posições.
 * 
 * @author fba
 * @version 1.0
 */
public interface IPosition {

    /**
     * Obtém o índice da linha correspondente a esta posição no tabuleiro.
     * 
     * @return O número da linha.
     */
    int getRow();

    /**
     * Obtém o índice da coluna correspondente a esta posição no tabuleiro.
     * 
     * @return O número da coluna.
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto para verificar se representam exatamente as mesmas coordenadas.
     * 
     * @param other O objeto a ser comparado.
     * @return {@code true} se representarem a mesma posição; {@code false} caso contrário.
     */
    boolean equals(Object other);

    /**
     * Verifica se a posição atual é diretamente adjacente a outra posição indicada.
     * 
     * @param other A outra posição ({@link IPosition}) a ser verificada.
     * @return {@code true} se as posições forem adjacentes; {@code false} caso contrário.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um disparo efetuado sobre esta posição, marcando-a como atingida.
     */
    void shoot();

    /**
     * Verifica se existe algum navio a ocupar esta posição.
     * 
     * @return {@code true} se a posição estiver ocupada por um navio; {@code false} caso contrário.
     */
    boolean isOccupied();

    /**
     * Verifica se esta posição já foi alvo de um disparo durante o jogo.
     * 
     * @return {@code true} se a posição já foi atingida; {@code false} caso contrário.
     */
    boolean isHit();
}
