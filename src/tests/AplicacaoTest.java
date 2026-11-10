package tests;

import exceptions.AplicacaoException;
import models.Aplicacao;
import models.Cliente;
import models.Gado;
import services.AplicacaoService;
import services.ClienteService;
import services.GadoService;

public class AplicacaoTest {

    private final AplicacaoService aplicacaoService = new AplicacaoService();
    private final GadoService gadoService = new GadoService();
    private final ClienteService clienteService = new ClienteService();
    private Cliente dono;
    private Gado gado;
    private Aplicacao aplicacao;

    public void iniciar() {
        System.out.println("=== TESTES DE APLICAÇÃO ===");
        try {
            // Toda aplicação precisa de um gado (e de um dono): cria os dois só para o teste
            dono = new Cliente("Dono do Teste", 12345678901L, "01/01/2000", "Fazenda Teste", "Rua Teste");
            clienteService.cadastrar(dono);
            gado = new Gado(dono, "Boi Teste", "Nelore", "Macho", "02/02/2024");
            gadoService.cadastrar(gado);

            listar();
            cadastrar();
            cadastrarInvalido();
            alterar();
            excluir();
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Preparação do teste", false);
        } finally {
            limpar();
        }
    }

    // Teste automatizado da funcionalidade de listagem
    public void listar() {
        try {
            String lista = aplicacaoService.listar();
            Resultado.registrar("Aplicação - Listar", !lista.isEmpty());
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Listar", false);
        }
    }

    // Teste automatizado da funcionalidade de cadastro
    public void cadastrar() {
        try {
            aplicacao = new Aplicacao(dono, gado, "Febre Aftosa", "01/11/2026", "01/05/2027", "Veterinário");
            boolean cadastrou = aplicacaoService.cadastrar(aplicacao);
            Resultado.registrar("Aplicação - Cadastrar", cadastrou && aplicacao.getId() != null);
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Cadastrar", false);
        }
    }

    // Teste do tratamento de erro: campos vazios devem lançar exceção
    public void cadastrarInvalido() {
        try {
            aplicacaoService.cadastrar(new Aplicacao(dono, gado, "", "", "", ""));
            Resultado.registrar("Aplicação - Cadastrar com campos vazios", false);
        } catch (AplicacaoException e) {
            Resultado.registrar("Aplicação - Cadastrar com campos vazios", true);
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Cadastrar com campos vazios", false);
        }
    }

    // Teste automatizado da funcionalidade de alteração
    public void alterar() {
        try {
            aplicacao.setVacina("Brucelose");
            boolean alterou = aplicacaoService.alterar(aplicacao);
            Aplicacao doBanco = aplicacaoService.buscarPorId(aplicacao.getId());
            Resultado.registrar("Aplicação - Alterar", alterou && "Brucelose".equals(doBanco.getVacina()));
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Alterar", false);
        }
    }

    // Teste automatizado da funcionalidade de exclusão
    public void excluir() {
        try {
            boolean excluiu = aplicacaoService.excluir(aplicacao.getId());
            Resultado.registrar("Aplicação - Excluir", excluiu && aplicacaoService.buscarPorId(aplicacao.getId()) == null);
        } catch (Exception e) {
            Resultado.registrar("Aplicação - Excluir", false);
        }
    }

    // Remove os dados criados pelo teste (aplicação, gado e dono, nessa ordem)
    private void limpar() {
        try {
            if (aplicacao != null && aplicacao.getId() != null) aplicacaoService.excluir(aplicacao.getId());
            if (gado != null && gado.getId() != null) gadoService.excluir(gado.getId());
            if (dono != null && dono.getId() != null) clienteService.excluir(dono.getId());
        } catch (Exception e) {
            System.err.println("Não foi possível limpar os dados do teste de aplicação.");
        }
    }
}
