package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que representa a frota de navios no jogo da Batalha Naval.
 * Define as constantes do tabuleiro e os métodos para gestão e consulta dos navios.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public interface IFleet {

    /**
     * Tamanho padrão do tabuleiro de jogo (grelha de 10x10).
     */
    Integer BOARD_SIZE = 10;

    /**
     * Número máximo de navios permitidos na frota.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Obtém a lista completa de todos os navios pertencentes à frota.
     * 
     * @return Lista contendo todos os objetos {@link IShip} da frota.
     */
    List<IShip> getShips();

    /**
     * Adiciona um novo navio à frota, caso cumpra as regras de validação do jogo.
     * 
     * @param s O navio ({@link IShip}) a ser adicionado à frota.
     * @return {@code true} se o navio for adicionado com sucesso; {@code false} caso contrário.
     */
    boolean addShip(IShip s);

    /**
     * Obtém uma lista de navios da frota pertencentes a uma determinada categoria/tipo.
     * 
     * @param category Categoria do navio (ex: "Caravela", "Nau", "Galeão").
     * @return Lista contendo os navios correspondentes à categoria especificada.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Obtém a lista dos navios que ainda se encontram a flutuar (não afundados).
     * 
     * @return Lista contendo apenas os navios ativos da frota.
     */
    List<IShip> getFloatingShips();

    /**
     * Procura e devolve o navio presente numa determinada posição do tabuleiro.
     * 
     * @param pos A posição ({@link IPosition}) a ser verificada no tabuleiro.
     * @return O objeto {@link IShip} localizado nessa posição, ou {@code null} se a posição estiver vazia.
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime no ecrã/consola o estado atual de todos os navios da frota.
     */
    void printStatus();
}
