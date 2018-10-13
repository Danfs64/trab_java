package trab_java;

public class Candidato {
	//Attributes
	String nome;
	int numero;
	int votos;
	Partido partido;
	Coligacao col = null;
	boolean eleito;
	
	//Constructor
	public Candidato(String nome, int numero, int votos, Partido partido, boolean eleito) {
		this.nome = nome;
		this.numero = numero;
		this.votos = votos;
		this.partido = partido;
		this.col = partido.getColigacao();
		this.eleito = eleito;
		
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
	
	public boolean isEleito() {
		return this.eleito;
	}
	
	//Methods
	@Override
	public String toString() {
		String txt = this.nome;
		
		txt += "(" + this.partido.getNome() + ", " + this.votos + "votos)";
		
		if(this.partido.hasColigacao()) txt += " - " + this.col;
		
		return txt;
	}
}
