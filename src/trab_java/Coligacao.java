package trab_java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Coligacao implements Comparable<Coligacao> {
	//Attributes
	Set<Partido> col = new HashSet<Partido>();
	int votos_totais = 0;
	String nome;
	
	//Constructor
	public Coligacao(String[] pNomes, LinkedList<Partido> partidos) {
		this.nome = String.join(" / ",pNomes);
		
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
	
	public String getNome() {
		return this.nome;
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
		int cont = 0;
		
		for(Partido x : col) {
			txt += x.getNome();
			if(cont > col.size()) {
				txt +=" / ";
			}
			
		}
		
		return txt;
	}
	
	@Override
	public int compareTo(Coligacao x) {
		return x.getVotos() - this.getVotos();
	}
}
