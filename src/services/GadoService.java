package services;

import dao.GadoDAO;
import exceptions.GadoException;
import models.Cliente;
import models.Gado;
import java.util.ArrayList;

public class GadoService {
    // O Service usa o DAO para falar com o banco
    private final GadoDAO gadoDAO = new GadoDAO();

    public String listar() {
        String lista = "";
        ArrayList<Gado> gados = gadoDAO.listar();

        if (gados.size() > 0) {
            for (Gado gado : gados) {
                lista += gado + "\n";
            }
        } else {
            lista = "Nenhum Gado encontrado!";
        }
        return lista;
    }

    public String listarGado() {
        String lista = "";
        ArrayList<Gado> gados = gadoDAO.listar();

        if (gados.size() > 0) {
            for (Gado gado : gados) {
                lista += gado.listaGado() + "\n";
            }
        } else {
            lista = "Nenhum Gado encontrado!";
        }
        return lista;
    }

    public Boolean cadastrar (Gado gado) throws GadoException {
        if (validarDados(gado.getDono(), gado.getNome(), gado.getRaca(), gado.getGenero(), gado.getDataNasc())) {
            return gadoDAO.cadastrar(gado);
        }
        return false;
    }

    private Boolean validarDados (Cliente dono, String nome, String raca, String genero, String dataNasc) throws GadoException {
        String erros = "";

        if (nome.trim().isEmpty()) erros += "\n- Campo 'Apelido do Animal' é obrigatório!";
        if (raca.trim().isEmpty()) erros += "\n- Campo 'Raça do Animal' é obrigatório!";
        if (genero.trim().isEmpty()) erros += "\n- Campo 'Genero' é obrigatório!";
        if (dataNasc.trim().isEmpty()) erros += "\n- Campo 'Data de Nascimento do Animal' é obrigatório!";

        if (!erros.isEmpty()) {
            throw new GadoException("Ops! Erros Encontrados: " + erros);
        }
        return true;
    }

    public Gado buscarPorId (Integer id) {
        return gadoDAO.buscarPorId(id);
    }

    public Boolean excluir(Integer id) {
        return gadoDAO.excluir(id);
    }

    public Boolean alterar(Gado gado) throws GadoException {
        if (validarDados(gado.getDono(), gado.getNome(), gado.getRaca(), gado.getGenero(), gado.getDataNasc())) {
            return gadoDAO.alterar(gado);
        }
        return false;
    }
}
