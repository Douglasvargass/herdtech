package services;

import dao.ClienteDAO;
import exceptions.ClienteException;
import models.Cliente;
import java.util.ArrayList;

public class ClienteService {
	// O Service usa o DAO para falar com o banco
	private final ClienteDAO clienteDAO = new ClienteDAO();

	public String listar() {
		String lista = "";
		ArrayList<Cliente> clientes = clienteDAO.listar();

		if (clientes.size() > 0) {
			for (Cliente cliente : clientes) {
				lista += cliente + "\n";
			}
		} else {
			lista = "Nenhum cliente encontrado!";
		}
		return lista;
	}

	public Boolean cadastrar (Cliente cliente) throws ClienteException {
		if (validarDados(cliente.getNome(), cliente.getCpf(), cliente.getDataNasc(), cliente.getNomeFazenda(), cliente.getEndereco())) {
			return clienteDAO.cadastrar(cliente);
		}
		return false;
	}

	private Boolean validarDados (String nome, Long cpf, String dataNasc, String nomeFazenda, String endereco) throws ClienteException {
		String erros = "";

		if (nome.trim().isEmpty()) erros += "\n- Campo 'Nome' é obrigatório!";
		if (cpf == null) erros += "\n- Campo 'CPF' é obrigatório e deve ser numérico!";
		if (dataNasc.trim().isEmpty()) erros += "\n- Campo 'Data de Nascimento' é obrigatório!";
		if (nomeFazenda.trim().isEmpty()) erros += "\n- Campo 'Nome da Fazenda' é obrigatório!";
		if (endereco.trim().isEmpty()) erros += "\n- Campo 'Endereço' é obrigatório!";

		if (!erros.isEmpty()) {
			throw new ClienteException("Ops! Erros Encontrados: " + erros);
		}
		return true;
	}

	public Cliente buscarPorId (Integer id) {
		return clienteDAO.buscarPorId(id);
	}

	public Boolean excluir(Integer id) {
		return clienteDAO.excluir(id);
	}

	public Boolean alterar(Cliente cliente) throws ClienteException {
		if (validarDados(cliente.getNome(), cliente.getCpf(), cliente.getDataNasc(), cliente.getNomeFazenda(), cliente.getEndereco())) {
			return clienteDAO.alterar(cliente);
		}
		return false;
	}
}
