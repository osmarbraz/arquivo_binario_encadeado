/**
 * Realiza a manipulação encadeada do arquivo binário.
 * 
 */
public class ListaSE {
    
    /**
     * Inicio é recuperado do arquivo de início.
     */

    /**
     * Aloca um novo registro no arquivo e retorna posição de inclusão.
     *
     * @param dado Um novo cliente no arquivo.
     * @return a posição de inserção.
     */
    public int alocar(Cliente dado) {
        //Instancia o Gerenciador de Cliente
        GerenciadorCliente gerenciador = new GerenciadorCliente();
        //Insere o novo registro no fim do arquivo
        gerenciador.inserirFimArquivo(new RegistroCliente(dado));
        //Retorna posição de inserção
        return gerenciador.getPosicaoFimArquivo();
    }

    /**
     * Atualiza um cliente em uma posição.
     *
     * @param dado Dado de um cliente.
     * @param posicao Posição do cliente a ser atualizado.
     *
     * @return Se consegui atualizar.
     */
    public boolean setDado(Cliente dado, int posicao) {
        //Instancia o Gerenciador de Cliente
        GerenciadorCliente ogerenciadorCliente = new GerenciadorCliente();
        //Retorna se conseguiu atualizar o dado
        return ogerenciadorCliente.atualizarArquivo(posicao, dado);
    }

    public Cliente getDado(int posicao) {
        //Gerenciador do Arquivo
        GerenciadorCliente gerenciador = null;
        //Cliente a ser retornado
        Cliente dado = null;
        //Posição deve ser diferente de -1
        if (posicao != -1) {
            //Instancia o Gerenciador de Cliente
            gerenciador = new GerenciadorCliente();
            //Realiza a leitura de um cliente da posição especificada
            dado = gerenciador.leitura(posicao);
        }
        return dado;
    }

    /**
     * Modificado o valor de início do arquivo.
     * 
     * Salva o valor de dado(início) no arquivo de início.
     *
     * @param dado Valor do início.
     */
    public void setInicio(int dado) {
        //Instancia o Gerenciador de Início
        GerenciadorInicio gerente = new GerenciadorInicio();
        //Instancia um Registro de Início com o dado
        RegistroInicio registro = new RegistroInicio(new Inicio(dado));
        //Insere o início no arquivo
        gerente.inserir(registro);
    }

    /**
     * Retorna o valor de inicio do arquivo.
     *
     * @return O valor do início.
     */
    public int getInicio() {
        //Instancia o Gerenciador de Início
        GerenciadorInicio gerenciador = new GerenciadorInicio();
        //Inicio a ser retornado
        Inicio dado = gerenciador.leitura(0);
        //Retorna o valor do início
        return dado.getInicio();
    }

    /**
     * Inclusão no início.
     * 
     * Incluí logicamente um registro no início.
     *
     * @param novo Um novo cliente;
     *
     * @return Se incluiu no início o novo cliente.
     */
    public boolean inserirInicio(Cliente novo) {
        novo.setProximo(getInicio());   //Coloca o início no próximo do novo
        int posicaoNovo = alocar(novo); // Aloca(salva no arquivo) e obtêm a posição de um novo nó no arquivo
        setInicio(posicaoNovo); // atribui o novo início
        return true;
    }

    /**
     * Lista lógicamente o arquivo usando o próximo.
     *
     * @return Uma String com os dados do arquivo.
     */
    public String listarLogico() {
        //String de retorno
        String linha = "";
        if (getInicio() != -1) {
            //Pega o cliente atual a partir do início
            Cliente atual = getDado(getInicio());
            linha = "Início = " + getInicio() + "\n";
            while (atual != null) {
                //Concatena os dados do registro
                linha = linha + atual.toString() + "\n";
                //Pega o próximo atual
                atual = getDado(atual.getProximo());
            }
        }
        return linha;
    }

     /**
     * Lista fisicamente o arquivo do início até o fim.
     *
     * @return Uma String com os dados do arquivo.
     */
    public String listarFisico() {
        //String de retorno
        String linha = "";
        //Instancia o Gerenciador de Cliente
        GerenciadorCliente gerente = new GerenciadorCliente();
        linha = gerente.listarFisico();
        return linha;
    }
    
    /**
     * Recupera as informações dos arquivos.
     *
     * @return Uma string com os dados do arquivo.
     */
    public String informacoes() {
        //String de retorno
        String linha = "";
        //Instancia o Gerenciador de Cliente
        GerenciadorCliente cliente = new GerenciadorCliente();
        linha = "Arquivo Cliente = " + cliente.informacoes();

        //Instancia o Gerenciador de Início
        GerenciadorInicio inicio = new GerenciadorInicio();
        linha = linha + "\nArquivo Início = " + inicio.informacoes();

        return linha;
    }

    /**
     * Apaga os dados dos arquivos.
     *
     * @return Se conseguiu esvaziar os arquivos.
     */
    public boolean zeraArquivo() {
        //Instancia o Gerenciador de Cliente
        GerenciadorCliente cliente = new GerenciadorCliente();
        cliente.zeraArquivo();

        //Instancia o Gerenciador de Início
        GerenciadorInicio inicio = new GerenciadorInicio();
        inicio.zeraArquivo();

        return true;
    }
}
