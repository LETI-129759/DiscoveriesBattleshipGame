/**
 * 
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa a interface de uma frota de navios no jogo de Batalha Naval.
 * Define as operações para gerir, consultar e interagir com os navios no tabuleiro.
 * 
 * @author fba
 */
public interface IFleet {
    
    /** Tamanho padrão do tabuleiro de jogo (altura e largura). */
    Integer BOARD_SIZE = 10;
    
    /** Tamanho total da frota (número de navios permitidos/existentes). */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve a lista de todos os navios que compõem a frota.
     * 
     * @return uma lista contendo todos os navios {@link IShip}
     */
    List<IShip> getShips();

    /**
     * Adiciona um novo navio à frota, caso seja válido e haja espaço/regras cumpridas.
     * 
     * @hparam s o navio {@link IShip} a adicionar
     * @return true se o navio foi adicionado com sucesso, false caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Devolve uma lista com todos os navios da frota que pertencem a uma determinada categoria ou tipo.
     * 
     * @param category a string representativa da categoria/nome do navio
     * @return uma lista com os navios correspondentes à categoria especificada
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve uma lista com todos os navios que ainda se encontram a flutuar (não totalmente afundados).
     * 
     * @return uma lista com os navios ainda operacionais/a flutuar
     */
    List<IShip> getFloatingShips();

    /**
     * Procura e devolve o navio localizado numa determinada posição do tabuleiro.
     * 
     * @param pos a posição {@link IPosition} a verificar
     * @return o navio {@link IShip} presente nessa posição, ou {@code null} se estiver vazia
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime o estado atual da frota no terminal.
     */
    void printStatus();
}
