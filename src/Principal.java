
/**
 * Programa que armazena um registro de tamanho fixo em um arquivo binário de forma encadeada.
 *
 * Realiza as operações de inclusão no início e listagem dos registros de forma encadeada.
 *
 */
import javax.swing.JOptionPane;

public class Principal {

    public static Cliente leitura(String mensagem) {
        //Mostra a mensagem se ela for diferente de vazio
        if (!mensagem.equals("")) {
            JOptionPane.showMessageDialog(null, mensagem);
        }
        //Instancia o cliente a ser preenchido
        Cliente cliente = new Cliente();
        //Preenche o cliente com os dados lidos
        cliente.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Código")));
        cliente.setNome(JOptionPane.showInputDialog("Digite o Nome"));
        cliente.setEndereco(JOptionPane.showInputDialog("Digite o Endereço"));
        cliente.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o Salário")));
        cliente.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Digite o Idade")));
        //Retorna o cliente preenchido
        return cliente;
    }

    public static void main(String Arg[]) {
        //Classe que gerencia o encadeamento de cliente
        ListaSE lista = new ListaSE();
        //Recebe a opção do menu
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("\t### Arquivo Binário Encadeado ###\n"
                    + " 1 - Incluir Início \n "
                    + " 2 - Listar Lógico\n "
                    + " 3 - Listar Físico \n "
                    + " 4 - Informações \n "
                    + " 5 - Zera Arquivo \n "
                    + "99 - Sair\n"
                    + "Digite uma Opção: "));

            switch (opcao) {
                case 1: {
                    //Chama o método leitura para retornar um cliente instanciado e preenchido
                    Cliente cliente = leitura("");
                    if (lista.inserirInicio(cliente) == true) {
                        JOptionPane.showMessageDialog(null, "Registro inserido no início com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro não foi inserido no início.");
                    }
                    break;
                }

                case 2: {
                    //Lista logicamente os dados do arquivo. Não inclui chave com -1                    
                    String saida = lista.listarLogico();
                    JOptionPane.showMessageDialog(null, "Lista Lógico:\n" + saida);
                    break;
                }
                case 3: {
                    //Lista fisicamente os dados do arquivo
                    String saida = lista.listarFisico();
                    JOptionPane.showMessageDialog(null, "Lista Físico:\n" + saida);
                    break;
                }
                case 4: {
                    //Retorna as informações do arquivo
                    String informacoes = lista.informacoes();
                    JOptionPane.showMessageDialog(null, informacoes);
                    break;
                }
                case 5: {
                    //Esvazia o arquivo de dados
                    if (lista.zeraArquivo() == true) {
                        JOptionPane.showMessageDialog(null, "Arquivo zerado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Arquivo não foi zerado!");
                    }
                    break;
                }
                case 99: {
                    System.out.println("Saindo do Sistema!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } while (opcao != 99);
    }
}
