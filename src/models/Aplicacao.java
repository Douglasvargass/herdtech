package models;

import java.util.Objects;

public class Aplicacao {
	// Atributos
	private Integer id;
	private Cliente dono;
	private Gado gado;
	private String vacina;
	private String dataAplicacao;
	private String proximaVacina;
	private String quemAplicou;
	
	// Construtores
	public Aplicacao() {
		
	}
	
	public Aplicacao(Cliente dono, Gado gado, String vacina, String dataAplicacao, String proximaVacina, String quemAplicou) {
		this.dono = dono;
		this.gado = gado;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
		this.proximaVacina = proximaVacina;
		this.quemAplicou = quemAplicou;
	}

	public Aplicacao(Integer id, Cliente dono, Gado gado, String vacina, String dataAplicacao, String proximaVacina, String quemAplicou) {
		this.id = id;
		this.dono = dono;
		this.gado = gado;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
		this.proximaVacina = proximaVacina;
		this.quemAplicou = quemAplicou;
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
	
	public Gado getGado() {
		return gado;
	}
	
	public void setGado(Gado gado) {
		this.gado = gado;
	}
	
	public String getVacina() {
		return vacina;
	}
	
	public void setVacina(String vacina) {
		this.vacina = vacina;
	}
	
	public String getDataAplicacao() {
		return dataAplicacao;
	}
	
	public void setDataAplicacao(String dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	
	public String getProximaVacina() {
		return proximaVacina;
	}
	
	public void setProximaVacina(String proximaVacina) {
		this.proximaVacina = proximaVacina;
	}
	
	public String getQuemAplicou() {
		return quemAplicou;
	}
	
	public void setQuemAplicou(String quemAplicou) {
		this.quemAplicou = quemAplicou;
	}
	
	// Exibição de dados
	@Override
	public String toString() {
		return "Cliente: ID " + dono.getId() +
			   " - Dono: " + dono.getNome() +
			   " | ID do Gado: " + gado.getId() +
			   " | ID da Vacina: " + id +
			   " | Vacina: " + vacina +
			   " | Data da Aplicação: " + dataAplicacao + 
			   " | Próxima Vacina: " + proximaVacina + 
			   " | Quem Aplicou: " + quemAplicou;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Aplicacao vacina = (Aplicacao) o;
		return Objects.equals(id, vacina.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
