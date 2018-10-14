package trab_java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Eleicao {
	
	public static void main(String[] args) {
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
				if(eleito) vagas++;
				int numero = Integer.parseInt(dados[1]), votos = Integer.parseInt(dados[4].replace(".",""));
				String nome = dados[2];
				
				//Partido, coligacao e candidato sendo criados/adicionados 
				Coligacao col = null;
				String nome_Col;
				
				if(dados[3].split("-").length > 1) nome_Col = dados[3].split("-")[1].replaceAll(" ", "");
				else nome_Col = dados[3].replaceAll(" ", "");
				
				for(Coligacao aux : coligacoes) {
					if(aux.getNome().replaceAll(" ", "").equals(nome_Col)) {
						col = aux;
						break;
					}
				}
				if(col == null) {
					col = new Coligacao(nome_Col, partidos);
					coligacoes.add(col);
				}
				
				Partido p = null;
				for(Partido aux : partidos) {
					if(aux.getNome().replaceAll(" ", "").equals(dados[3].split("-")[0].replaceAll(" ", ""))) {
						p = aux;
						break;
					}
				}
				
				Candidato candidato = new Candidato(nome, numero, votos, p, eleito);
				candidatos.add(candidato);
				p.addCandidato(candidato);
			}
			
			//Arquivo de entrada sendo fechado
			arq.close();
			
			//Ordenaçao dos candidatos, partidos e coligacoes
			Collections.sort(candidatos);
			Collections.sort(partidos);
			Collections.sort(coligacoes);
			
			//Strings de saida
			String eleitos, mais_votados, quase_eleitos, eleitos_prop, colig, part;
			eleitos = "Vereadores eleitos:\n";
			mais_votados = "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas\n";
			quase_eleitos = "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:(com sua posição no ranking de mais votados)\n";
			eleitos_prop = "Eleitos, que se beneficiaram do sistema proporcional:(com sua posição no ranking de mais votados)\n";
			colig = "Votação (nominal) das coligações e número de candidatos eleitos:\n";
			part = "Votação (nominal) dos partidos e número de candidatos eleitos:\n";
			
			//Alteração de todas as strings diretamente relacionadas com os candidatos
			int aux_eleitos = 1, aux_mVotados = 1, aux_quase = 1, aux_prop = 1;
			//Como o print abaixo já diz são os vereadores eleitos
			for(Candidato x : candidatos) {
				if(x.isEleito()) {
					eleitos += aux_eleitos++ + " - " + x + '\n';
				}
				if(candidatos.indexOf(x) < vagas) {
					mais_votados += aux_mVotados++ + " - "+ x + '\n';
				}
				if(candidatos.indexOf(x) < vagas && !x.isEleito()) {
					quase_eleitos += aux_quase + " - "+ x + '\n';
				}
				aux_quase++;
				if(candidatos.indexOf(x) > vagas && x.isEleito()) {
					eleitos_prop += aux_prop + " - "+ x + '\n';
				}
				aux_prop++;
			}
			
			//Alteração da string com os dados das coligações
			int aux = 1;
			for(Coligacao x : coligacoes) {
				colig += aux++ + " - " + x + "\n";
			}
			
			//Alteração da string com os dados dos partidos
			aux = 1;
			for(Partido x : partidos) {
				part += aux++ + " - " + x + "\n";
			}
			
			//Saida
			System.out.println("Numero de vagas: "+ vagas);
			System.out.println();
			System.out.println(eleitos);
			System.out.println(mais_votados);
			System.out.println(quase_eleitos);
			System.out.println(eleitos_prop);
			System.out.println(colig);
			System.out.println(part);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
