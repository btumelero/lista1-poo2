/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bruno.191196
 */
public class Arquivos {
  ArrayList<String> dicionario = new ArrayList<>();
  
  public void adicionaPalavras() {
    Scanner scan = new Scanner(System.in);
    String linha = scan.nextLine();
    String[] partes = linha.split(" ");       //separa as palavras quando encontra um espaço
    boolean repetido = false;
    for (int i = 0; i < partes.length; i++) { //limpa a palavra
      partes[i] = limpaTexto(partes[i]);
      System.out.println(partes[i]);
      for (int j = 0; j < dicionario.size() && repetido == false; j++) { //checa se a palavra já está no dicionario
        if (partes[i].equals(dicionario.get(j))) {
          repetido = true;
        }
      }
      if (repetido == false) {
        dicionario.add(partes[i]);
      }
    }
  }
  
  public String limpaTexto(String texto) {
    texto = texto.replaceAll(",", "");
    texto = texto.replaceAll("\\.", "");
    texto = texto.replaceAll(":", "");
    texto = texto.replaceAll(";", "");
    texto = texto.replaceAll("/", "");
    texto = texto.replaceAll("-", "");
    texto = texto.replaceAll("_", "");
    return texto;
  }
  
  public void textoLimpo() {
    String texto = "texto.txt";
    String textoLimpo = "texto_limpo.txt";
    
    try {
      String linha;
      FileReader fileReader = new FileReader(texto);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      FileWriter fileWriter = new FileWriter(textoLimpo);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      while((linha = bufferedReader.readLine()) != null) {
        linha = limpaTexto(linha);
        bufferedWriter.write(linha);
      }
      bufferedWriter.close();
      bufferedReader.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Não foi possível abrir o aquivo");                
    }
    catch(IOException ex) {
      System.out.println("Erro ao escrever arquivo");
    }
  }
  
  public void dicionario() {
    String dicio = "dicionario.txt";
    try {
      String linha;
      FileReader fileReader = new FileReader(dicio);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      FileWriter fileWriter = new FileWriter(dicio, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      boolean repetido;
      for (int i = 0; i < dicionario.size(); i++) {
        repetido = false;
        while((linha = bufferedReader.readLine()) != null) {
          if (linha.contains(dicionario.get(i))) {
            repetido = true;
          }
        }
        if (repetido == false) {
          bufferedWriter.write(dicionario.get(i));
          bufferedWriter.newLine();
        }
      }
      bufferedReader.close();
      bufferedWriter.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Não foi possível abrir o aquivo");                
    }
    catch(IOException ex) {
      System.out.println("Erro ao escrever arquivo");
    }
  }
  
  public void contagem() {
    String contagem = "contagem.csv";
    String dicio = "dicionario.txt";
    String texto = "texto.txt";
    try {
      FileReader fileReaderDicionario = new FileReader(dicio);
      BufferedReader bufferedReaderDicionario = new BufferedReader(fileReaderDicionario);
      FileReader fileReaderTexto = new FileReader(texto);
      BufferedReader bufferedReaderTexto = new BufferedReader(fileReaderTexto);
      FileWriter fileWriter = new FileWriter(contagem);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      String linha;
      dicionario.clear();
      while((linha = bufferedReaderDicionario.readLine()) != null) {
        dicionario.add(linha);
      }
      for (int i = 0; i < dicionario.size(); i++) {
        bufferedWriter.write(dicionario.get(i));
        if (i != dicionario.size()-1) {
          bufferedWriter.write(";");
        }
      }
      bufferedWriter.newLine();
      int[] cont = new int[dicionario.size()];
      while((linha = bufferedReaderTexto.readLine()) != null) {
        for (int i = 0; i < dicionario.size(); i++) {
          while (linha.contains(dicionario.get(i))) {
            cont[i]++;
            linha = linha.replaceFirst(dicionario.get(i), "");
          }
        }
      }
      for (int i=0; i < cont.length; i++) {
        bufferedWriter.write(cont[i] + ";");
      }
      bufferedReaderDicionario.close();
      bufferedReaderTexto.close();
      bufferedWriter.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Não foi possível abrir o aquivo");                
    }
    catch(IOException ex) {
      System.out.println("Erro ao escrever arquivo");
    }
  }
}
