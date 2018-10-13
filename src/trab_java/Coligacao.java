package trab_java;

import java.util.Set;

public class Coligacao {
	//Attributes
	Set<Partido> col;
	int votos_totais = 0;
	
	//Constructor
	public Coligacao(String[] pNomes, Set<Partido> partidos) {
		for(int i = 0; i < pNomes.length; i++) {
			boolean ok = false;
			for(Partido x: partidos) {
				if(x.getNome().equals(pNomes[i])) {
					addPartido(x);
					x.setColigacao(this);
					ok = true;
					break;
				}
			}
			
			if(!ok) {
				Partido novo = new Partido(pNomes[i]);
				addPartido(novo);
				partidos.add(novo);
			}
		}
	}
	
	//Getters
	public int getVotos() {
		return votos_totais;
	}
	
	//Methods
	public void addVotos(int n) {
		votos_totais += n;
	}
	
	public void addPartido(Partido p) {
		this.col.add(p);
	}

	@Override
	public String toString() {
		String txt = "Coligação: ";
		
		for(Partido x : col) {
			txt += x.getNome()+" / ";
		}
		
		return txt;
	}
}
