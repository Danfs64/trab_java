package trab_java;

public class Candidato {
	String nome;
	int votos;
	int numero;
	Partido partido;
	Coligacao col;
	
	public Candidato(String nome, int votos, int numero, Partido partido, Coligacao col) {
		this.nome = nome;
		this.votos = votos;
		this.numero = numero;
		this.partido = partido;
		this.col = col;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getVotos() {
		return this.votos;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public Partido getPartido() {
		return this.partido;
	}
	
	public Coligacao getColigacao() {
		return this.col;
	}
}
