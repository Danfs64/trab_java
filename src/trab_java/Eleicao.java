package trab_java;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

public class Eleicao {
	HashSet<Candidato> candidatos;
	HashSet<Partido> partidos;
	HashSet<Coligacao> coligacoes;
	
	public void main(String[] args) {
		System.out.println(args[0]);
		try {
			//Leitor do arquivo
			BufferedReader arq = new BufferedReader(new FileReader(args[0]));
			
			this.candidatos = new HashSet<Candidato>();
			this.partidos = new HashSet<Partido>();
			this.coligacoes = new HashSet<Coligacao>();
			
			for(String linha = arq.readLine(); linha != null; linha = arq.readLine()) {
				//Strings obtidas a partir da separação da linha
				String[] dados = linha.split(";");
				
				//Dados do candidato
				boolean eleito = dados[0].contains("*");
				int numero = Integer.valueOf(dados[1]), votos = Integer.valueOf(dados[5]);
				String nome = dados[2];
				
				//Partido, coligação e candidato criados/adicionados 
				Partido p = new Partido(dados[3].split("-")[0]);
				partidos.add(p);
				
				Coligacao col = new Coligacao(dados[3].split("-")[1].split(" / "));
				coligacoes.add(col);
				
				Candidato candidato = new Candidato(nome, numero, votos, p, eleito);
				candidatos.add(candidato);
				
				
				
				
				
				
				
				
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("DEU MERDA");
		}
	}
}
