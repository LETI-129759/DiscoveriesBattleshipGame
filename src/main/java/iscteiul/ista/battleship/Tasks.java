package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilitária que contém os cenários de tarefas/testes e interação via consola para o jogo Batalha Naval.
 * Permite simular a criação de navios, construção de frotas, disparo de salvos de tiros e controlo do fluxo de jogo.
 * 
 * @author Rodrigo Alves
 * @version 1.0
 */
public class Tasks {

    /** Logger para o registo de mensagens de saída do jogo. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Número de disparos por cada ronda de tiro ("rajada"). */
    private static final int NUMBER_SHOTS = 3;

    /** Mensagem de despedida apresentada ao terminar o jogo. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Comando de texto para criar uma nova frota. */
    private static final String NOVAFROTA = "nova";

    /** Comando de texto para desistir e sair da aplicação. */
    private static final String DESISTIR = "desisto";

    /** Comando de texto para disparar uma ronda de tiros. */
    private static final String RAJADA = "rajada";

    /** Comando de texto para visualizar o mapa dos disparos efetuados. */
    private static final String VERTIROS = "ver";

    /** Comando de texto para visualizar o mapa completo da frota (modo batota). */
    private static final String BATOTA = "mapa";

    /** Comando de texto para consultar o estado atual da frota. */
    private static final String STATUS = "estado";

    /**
     * Tarefa A: Testa a construção de navios individuais.
     * Lê a definição de um navio e subsequentemente verifica se o navio ocupa
     * um conjunto de posições introduzidas pelo utilizador.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Tarefa B: Testa a criação e o estado de uma frota através do processamento de comandos de consola.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa C: Testa a criação de frotas e adiciona a funcionalidade de visualização do mapa completo (batota).
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa D: Simula o ciclo completo de jogo, incluindo a criação da frota, consulta de estados,
     * disparo de rajadas de tiro e exibição dos tiros e frota.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma nova frota através da leitura sequencial de navios fornecidos na consola.
     * 
     * @param in O {@link Scanner} para leitura dos dados introduzidos.
     * @return A frota ({@link Fleet}) contendo os navios adicionados com sucesso.
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i representa o total de navios criados com sucesso

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê da consola os dados do navio (tipo, posição e orientação) e cria a respetiva instância.
     * 
     * @param in O {@link Scanner} para leitura dos dados.
     * @return A instância de {@link Ship} criada, ou {@code null} se o tipo for inválido.
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê do canal de entrada uma coordenada do mapa (linha e coluna).
     * 
     * @param in O {@link Scanner} para leitura das coordenadas.
     * @return A nova instância de {@link Position} lida.
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Executa uma ronda de disparos (composta por 3 tiros) sobre o jogo em curso.
     * 
     * @param in   O {@link Scanner} para leitura das posições dos disparos.
     * @param game O contexto de jogo ({@link IGame}) sobre o qual os disparos são efetuados.
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
