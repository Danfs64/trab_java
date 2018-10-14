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
	public Coligacao(String pNomes, LinkedList<Partido> partidos) {
		if(Character.isWhitespace(pNomes.charAt(0))) {
			pNomes = pNomes.substring(1);
		}
		this.nome = pNomes;
		String[] aux = pNomes.split("/");
		
		for(int i = 0; i < aux.length; i++) {
			boolean ok = false;
			for(Partido x: partidos) {
				if(x.getNome().replaceAll(" ", "").equals(aux[i].replaceAll(" ", ""))) {
					addPartido(x);
					x.setColigacao(this);
					ok = true;
					break;
				}
			}
			
			if(!ok) {
				if(Character.isWhitespace(aux[i].charAt(0))) {
					aux[i] = aux[i].substring(1);
				}
				Partido novo = new Partido(aux[i]);
				addPartido(novo);
				partidos.add(novo);
			}
		}
	}
	
	//Getters
	public int getVotos() {
		int x = 0;
		
		for(Partido y : this.col) {
			x += y.getVotos();
		}
		
		return x;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	//Methods
	
	public void addPartido(Partido p) {
		this.col.add(p);
	}
	
	public int nEleitos() {
		int aux = 0;
		for(Partido x : this.col) {
			aux += x.getEleitos();
		}
		
		return aux;
	}

	@Override
	public String toString() {
		String txt = "Coligação: ";
		
		txt += this.getNome() + ", " + this.getVotos() + " votos, " + this.nEleitos();   
		
		if (this.nEleitos() > 1) txt += " candidatos eleitos";
		else txt += " candidato eleito";
		
		return txt;
	}
	
	@Override
	public int compareTo(Coligacao x) {
		return x.getVotos() - this.getVotos();
	}
}
