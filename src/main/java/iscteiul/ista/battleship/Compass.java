package iscteiul.ista.battleship;

/**
 * Representa os pontos cardeais (orientações) possíveis para os navios no jogo Batalha Naval.
 * 
 * @author fba
 */
public enum Compass {
    /** Orientação Norte ('n'). */
    NORTH('n'), 
    
    /** Orientação Sul ('s'). */
    SOUTH('s'), 
    
    /** Orientação Este/Leste ('e'). */
    EAST('e'), 
    
    /** Orientação Oeste ('o'). */
    WEST('o'), 
    
    /** Orientação Desconhecida ou inválida ('u'). */
    UNKNOWN('u');

    private final char c;

    /**
     * Construtor do enum.
     * 
     * @param c o carácter que representa a direção.
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o carácter associado a esta orientação.
     * 
     * @return o carácter que representa a direção ('n', 's', 'e', 'o' ou 'u').
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em formato de texto (String) do carácter desta orientação.
     * 
     * @return uma String contendo o carácter da direção.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter na respetiva orientação {@link Compass}.
     * <p>
     * Os carácteres válidos são 'n' (Norte), 's' (Sul), 'e' (Este) e 'o' (Oeste).
     * Qualquer outro carácter resultará na orientação {@link #UNKNOWN}.
     * 
     * @param ch o carácter a ser convertido.
     * @return a orientação correspondente ao carácter, ou {@link #UNKNOWN} se não for reconhecido.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
