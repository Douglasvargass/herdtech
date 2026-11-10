package controllers;

import exceptions.AplicacaoException;
import exceptions.ClienteException;
import exceptions.GadoException;
import views.HeedTechView;

public class HeedTechController {
    private final HeedTechView heedTechView = new HeedTechView();
    private final ClienteController clienteController = new ClienteController();
    private final GadoController gadoController = new GadoController();
    private final AplicacaoController aplicacaoController = new AplicacaoController();

    public void iniciar() throws ClienteException, GadoException, AplicacaoException {
        int opcao;

        // Menu HOME
        do {
            opcao = heedTechView.telaInicial();

            switch (opcao) {
                case 0:
                    // Função Menu Cliente
                    this.menuCliente();
                    break;
                case 1:
                    // Função Menu Gado
                    this.menuGado();
                    break;
                case 2:
                    // Função Menu Aplivação da Vacina
                    this.menuAplicacao();
                    break;
            }
        } while (opcao >= 0);
    }

    private void menuCliente() throws ClienteException {
        clienteController.iniciar();
    }

    private void menuGado() throws GadoException {
        gadoController.iniciar();
    }

    private void menuAplicacao() throws AplicacaoException {
        aplicacaoController.iniciar();
    }
}
