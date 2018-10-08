package trab_java;
import java.util.LinkedList;


public class Coligacao {
	LinkedList<Partido> col;
	int votos_totais_col;
	
	public Coligacao(String[] pNomes, LinkedList<Partido> partidos) {
		for(int i = 0; i < pNomes.length; i++) {
			for(Partido x: partidos) {
				if(x.getNome().equals(pNomes[i])) {
					col.add(x);
				}
			}
		}
	}
	
	public int getVotos() {
		this.votos_totais_col = 0;
		for(Partido x : this.col) {
			this.votos_totais_col += x.total_votos;
		}
		
		return this.votos_totais_col;
	}

}
