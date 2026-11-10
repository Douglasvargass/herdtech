package controllers;

import exceptions.GadoException;
import models.Cliente;
import models.Gado;
import services.ClienteService;
import services.GadoService;
import views.ClienteView;
import views.GadoView;

public class GadoController {
    private final GadoView gadoView = new GadoView();
    private final GadoService gadoService = new GadoService();
    private final ClienteView clienteView = new ClienteView();
    private final ClienteService clienteService = new ClienteService();

    public void iniciar() throws GadoException {
        int opcao;

        do {
            opcao = gadoView.telaInicial();
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
            String lista = gadoService.listar();
            gadoView.exibir("LISTA DE GADO", lista);
        } catch (Exception e) {
            gadoView.alertaErro("LISTA DE GADO", e.getMessage());
        }
    }

    private void cadastrar() throws GadoException {
        try {
            Integer id = clienteView.informarId("CADASTRO DO GADO", clienteService.listar());
            if (id == null) return;

            Cliente dono = clienteService.buscarPorId(id);
            if (dono == null) {
                clienteView.alertaErro("CADASTRO DO GADO", "Ops! Cliente inexistente");
                return;
            }

            Gado gado = gadoView.informarDados("CADASTRO DO GADO", new Gado());
            if (gado == null) return;
            gado.setDono(dono);

            if (gadoService.cadastrar(gado)) {
                gadoView.alertaSucesso("CADASTRO DO GADO", "Gado Cadastrado");
            } else {
                gadoView.alertaErro("CADASTRO DO GADO", "Erro ao Cadastrar!");
            }
        } catch (Exception e) {
            gadoView.alertaErro("CADASTRO DO GADO", e.getMessage());
        }
    }

    private void excluir() {
        try {
            Integer id = gadoView.informarId("EXCLUIR GADO", gadoService.listar());
            if (id == null) return;

            Gado excluirGado = gadoService.buscarPorId(id);
            if (excluirGado == null) {
                gadoView.alertaErro("EXCLUIR GADO", "Ops! Gado inexistente");
                return;
            }

            int confirma = gadoView.alertaConfirmacao("EXCLUIR GADO",
                    "Deseja realmente Excluir o Animal"
                            + excluirGado.getNome()
                            + " (Id: " + excluirGado.getId() + ")?"
            );

            if (confirma == 0) {
                if (gadoService.excluir(id)) {
                    gadoView.alertaSucesso("EXCLUIR GADO", "Gado Excluido com Sucesso");
                } else {
                    gadoView.alertaErro("EXCLUIR GADO", "Ops! Erro ao Excluir Gado");
                }
            }
        } catch (Exception e) {
            gadoView.alertaErro("EXCLUIR GADO", e.getMessage());
        }
    }

    private void alterar() {
        try {
            Integer id = gadoView.informarId("ALTERAR GADO", gadoService.listar());
            if (id == null) return;

            Gado alterarPorId = gadoService.buscarPorId(id);
            if (alterarPorId == null) {
                gadoView.alertaErro("ALTERAR GADO", "Ops! Gado inexistente");
                return;
            }

            Gado alterarGado = new Gado (alterarPorId.getId(), alterarPorId.getDono(), alterarPorId.getNome(), alterarPorId.getRaca(), alterarPorId.getGenero(), alterarPorId.getDataNasc());
            alterarGado = gadoView.informarDados("ALTERAR GADO", alterarGado);
            if (alterarGado == null) return;

            if (gadoService.alterar(alterarGado)) {
                gadoView.alertaSucesso("ALTERAR GADO", "Gado Alterado com Sucesso");
            } else {
                gadoView.alertaErro("ALTERAR GADO", "Ops! Erro ao Alterar Gado");
            }
        } catch (Exception e) {
            gadoView.alertaErro("ALTERAR Gado", e.getMessage());
        }
    }
}
