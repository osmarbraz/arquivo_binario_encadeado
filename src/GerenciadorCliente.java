
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Realiza as operações básicas no arquivo de cliente.
 * 
 */
public class GerenciadorCliente {

    private String nomeArquivo;
    private RandomAccessFile arquivo;

    /**
     * Construtor sem parâmetro.
     *
     * Abre o arquivo com o nome default ao inicializar a classe.
     */
    public GerenciadorCliente() {
        setNomeArquivo("CLIENTE.DAT");
        abrirArquivo();
    }

    /**
     * Construtor com parâmetro.
     *
     * Abre o arquivo com o nome especificado ao inicializar a classe.
     */
    public GerenciadorCliente(String nomeArquivo) {
        setNomeArquivo(nomeArquivo);
        abrirArquivo();
    }

    /**
     * Destrutor da classe. Fecha o arquivo.
     */
    public void finalize() {
        fecharArquivo();
    }

    // Get´s e Set´s
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * Abre o arquivo com o nome especificado.
     */
    public void abrirArquivo() {
        try {
            //Cria a referência externa ao objeto fileArquivo
            File fileArquivo = new File(getNomeArquivo());
            //Abre o arquivo para leitura e escrita
            arquivo = new RandomAccessFile(fileArquivo, "rw");
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
    }

    /**
     * Fecha a referência ao arquivo.
     */
    public void fecharArquivo() {
        try {
            //Fecha o arquivo
            arquivo.close();
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
    }

    /**
     * Inclui um registro no fim do arquivo.
     *
     * @param registro Registro de cliente a ser adicionado no fim do arquivo.
     * 
     * @return Retorna verdadeiro ou falso se conseguiu realizar a inclusão.
     */
    public boolean inserirFimArquivo(RegistroCliente registro) {
        try {
            //Posiciona o ponteiro de gravação no final do arquivo
            arquivo.seek(arquivo.length());
            //Escreve o registro no arquivo
            registro.escrita(arquivo);
            return true;
        } catch (IOException io) {
            System.out.println("11Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    /**
     * Retorna a quantidade de registros.
     *
     * @return Um número inteiro com a quantidade de registros.
     */
    public int getQuantidadeRegistro() {
        int contador = 0;
        try {
            //Instancia um registro para armazenar os dados lido do arquivo.
            RegistroCliente registro = new RegistroCliente();
            //Posiciono no início do arquivo.
            arquivo.seek(0);
            //Enquanto o ponteiro de leitura for menor que o tamanho do arquivo.
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                //Realiza a leitura de um registro do arquivo
                registro.leitura(arquivo);
                //Incrementa o contador de registro.
                contador = contador + 1;
            }
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return contador;
    }

    /**
     * Recupera as informações do arquivo.
     *
     * @return Uma string com os dados do arquivo.
     */
    public String informacoes() {
        String informacoes = "";
        try {
            //Concatena as informações do arquivo
            informacoes = "Tamanho do Arquivo : " + arquivo.length() + " Kb " + "\n     Número de Registros : " + getQuantidadeRegistro();
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return informacoes;
    }

    /**
     * Atualiza um registro no arquivo com base na posição.
     *
     * @param posicao Posição do registro a ser atualizado.
     * @param cliente Um cliente com os novos dados.
     * 
     * @return Retorna verdadeiro ou falso se conseguiu atualizar o registro.
     */
    public boolean atualizarArquivo(int posicao, Cliente cliente) {
        try {
            //Posiciona o arquivo no posição a ser alterado
            arquivo.seek(posicao * RegistroCliente.getTamanhoRegistro());

            //Instancia o regitro 
            RegistroCliente registro = new RegistroCliente(cliente);

            //Escreve o registro no arquivo
            registro.escrita(arquivo);

            //Sucesso na atualização
            return true;
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    /**
     * Leitura de um registro de uma posição..
     *
     * @param posicao Posição do registro a ser lido.
     * 
     * @return Retorna Um cliente lido do arquivo.
     */
    public Cliente leitura(int posicao) {
        RegistroCliente registro = null;
        try {
            //Posiciona o arquivo no posição a ser alterado
            arquivo.seek(posicao * RegistroCliente.getTamanhoRegistro());
            //Instancia o registro de cliente
            registro = new RegistroCliente();
            //Leo o registro no arquivo
            registro.leitura(arquivo);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        //Retorna o cliente do registro
        return (Cliente) registro;
    }

    /**
     * Realiza o retorno dos dados do arquivo. 
     * 
     * Retorna todos os dados do arquivo inclusive os excluídos.
     *
     * @return Uma String com os dados do arquivo.
     */
    public String listarFisico() {
        //Variável para concatenar os dados
        String linha = "";
        //Instancia um registro para armazenar os dados lido do arquivo.
        RegistroCliente registro = new RegistroCliente();
        try {
            arquivo.seek(0);
            //Enquanto o ponteiro de leitura for menor que o tamanho do arquivo
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                //Realiza a leitura de um registro do arquivo
                registro.leitura(arquivo);
                //Concatena os dados do registro
                linha = linha + registro.toString() + "\n";
            }
        } catch (EOFException eof) {
            System.out.println("Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return linha;
    }
    
    
    /**
     * Apaga os registro do arquivo.
     *
     * @return Se conseguiu esvaziar o arquivo.
     */
    public boolean zeraArquivo() {
        try {
            //Seta o tamanho do arquivo em 0.
            arquivo.setLength(0);
            return true;
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
            return false;
        }
    }

    /**
     * Retorna o tamanho do arquivo.
     *
     * @return Um long com o tamanho do arquivo
     */
    public long getTamanhoArquivo() {
        long tamanho = 0;
        try {
            tamanho = getArquivo().length();
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        return tamanho;
    }

    /**
     * Retorna a posição do registro do fim do arquivo.
     *
     * @return Um inteiro com a posição do registro do fim do arquivo.
     */
    public int getPosicaoFimArquivo() {
        int posicao = 0;
        try {
            //retorna a quantidade registros
            posicao = (int) (arquivo.length() / RegistroCliente.getTamanhoRegistro());
        } catch (IOException io) {
            System.out.println("Problemas ao manipular o arquivo: " + io);
        }
        //Desconta 1 pois o arquivo começa em 0
        return posicao - 1;
    }
}
