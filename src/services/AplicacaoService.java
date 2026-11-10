package services;

import dao.AplicacaoDAO;
import exceptions.AplicacaoException;
import models.Aplicacao;
import models.Gado;
import java.util.ArrayList;

public class AplicacaoService {
    // O Service usa o DAO para falar com o banco
    private final AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();

    public String listar() {
        String lista = "";
        ArrayList<Aplicacao> aplicacoes = aplicacaoDAO.listar();

        if (aplicacoes.size() > 0) {
            for (Aplicacao aplicacao : aplicacoes) {
                lista += aplicacao + "\n";
            }
        } else {
            lista = "Nenhuma Aplicação de Vacina encontrada!";
        }
        return lista;
    }

    public Boolean cadastrar (Aplicacao aplicacao) throws AplicacaoException {
        if (validarDados(aplicacao.getGado(), aplicacao.getVacina(), aplicacao.getDataAplicacao(), aplicacao.getProximaVacina(), aplicacao.getQuemAplicou())) {
            return aplicacaoDAO.cadastrar(aplicacao);
        }
        return false;
    }

    private Boolean validarDados (Gado gado, String vacina, String dataAplicacao, String proximaAplicacao, String quemAplicou) throws AplicacaoException {
        String erros = "";

        if (vacina.trim().isEmpty()) erros += "\n- Campo 'Nome da Vacina' é obrigatório!";
        if (dataAplicacao.trim().isEmpty()) erros += "\n- Campo 'Data da Aplicação' é obrigatório!";
        if (proximaAplicacao.trim().isEmpty()) erros += "\n- Campo 'Próxima vacina (data)' é obrigatório!";
        if (quemAplicou.trim().isEmpty()) erros += "\n- Campo 'Quem aplicou? (Veterinário ou Dono)' é obrigatório!";

        if (!erros.isEmpty()) {
            throw new AplicacaoException("Ops! Erros Encontrados: " + erros);
        }
        return true;
    }

    public Aplicacao buscarPorId (Integer id) {
        return aplicacaoDAO.buscarPorId(id);
    }

    public Boolean excluir(Integer id) {
        return aplicacaoDAO.excluir(id);
    }

    public Boolean alterar(Aplicacao aplicacao) throws AplicacaoException {
        if (validarDados(aplicacao.getGado(), aplicacao.getVacina(), aplicacao.getDataAplicacao(), aplicacao.getProximaVacina(), aplicacao.getQuemAplicou())) {
            return aplicacaoDAO.alterar(aplicacao);
        }
        return false;
    }
}
