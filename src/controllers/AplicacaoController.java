package controllers;

import exceptions.AplicacaoException;
import models.Aplicacao;
import models.Gado;
import services.AplicacaoService;
import services.GadoService;
import views.AplicacaoView;
import views.GadoView;

public class AplicacaoController {
    private final AplicacaoView aplicacaoView = new AplicacaoView();
    private final AplicacaoService aplicacaoService = new AplicacaoService();
    private final GadoView gadoView = new GadoView();
    private final GadoService gadoService = new GadoService();

    public void iniciar() throws AplicacaoException {
        int opcao;

        do {
            opcao = aplicacaoView.telaInicial();

            switch (opcao) {
                case 0:
                    this.listar();
                    break;
                case 1:
                    this.cadastrar();
                    break;
                case 2:
                    this.alterar();
                    break;
                case 3:
                    this.excluir();
                    break;
            }
        } while (opcao >= 0);
    }

    private void listar() {
        try {
            String lista = aplicacaoService.listar();
            aplicacaoView.exibir("HISTÓRICO DE VACINAS", lista);
        } catch (Exception e) {
            aplicacaoView.alertaErro("HISTÓRICO DE VACINAS", e.getMessage());
        }
    }

    private void cadastrar() throws AplicacaoException {
        try {
            Integer id = gadoView.informarId("CADASTRO DE APLICAÇÃO", gadoService.listarGado());
            if (id == null) return;

            Gado gado = gadoService.buscarPorId(id);
            if (gado == null) {
                gadoView.alertaErro("CADASTRO DE APLICAÇÃO", "Ops! Gado inexistente");
                return;
            }

            Aplicacao aplicacao = aplicacaoView.informarDados("CADASTRO DE APLICAÇÃO", new Aplicacao());
            if (aplicacao == null) return;
            aplicacao.setDono(gado.getDono());
            aplicacao.setGado(gado);

            if (aplicacaoService.cadastrar(aplicacao)) {
                aplicacaoView.alertaSucesso("CADASTRO DE APLICAÇÃO", "Aplicação Cadastrada");
            } else {
                aplicacaoView.alertaErro("CADASTRO DE APLICAÇÃO", "Erro ao Cadastrar!");
            }
        } catch (Exception e) {
            aplicacaoView.alertaErro("CADASTRO DE APLICAÇÃO", e.getMessage());
        }
    }

    private void excluir() {
        try {
            Integer id = aplicacaoView.informarId("EXCLUIR APLICAÇÃO", aplicacaoService.listar());
            if (id == null) return;

            Aplicacao excluirAplicacao = aplicacaoService.buscarPorId(id);
            if (excluirAplicacao == null) {
                aplicacaoView.alertaErro("EXCLUIR APLICAÇÃO", "Ops! Aplicação inexistente");
                return;
            }

            int confirma = aplicacaoView.alertaConfirmacao("EXCLUIR APLICAÇÃO",
                    "Deseja realmente Excluir a Aplicação"
                            + excluirAplicacao.getVacina()
                            + " (Id: " + excluirAplicacao.getId() + ")?"
            );

            if (confirma == 0) {
                if (aplicacaoService.excluir(id)) {
                    aplicacaoView.alertaSucesso("EXCLUIR APLICAÇÃO", "Aplicação Excluido com Sucesso");
                } else {
                    aplicacaoView.alertaErro("EXCLUIR APLICAÇÃO", "Ops! Erro ao Excluir Aplicação");
                }
            }
        } catch (Exception e) {
            aplicacaoView.alertaErro("EXCLUIR APLICAÇÃO", e.getMessage());
        }
    }

    private void alterar() {
        try {
            Integer id = aplicacaoView.informarId("ALTERAR APLICAÇÃO", aplicacaoService.listar());
            if (id == null) return;

            Aplicacao alterarPorId = aplicacaoService.buscarPorId(id);
            if (alterarPorId == null) {
                aplicacaoView.alertaErro("ALTERAR APLICAÇÃO", "Ops! Aplicação inexistente");
                return;
            }

            Aplicacao alterarAplicacao = new Aplicacao(alterarPorId.getId(), alterarPorId.getDono(), alterarPorId.getGado(), alterarPorId.getVacina(), alterarPorId.getDataAplicacao(), alterarPorId.getProximaVacina(), alterarPorId.getQuemAplicou());
            alterarAplicacao = aplicacaoView.informarDados("ALTERAR APLICAÇÃO", alterarAplicacao);
            if (alterarAplicacao == null) return;

            if (aplicacaoService.alterar(alterarAplicacao)) {
                aplicacaoView.alertaSucesso("ALTERAR APLICAÇÃO", "Aplicação Alterado com Sucesso");
            } else {
                aplicacaoView.alertaErro("ALTERAR APLICAÇÃO", "Ops! Erro ao Alterar Aplicação");
            }
        } catch (Exception e) {
            aplicacaoView.alertaErro("ALTERAR APLICAÇÃO", e.getMessage());
        }
    }
}
