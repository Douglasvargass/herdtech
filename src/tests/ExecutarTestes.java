package tests;

// Executa todos os testes automatizados, na ordem dos CRUDs: Cliente, Gado e Aplicação.
// Precisa do banco de dados ligado (pede a senha numa janela, como o programa principal).
public class ExecutarTestes {
    static void main() {
        new ClienteTest().iniciar();
        new GadoTest().iniciar();
        new AplicacaoTest().iniciar();

        System.out.println();
        System.out.println("=== RESUMO ===");
        System.out.println("Passaram: " + Resultado.getPassou());
        System.out.println("Falharam: " + Resultado.getFalhou());

        // Encerra o programa (a janela de senha mantém o Java aberto)
        System.exit(Resultado.getFalhou() == 0 ? 0 : 1);
    }
}
