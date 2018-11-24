
/**
 * Classe base de cliente.
 */
public class Cliente {

    private int codigo;
    private String nome;
    private String endereco;
    private double salario;
    private int idade;
    private int proximo;

    /**
     * Construtor sem parâmetros.
     */
    public Cliente() {
        this(0, "", "", 0.0, 0, -1);
    }

    /**
     * Construtor com parâmetros.
     *
     * @param codigo Código de um cliente.
     * @param nome Nome de um cliente.
     * @param endereco Endereço de um cliente.
     * @param salario Salário de um cliente.
     * @param idade Idade de um cliente
     * @param proximo Próximo registro de cliente.
     */
    public Cliente(int codigo, String nome, String endereco, double salario, int idade, int proximo) {
        setCodigo(codigo);
        setNome(nome);
        setEndereco(endereco);
        setSalario(salario);
        setIdade(idade);
        setProximo(proximo);
    }
    
     // Get´s e Set´s
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getSalario() {
        return salario;
    }

    public int getIdade() {
        return idade;
    }

    public int getProximo() {
        return proximo;
    }

    public void setProximo(int proximo) {
        this.proximo = proximo;
    }

    /**
     * Retorna os dados de cliente em uma String.
     *
     * @return Uma String com os dados.
     */
    public String toString() {
        return ("Código: " + getCodigo() + " \\ Nome: " + getNome()
                + "\\ Endereço: " + getEndereco() + " \\ Salário: "
                + getSalario() + " \\ Idade : " + getIdade() + " \\ Próximo : " + getProximo());
    }
}
