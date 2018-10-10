package trab_java;

public class Candidato {
	//Attributes
	String nome;
	int numero;
	int votos;
	Partido partido;
	Coligacao col = null;
	
	//Constructor
	public Candidato(String nome, int numero, int votos, Partido partido) {
		this.nome = nome;
		this.numero = numero;
		this.votos = votos;
		this.partido = partido;
		this.col = partido.getColigacao();
		
		partido.addCandidato(this);
	}
		
	//Getters
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
