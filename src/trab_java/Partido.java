package trab_java;
import java.util.HashSet;

public class Partido {
	String nome;
	int total_votos;
	HashSet<Candidato> candidatos;
	
	public Partido(String nome) {
		this.nome = nome;
		this.total_votos = 0;
		this.candidatos = new HashSet<Candidato>();
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
