package trab_java;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

public class Eleicao {

	public static void main(String[] args) {
		System.out.println(args[0]);
		try {
			//Leitor do arquivo
			BufferedReader arq = new BufferedReader(new FileReader(args[0]));
			//Cria os agrupamentos necessarios
			HashSet<Candidato> candidatos = new HashSet<Candidato>();
			HashSet<Partido> partidos = new HashSet<Partido>();
			HashSet<Coligacao> coligacoes = new HashSet<Coligacao>();
			
			//Pula a primeira linha
			arq.readLine();
			
			for(String linha = arq.readLine(); linha != null; linha = arq.readLine()) {
				//Strings obtidas a partir da separacao da linha
				String[] dados = linha.split(";");
				
				//Dados do candidato
				boolean eleito = dados[0].contains("*");
				int numero = Integer.parseInt(dados[1]), votos = Integer.parseInt(dados[4].replace(".",""));
				String nome = dados[2];
				
				//Partido, coligacao e candidato sendo criados/adicionados 
				Partido p = null;
				for(Partido aux : partidos) {
					if(aux.getNome().equals(dados[3].split("-")[0])) {
						p = aux;
						break;
					}
				}
				if(p == null) p = new Partido(dados[3].split("-")[0]);
				partidos.add(p);
				
				if(dados[3].split("-").length > 1) {
				Coligacao col = new Coligacao(dados[3].split("-")[1].split(" / "),partidos);
				coligacoes.add(col);
				}
				
				Candidato candidato = new Candidato(nome, numero, votos, p, eleito);
				candidatos.add(candidato);
				p.addCandidato(candidato);
			}
			
			arq.close();
			
//			for(Candidato c : candidatos) {
//				System.out.println(c);
//			}
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("DEU MERDA");
		}
		finally {
			System.out.println("TERMINOU!");
		}
	}
}
