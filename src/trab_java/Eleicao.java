package trab_java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Eleicao {

	public static void main(String[] args) {
		System.out.println(args[0]);
		try {
			//Leitor do arquivo
			BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
			
			//Cria os agrupamentos necessarios
			LinkedList<Candidato> candidatos = new LinkedList<Candidato>();
			LinkedList<Partido> partidos = new LinkedList<Partido>();
			LinkedList<Coligacao> coligacoes = new LinkedList<Coligacao>();
			int vagas = 0;
			
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
				Coligacao col = null;
				for(Coligacao aux : coligacoes) {
					if(aux.getNome().equals(dados[3].split("-")[1])) {
						col = aux;
						break;
					}
				}
				if(col == null) col = new Coligacao(dados[3].split("-")[1].split(" / "),partidos);
				
				coligacoes.add(col);
				}
				
				Candidato candidato = new Candidato(nome, numero, votos, p, eleito);
				candidatos.add(candidato);
				p.addCandidato(candidato);
				if(eleito) vagas++;
			}
			
			arq.close();
			
			//Abrindo arquivo de relatorio
			PrintWriter relatorio = new PrintWriter (new OutputStreamWriter (new FileOutputStream ("relatorio.txt"), "UTF-8"));
			
			//Escrevi o numero de vagas
			relatorio.println("Numero de vagas: "+ vagas);
			relatorio.println();
			
			//Ordenaçao dos candidatos, partidos e coligacoes
			Collections.sort(candidatos);
			Collections.sort(partidos);
			Collections.sort(coligacoes);
			
			//Como o print abaixo já diz são os vereadores eleitos
			relatorio.println("Vereadores eleitos:");
			int aux = 1;
			for(Candidato x : candidatos) {
				if(x.isEleito()) {
					relatorio.println(aux++ + " - "+ x);
				}
			}
			
			relatorio.println();
			relatorio.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas");
			aux = 1;
			for(Candidato x : candidatos) {
				if(candidatos.indexOf(x) < vagas) {
					relatorio.println(aux++ + " - "+ x);
				}
			}
			
			relatorio.println();
			relatorio.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:(com sua posição no ranking de mais votados)");
			aux = 1;
			for(Candidato x : candidatos) {
				if(candidatos.indexOf(x) < vagas && !x.isEleito()) {
					relatorio.println(aux + " - "+ x);
				}
				aux++;
			}
			
			relatorio.println();
			relatorio.println("Eleitos, que se beneficiaram do sistema proporcional:(com sua posição no ranking de mais votados)");
			aux = 1;
			for(Candidato x : candidatos) {
				if(candidatos.indexOf(x) > vagas && x.isEleito()) {
					relatorio.println(aux + " - "+ x);
				}
				aux++;
			}
			
			relatorio.println();
			relatorio.println("Votação (nominal) das coligações e número de candidatos eleitos:");
			aux = 1;
			for(Coligacao x : coligacoes) {
				relatorio.println(aux++ + " - " + x);
			}
			
			relatorio.println();
			relatorio.println("Votação (nominal) dos partidos e número de candidatos eleitos:");
			aux = 1;
			for(Partido x : partidos) {
				relatorio.println(aux++ + " - " + x);
			}
		
			relatorio.close();
			
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
