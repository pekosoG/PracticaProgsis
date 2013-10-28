package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ManejaArchivo {
	
	public static Vector<String> leeASM(String NombreArchivo) {

		Vector<String> Regresale= new Vector<String>();
		try {
			FileReader fr=new FileReader(NombreArchivo);
			BufferedReader br= new BufferedReader(fr);
			
			while(br.ready()) 
				Regresale.add(br.readLine());
			
		}catch(IOException ee) {
			JOptionPane.showMessageDialog(null, "Error al abrir el Arhivo "+NombreArchivo);
		}
		return Regresale;
	}
	
	public static void leeTABOP(String NombreArchivo, Vector<ResultadoTabop> Tabop) {
		
		try {
			FileReader fr=new FileReader(NombreArchivo);
			BufferedReader br= new BufferedReader(fr);
			
			while(br.ready()) {
				String linea=br.readLine();
				try {
					/****inst-tiene_Operador-direcc-codmaq-bytesCalculados-bytesPorCalcular-total*/
					String[] aux=linea.split("-");
					Tabop.add(new ResultadoTabop(aux[0],aux[1].equalsIgnoreCase("N")?false:true,aux[2],aux[3],aux[4],aux[5],Integer.valueOf(aux[6])));
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Problema al leer linea del Tabop.txt\nLinea: "+linea+"\nError: "+e.getLocalizedMessage());
				}
			}
			
		}catch(IOException ee) {
			JOptionPane.showMessageDialog(null, "Error al abrir el Arhivo "+NombreArchivo);
		}
	}

	public static void escribeLTS(Vector<LineaASM> lineasASM,String archivo) {
		try {
			File file=new File(archivo);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);

			pw.println("CodMaq\tEtiq\tInst\tOper\tDire\tByes");
			
			for(LineaASM aux:lineasASM) {
				String linea="";

				try {
					ResultadoTabop resAux=aux.getResTabop();
					String codMaq=""+resAux.getCodmaquina(),
							et=""+aux.getEtiqueta(),
							inst=""+aux.getInstruccion(),
							op=""+aux.getOperando(),
							dir=""+resAux.getDireccionamiento(),
							bytes=""+resAux.getTotalBytes();
					linea=codMaq+"\t"+et+"\t"+inst+"\t"+op+"\t"+dir+"\t"+bytes;
				}catch(Exception ee) {
					//ee.printStackTrace();
					linea="\t\t"+aux.getInstruccion()+"\t"+aux.getProblema();
				}
				pw.println(linea);	
			}
			pw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
