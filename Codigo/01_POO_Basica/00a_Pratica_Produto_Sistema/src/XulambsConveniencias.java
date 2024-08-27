import java.util.Arrays;
import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/** Sistema de Vendas para a Xulambs Conveniências. */
public class XulambsConveniencias {
    static Scanner teclado = new Scanner(System.in);

    /**
     * Encapsula uma pausa (leitura de teclado) no aplicativo de console.
     */
    public static void pausa() {
        System.out.print("Pressione <enter> para continuar.");
        teclado.nextLine();
    }

    /**
     * Loop para cadastrar produtos a partir do teclado e retorná-los em um array.
     * Podem ser cadastrados até 100 produtos.
     * A tentativa de ler mais do que 100 produtos causará um erro.
     * 
     * @return Um array com os produtos (pode estar vazio).
     */
    public static Produto[] cadastrarProdutos() {
        final int MAX_PRODUTOS = 100;
        Produto[] novosProdutos = new Produto[MAX_PRODUTOS];
        Produto novo;
        int quantosProdutos = 0;
        String descricao;
        double valorUnitario;
        String continuar = "s";
        do {
            System.out.println("CADASTRO DE PRODUTO");
            System.out.print("Descrição do produto: ");
            descricao = teclado.nextLine();
            System.out.print("Valor unitário do produto: ");
            valorUnitario = Double.parseDouble(teclado.nextLine().replace(",", "."));
            novo = new Produto();
            novo.registrar(descricao, valorUnitario);
            novosProdutos[quantosProdutos] = novo;
            quantosProdutos++;
            System.out.println();
            System.out.print("Continuar cadastro (s/n)?");
            continuar = teclado.nextLine();
        } while (continuar.toLowerCase().equals("s"));
        novosProdutos = Arrays.copyOf(novosProdutos, quantosProdutos);
        return novosProdutos;
    }

    /**
     * Encapsula o processo de adicionar um produto na venda: recebe a quantidade
     * vendida,
     * calcula o valor total a ser adicionado na venda e retorna este valor. Como o
     * valor do lote do produto é protegido,
     * a quantidade mínima de venda é de 1 unidade.
     * 
     * @param prod O produto a ser vendido.
     * @return O valor obtido com a venda de N unidades deste produto (N sendo
     *         definido pela pessoa operadora na execução)
     */
    public static double adicionarNaVenda(Produto prod) {
        System.out.println("Produto escolido : " + prod.descricaoProduto());
        System.out.println("Quantas unidades deseja? ");
        int quantidade = Integer.parseInt(teclado.nextLine());
        return prod.valorLote(quantidade);
    }

    public static void main(String[] args) {
        Produto[] produtos = cadastrarProdutos();
        int opcao = exibirMenu();
        do {
            switch (opcao) {
                case 1 -> iniciarVenda();
                case 2 -> exibirTotalVendido();
            }
            opcao = exibirMenu();
        } while (opcao != 0);
    }

    private static Object exibirTotalVendido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirTotalVendido'");
    }

    private static double iniciarVenda() {
        double valorVenda=0;
        do{
            Produto prod = escolherProduto();
            valorVenda+= adicionarNaVenda(prod);
        }while();
        return valorVenda;
    }

    public static int exibirMenu() {
        int opcao = 0;
        System.out.println("XULAMBS CONVENIÊNCIA");
        System.out.println("Escoha sua opção: ");
        System.out.println("1 - Iniciar Venda");
        System.out.println("2 - Exibir Total Vendido");
        System.out.println("0 - Sair");
        System.out.println("Digite sua escolha: ");
        return (opcao = Integer.parseInt(teclado.nextLine()));
    }
}
