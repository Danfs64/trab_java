package trab_java;
import java.util.LinkedList;

public class Partido {
	String nome;
	int total_votos;
	LinkedList<Candidato> candidatos;
	
	public Partido(String nome) {
		this.nome = nome;
		this.total_votos = 0;
		this.candidatos = new LinkedList<Candidato>();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int totalVotos() {
		return this.total_votos;
	}
	
	public void addCandidato(Candidato x) {
		this.candidatos.add(x);
		this.total_votos += x.getVotos();
	}

}
