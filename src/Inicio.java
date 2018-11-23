
/**
 * Classe base de inicio.
 */
public class Inicio {

    private int inicio;

    /**
     * Construtor sem parâmetros.
     */
    public Inicio() {
        this(-1);
    }

    /**
     * Construtor com parâmetro
     *
     * @param inicio Um início
     */
    public Inicio(int inicio) {
        this.inicio = inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getInicio() {
        return this.inicio;
    }
}
