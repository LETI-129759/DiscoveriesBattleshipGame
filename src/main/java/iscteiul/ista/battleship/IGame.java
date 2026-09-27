package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que representa a lógica principal do jogo da Batalha Naval.
 * Define os métodos para efetuar disparos, consultar estatísticas das jogadas e visualizar o estado do jogo.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public interface IGame {

    /**
     * Efetua um disparo numa determinada posição do tabuleiro.
     * 
     * @param pos A posição ({@link IPosition}) onde o disparo é efetuado.
     * @return O objeto {@link IShip} atingido e afundado pelo disparo, ou {@code null} se o tiro for na água ou não afundar o navio.
     */
    IShip fire(IPosition pos);

    /**
     * Obtém a lista com todas as posições onde já foram efetuados disparos durante o jogo.
     * 
     * @return Lista de posições ({@link IPosition}) correspondente ao histórico de disparos.
     */
    List<IPosition> getShots();

    /**
     * Obtém o número total de disparos repetidos (tiros efetuados em coordenadas onde já se tinha disparado anteriormente).
     * 
     * @return Quantidade de disparos repetidos.
     */
    int getRepeatedShots();

    /**
     * Obtém o número total de disparos inválidos (tiros fora dos limites permitidos do tabuleiro).
     * 
     * @return Quantidade de disparos inválidos.
     */
    int getInvalidShots();

    /**
     * Obtém o número total de disparos certeiros que atingiram algum navio da frota.
     * 
     * @return Quantidade de tiros certeiros.
     */
    int getHits();

    /**
     * Obtém o número total de navios da frota que já foram totalmente afundados.
     * 
     * @return Quantidade de navios afundados.
     */
    int getSunkShips();

    /**
     * Obtém o número de navios da frota que ainda se encontram a flutuar/ativos.
     * 
     * @return Quantidade de navios restantes.
     */
    int getRemainingShips();

    /**
     * Imprime na consola/ecrã a lista e o mapa dos disparos válidos efetuados durante a partida.
     */
    void printValidShots();

    /**
     * Imprime na consola/ecrã o estado atual de toda a frota do jogo.
     */
    void printFleet();
}
