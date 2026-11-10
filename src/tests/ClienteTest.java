package tests;

import exceptions.ClienteException;
import models.Cliente;
import services.ClienteService;

public class ClienteTest {

    private final ClienteService clienteService = new ClienteService();
    private Cliente cliente;

    public void iniciar() {
        System.out.println("=== TESTES DE CLIENTE ===");
        try {
            listar();
            cadastrar();
            cadastrarInvalido();
            alterar();
        } finally {
            // Exclusão é testada por último e também limpa o banco
            excluir();
        }
    }

    // Teste automatizado da funcionalidade de listagem
    public void listar() {
        try {
            String lista = clienteService.listar();
            Resultado.registrar("Cliente - Listar", !lista.isEmpty());
        } catch (Exception e) {
            Resultado.registrar("Cliente - Listar", false);
        }
    }

    // Teste automatizado da funcionalidade de cadastro
    public void cadastrar() {
        try {
            cliente = new Cliente("Teste Cliente", 12345678901L, "01/01/2000", "Fazenda Teste", "Rua Teste");
            boolean cadastrou = clienteService.cadastrar(cliente);
            // O banco deve ter gerado o id do cliente
            Resultado.registrar("Cliente - Cadastrar", cadastrou && cliente.getId() != null);
        } catch (Exception e) {
            Resultado.registrar("Cliente - Cadastrar", false);
        }
    }

    // Teste do tratamento de erro: campos vazios devem lançar exceção
    public void cadastrarInvalido() {
        try {
            clienteService.cadastrar(new Cliente("", null, "", "", ""));
            Resultado.registrar("Cliente - Cadastrar com campos vazios", false);
        } catch (ClienteException e) {
            Resultado.registrar("Cliente - Cadastrar com campos vazios", true);
        } catch (Exception e) {
            Resultado.registrar("Cliente - Cadastrar com campos vazios", false);
        }
    }

    // Teste automatizado da funcionalidade de alteração
    public void alterar() {
        try {
            cliente.setNome("Teste Cliente Alterado");
            boolean alterou = clienteService.alterar(cliente);
            // Busca no banco para confirmar que o nome mudou
            Cliente doBanco = clienteService.buscarPorId(cliente.getId());
            Resultado.registrar("Cliente - Alterar", alterou && "Teste Cliente Alterado".equals(doBanco.getNome()));
        } catch (Exception e) {
            Resultado.registrar("Cliente - Alterar", false);
        }
    }

    // Teste automatizado da funcionalidade de exclusão
    public void excluir() {
        try {
            boolean excluiu = clienteService.excluir(cliente.getId());
            // Depois de excluir, o cliente não pode mais existir no banco
            Resultado.registrar("Cliente - Excluir", excluiu && clienteService.buscarPorId(cliente.getId()) == null);
        } catch (Exception e) {
            Resultado.registrar("Cliente - Excluir", false);
        }
    }
}
