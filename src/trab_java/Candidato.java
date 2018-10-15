package trab_java;

public class Candidato implements Comparable<Candidato> {
	//Attributes
	String nome;
	int numero, votos;
	Partido partido;
	boolean eleito;
	
	//Constructor
	public Candidato(String nome, int numero, int votos, Partido partido, boolean eleito) {
		this.nome = nome;
		this.numero = numero;
		this.votos = votos;
		this.partido = partido;
		this.eleito = eleito;
		
		partido.addCandidato(this);
	}
		
	//Getters
	public int getVotos() {
		return this.votos;
	}
	
	public boolean isEleito() {
		return this.eleito;
	}
	
	//Methods
	@Override
	public String toString() {
		String txt = this.nome;
		
		txt += " (" + this.partido.getNome() + ", " + this.votos + " votos)";
		
		txt += " - Coligação: " + this.partido.getColigacao().getNome();
		
		
		return txt;
	}

	@Override
	public int compareTo(Candidato x) {
		return x.getVotos() - this.getVotos();
	}
}
