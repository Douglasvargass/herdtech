package tests;

import exceptions.GadoException;
import models.Cliente;
import models.Gado;
import services.ClienteService;
import services.GadoService;

public class GadoTest {

    private final GadoService gadoService = new GadoService();
    private final ClienteService clienteService = new ClienteService();
    private Cliente dono;
    private Gado gado;

    public void iniciar() {
        System.out.println("=== TESTES DE GADO ===");
        try {
            // Todo gado precisa de um dono: cria um cliente só para o teste
            dono = new Cliente("Dono do Teste", 12345678901L, "01/01/2000", "Fazenda Teste", "Rua Teste");
            clienteService.cadastrar(dono);

            listar();
            cadastrar();
            cadastrarInvalido();
            alterar();
            excluir();
        } catch (Exception e) {
            Resultado.registrar("Gado - Preparação do teste", false);
        } finally {
            limpar();
        }
    }

    // Teste automatizado da funcionalidade de listagem
    public void listar() {
        try {
            String lista = gadoService.listar();
            Resultado.registrar("Gado - Listar", !lista.isEmpty());
        } catch (Exception e) {
            Resultado.registrar("Gado - Listar", false);
        }
    }

    // Teste automatizado da funcionalidade de cadastro
    public void cadastrar() {
        try {
            gado = new Gado(dono, "Boi Teste", "Nelore", "Macho", "02/02/2024");
            boolean cadastrou = gadoService.cadastrar(gado);
            Resultado.registrar("Gado - Cadastrar", cadastrou && gado.getId() != null);
        } catch (Exception e) {
            Resultado.registrar("Gado - Cadastrar", false);
        }
    }

    // Teste do tratamento de erro: campos vazios devem lançar exceção
    public void cadastrarInvalido() {
        try {
            gadoService.cadastrar(new Gado(dono, "", "", "", ""));
            Resultado.registrar("Gado - Cadastrar com campos vazios", false);
        } catch (GadoException e) {
            Resultado.registrar("Gado - Cadastrar com campos vazios", true);
        } catch (Exception e) {
            Resultado.registrar("Gado - Cadastrar com campos vazios", false);
        }
    }

    // Teste automatizado da funcionalidade de alteração
    public void alterar() {
        try {
            gado.setNome("Boi Teste Alterado");
            boolean alterou = gadoService.alterar(gado);
            Gado doBanco = gadoService.buscarPorId(gado.getId());
            Resultado.registrar("Gado - Alterar", alterou && "Boi Teste Alterado".equals(doBanco.getNome()));
        } catch (Exception e) {
            Resultado.registrar("Gado - Alterar", false);
        }
    }

    // Teste automatizado da funcionalidade de exclusão
    public void excluir() {
        try {
            boolean excluiu = gadoService.excluir(gado.getId());
            Resultado.registrar("Gado - Excluir", excluiu && gadoService.buscarPorId(gado.getId()) == null);
        } catch (Exception e) {
            Resultado.registrar("Gado - Excluir", false);
        }
    }

    // Remove os dados criados pelo teste (primeiro o gado, depois o dono)
    private void limpar() {
        try {
            if (gado != null && gado.getId() != null) gadoService.excluir(gado.getId());
            if (dono != null && dono.getId() != null) clienteService.excluir(dono.getId());
        } catch (Exception e) {
            System.err.println("Não foi possível limpar os dados do teste de gado.");
        }
    }
}
