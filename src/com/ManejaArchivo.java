package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ManejaArchivo {
	
	public static Vector<String> leeArchivo(String NombreArchivo) {

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

}
