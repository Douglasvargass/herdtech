package models;

import java.util.Objects;

public class Cliente {
	// Atributos
	private Integer id;
	private String nome;
	private Long cpf;
	private String dataNasc;
	private String nomeFazenda;
	private String endereco;
	
	// Construtores
	public Cliente() {
		
	}
	
	public Cliente(String nome, Long cpf, String dataNasc, String nomeFazenda, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.nomeFazenda = nomeFazenda;
		this.endereco = endereco;
	}

	public Cliente(Integer id, String nome, Long cpf, String dataNasc, String nomeFazenda, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.nomeFazenda = nomeFazenda;
		this.endereco = endereco;
	}

	// Getters e Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getNomeFazenda() {
		return nomeFazenda;
	}
	
	public void setNomeFazenda(String nomeFazenda) {
		this.nomeFazenda = nomeFazenda;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	// Exibição de dados
	@Override
	public String toString() {
		return "ID: " + id +
			   " | Nome do Cliente: " + nome +
			   " | CPF: " + cpf +
			   " | Data de Nascimento: " + dataNasc +
			   " | Nome da Fazenda: " + nomeFazenda +
			   " | Endereço: " + endereco;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return Objects.equals(id, cliente.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}