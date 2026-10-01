/**
 * 
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lógica de execução e o estado de um jogo de Batalha Naval.
 * Implementa a interface {@link IGame} para gerir disparos, frota, estatísticas
 * e visualização do tabuleiro.
 * 
 * @author fba
 */
public class Game implements IGame {
    
    /** A frota de navios associada a este jogo. */
    private IFleet fleet;
    
    /** Lista de posições onde foram efetuados disparos válidos. */
    private List<IPosition> shots;

    /** Contador de disparos efetuados fora dos limites do tabuleiro. */
    private Integer countInvalidShots;
    
    /** Contador de disparos efetuados repetidamente numa posição já atingida. */
    private Integer countRepeatedShots;
    
    /** Contador total de acertos bem-sucedidos em navios. */
    private Integer countHits;
    
    /** Contador total de navios afundados. */
    private Integer countSinks;

    /**
     * Constrói uma nova instância de um jogo com base numa frota fornecida.
     * Inicializa a lista de disparos e define todos os contadores de estatísticas a zero.
     * 
     * @param fleet a frota de navios a utilizar no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        countHits = 0;
        countSinks = 0;
        this.fleet = fleet;
    }

    /**
     * Efetua um disparo numa determinada posição do tabuleiro.
     * Atualiza as estatísticas de tiros válidos, inválidos, repetidos, acertos e afundamentos.
     * 
     * @param pos a posição onde se pretende disparar
     * @return o navio {@link IShip} que foi afundado com este disparo, ou {@ caso contrário}
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devolve a lista de todas as posições onde foram efetuados tiros válidos.
     * 
     * @return uma lista com as posições dos disparos
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Devolve o número total de disparos repetidos efetuados durante o jogo.
     * 
     * @return o número de tiros repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Devolve o número total de disparos inválidos efetuados durante o jogo.
     * 
     * @return o número de tiros inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Devolve o número total de acertos em navios da frota.
     * 
     * @return o número de acertos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Devolve o número total de navios que já foram totalmente afundados.
     * 
     * @return o número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Devolve o número de navios que ainda se encontram a flutuar no tabuleiro.
     * 
     * @return o número de navios restantes
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se uma dada posição se encontra dentro dos limites válidos do tabuleiro.
     * 
     * @param pos a posição a verificar
     * @return true se a posição for válida, false caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um disparo já tinha sido efetuado anteriormente na mesma posição.
     * 
     * @param pos a posição a verificar
     * @return true se o tiro for repetido, false caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime no console uma grelha do tabuleiro marcando as posições especificadas com o caráter dado.
     * 
     * @oaram positions lista de posições a marcar no tabuleiro
     * @param marker o caráter a utilizar para marcar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Imprime o tabuleiro mostrando os disparos válidos efetuados até ao momento.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro mostrando as posições atuais de todos os navios da frota.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}
