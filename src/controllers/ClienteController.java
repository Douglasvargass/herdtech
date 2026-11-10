package controllers;

import exceptions.ClienteException;
import models.Cliente;
import services.ClienteService;
import views.ClienteView;

public class ClienteController {
    private final ClienteView clienteView = new ClienteView();
    private final ClienteService clienteService = new ClienteService();

    public void iniciar() throws ClienteException {
        int opcao;

        do {
            opcao = clienteView.telaInicial();
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
            String lista = clienteService.listar();
            clienteView.exibir("LISTA DE CLIENTES", lista);
        } catch (Exception e) {
            clienteView.alertaErro("LISTA DE CLIENTES", e.getMessage());
        }
    }

    private void cadastrar() throws ClienteException {
        try {
            Cliente cliente = clienteView.informarDados("CADASTRO DE CLIENTE", new Cliente());
            if (cliente == null) return;

            if (clienteService.cadastrar(cliente)) {
                clienteView.alertaSucesso("CADASTRO DE CLIENTE", "Cliente Cadastrado");
            } else {
                clienteView.alertaErro("CADASTRO DE CLIENTE", "Erro ao Cadastrar!");
            }
        } catch (Exception e) {
            clienteView.alertaErro("CADASTRO DE CLIENTE", e.getMessage());
        }
    }

    private void excluir() {
        try {
            Integer id = clienteView.informarId("EXCLUIR CLIENTE", clienteService.listar());
            if (id == null) return;

            Cliente excluirCliente = clienteService.buscarPorId(id);
            if (excluirCliente == null) {
                clienteView.alertaErro("EXCLUIR CLIENTE", "Ops! Cliente inexistente");
                return;
            }

            int confirma = clienteView.alertaConfirmacao("EXCLUIR CLIENTE",
                    "Deseja realmente Excluir o Cliente"
                            + excluirCliente.getNome()
                            + " (Id: " + excluirCliente.getId() + ")?"
            );

            if (confirma == 0) {
                if (clienteService.excluir(id)) {
                    clienteView.alertaSucesso("EXCLUIR CLIENTE", "Cliente Excluido com Sucesso");
                } else {
                    clienteView.alertaErro("EXCLUIR CLIENTE", "Ops! Erro ao Excluir cliente");
                }
            }
        } catch (Exception e) {
            clienteView.alertaErro("EXCLUIR CLIENTE", e.getMessage());
        }
    }

    private void alterar() {
        try {
            Integer id = clienteView.informarId("ALTERAR CLIENTE", clienteService.listar());
            if (id == null) return;

            Cliente alterarPorId = clienteService.buscarPorId(id);
            if (alterarPorId == null) {
                clienteView.alertaErro("ALTERAR CLIENTE", "Ops! Cliente inexistente");
                return;
            }

            Cliente alterarCliente = new Cliente (alterarPorId.getId(), alterarPorId.getNome(), alterarPorId.getCpf(), alterarPorId.getDataNasc(), alterarPorId.getNomeFazenda(), alterarPorId.getEndereco());
            alterarCliente = clienteView.informarDados("ALTERAR CLIENTE", alterarCliente);
            if (alterarCliente == null) return;

            if (clienteService.alterar(alterarCliente)) {
                clienteView.alertaSucesso("ALTERAR CLIENTE", "Cliente Alterado com Sucesso");
            } else {
                clienteView.alertaErro("ALTERAR CLIENTE", "Ops! Erro ao Alterar cliente");
            }
        } catch (Exception e) {
            clienteView.alertaErro("ALTERAR CLIENTE", e.getMessage());
        }
    }
}
