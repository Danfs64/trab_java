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
					if(aux.getNome().replaceAll(" ", "").equals(dados[3].split("-")[0].replaceAll(" ", ""))) {
						p = aux;
						break;
					}
				}
				if(p == null) {
					p = new Partido(dados[3].split("-")[0]);
					partidos.add(p);
				}
				
				
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
				
				Candidato candidato = new Candidato(nome, numero, votos, p, eleito);
				candidatos.add(candidato);
				p.addCandidato(candidato);
				if(eleito) vagas++;
			}
			
			//System.out.println(partidos);
			arq.close();
			
			//Escrevi o numero de vagas
			System.out.println("Numero de vagas: "+ vagas);
			System.out.println();
			
			//Ordena�ao dos candidatos, partidos e coligacoes
			Collections.sort(candidatos);
			Collections.sort(partidos);
			Collections.sort(coligacoes);
			
			String eleitos, mais_votados, quase_eleitos, eleitos_prop, colig, part;
			eleitos = "Vereadores eleitos:\n";
			mais_votados = "Candidatos mais votados (em ordem decrescente de vota��o e respeitando n�mero de vagas\n";
			quase_eleitos = "Teriam sido eleitos se a vota��o fosse majorit�ria, e n�o foram eleitos:(com sua posi��o no ranking de mais votados)\n";
			eleitos_prop = "Eleitos, que se beneficiaram do sistema proporcional:(com sua posi��o no ranking de mais votados)\n";
			colig = "Vota��o (nominal) das coliga��es e n�mero de candidatos eleitos:\n";
			part = "Vota��o (nominal) dos partidos e n�mero de candidatos eleitos:\n";
			
			int aux_eleitos = 1, aux_mVotados = 1, aux_quase = 1, aux_prop = 1;
			//Como o print abaixo j� diz s�o os vereadores eleitos
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
			
			int aux = 1;
			for(Coligacao x : coligacoes) {
				colig += aux++ + " - " + x + "\n";
			}
			
			aux = 1;
			for(Partido x : partidos) {
				part += aux++ + " - " + x + "\n";
			}
			
			System.out.println(eleitos);
			System.out.println(mais_votados);
			System.out.println(quase_eleitos);
			System.out.println(eleitos_prop);
			System.out.println(colig);
			System.out.println(part);
			
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("DEU MERDA");
		}
	}
}
