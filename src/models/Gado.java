package models;

import java.util.Objects;

public class Gado {
	// Atributos
	private Integer id;
	private Cliente dono;
	private String nome;
	private String raca;
	private String genero;
	private String dataNasc;
	
	// Construtores
	public Gado() {
		
	}
	
	public Gado(Cliente dono, String nome, String raca, String genero, String dataNasc) {
		this.dono = dono;
		this.nome = nome;
		this.raca = raca;
		this.genero = genero;
		this.dataNasc = dataNasc;
	}

	public Gado(Integer id, Cliente dono, String nome, String raca, String genero, String dataNasc) {
		this.id = id;
		this.dono = dono;
		this.nome = nome;
		this.raca = raca;
		this.genero = genero;
		this.dataNasc = dataNasc;
	}

	// Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	// Exibição de dados
	@Override
	public String toString() {
		return "Cliente: ID " + dono.getId() +
			   " - Dono: " + dono.getNome() +
			   " | ID do Gado: " + id +
			   " | Apelido do Animal: " + nome +
			   " | Raça: " + raca +
			   " | Genero: " + genero +
			   " | Data de Nascimento do Animal: " + dataNasc;
	}

	public String listaGado() {
		return "ID do Gado: " + id +
			   " | Apelido do Animal: " + nome +
			   " | Raça: " + raca +
			   " | Genero: " + genero +
			   " | Data de Nascimento do Animal: " + dataNasc;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Gado gado = (Gado) o;
		return Objects.equals(id, gado.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}