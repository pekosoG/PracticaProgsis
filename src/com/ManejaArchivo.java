package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

}
