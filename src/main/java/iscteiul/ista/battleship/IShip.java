package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que representa um navio no jogo da Batalha Naval.
 * Define métodos para consultar as características do navio (categoria, tamanho, posição, orientação),
 * validar limites e proximidades no tabuleiro, e gerir os disparos sofridos.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public interface IShip {

    /**
     * Obtém a categoria ou tipo do navio (ex: "Caravela", "Nau", "Galeão").
     * 
     * @return O nome da categoria do navio.
     */
    String getCategory();

    /**
     * Obtém o tamanho do navio em número de células ocupadas no tabuleiro.
     * 
     * @return O tamanho do navio.
     */
    Integer getSize();

    /**
     * Obtém a lista de todas as posições ({@link IPosition}) ocupadas pelo navio no tabuleiro.
     * 
     * @return Lista de posições do navio.
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição de referência/âncora inicial do navio na grelha.
     * 
     * @return A posição ({@link IPosition}) de origem do navio.
     */
    IPosition getPosition();

    /**
     * Obtém a orientação/rumo do navio no tabuleiro (ex: Norte, Sul, Este, Oeste).
     * 
     * @return A orientação do navio definida pelo enum {@link Compass}.
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda se encontra a flutuar (ou seja, se ainda tem células por atingir).
     * 
     * @return {@code true} se o navio ainda estiver ativo/a flutuar; {@code false} se tiver sido afundado.
     */
    boolean stillFloating();

    /**
     * Obtém o índice da linha mais acima (topo) ocupada pelo navio.
     * 
     * @return O limite superior da linha.
     */
    int getTopMostPos();

    /**
     * Obtém o índice da linha mais abaixo (fundo) ocupada pelo navio.
     * 
     * @return O limite inferior da linha.
     */
    int getBottomMostPos();

    /**
     * Obtém o índice da coluna mais à esquerda ocupada pelo navio.
     * 
     * @return O limite esquerdo da coluna.
     */
    int getLeftMostPos();

    /**
     * Obtém o índice da coluna mais à direita ocupada pelo navio.
     * 
     * @return O limite direito da coluna.
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição do tabuleiro.
     * 
     * @param pos A posição ({@link IPosition}) a testar.
     * @return {@code true} se o navio ocupar a posição; {@code false} caso contrário.
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio, violando as regras de distanciamento.
     * 
     * @param other O outro navio ({@link IShip}) a comparar.
     * @return {@code true} se os navios estiverem demasiado próximos/sobrepostos; {@code false} caso contrário.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está demasiado próximo de uma determinada posição.
     * 
     * @param pos A posição ({@link IPosition}) a comparar.
     * @return {@code true} se a posição estiver demasiado próxima do navio; {@code false} caso contrário.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo sobre o navio numa coordenada específica, atualizando o seu estado de dano.
     * 
     * @param pos A posição ({@link IPosition}) onde o disparo foi efetuado.
     */
    void shoot(IPosition pos);
}
