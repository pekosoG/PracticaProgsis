package com;

import java.util.Vector;

import javax.swing.JOptionPane;

public class AnalizadorLineas {
	
	public static boolean despuesEnd=false,huboEnd=false;
	public static Vector<ResultadoTabop> tabop;//=new Vector<ResultadoTabop>();
	
	static {
		tabop=new Vector<ResultadoTabop>();
		ManejaArchivo.leeTABOP("Tabop.txt", tabop);
	}
	
	public static Vector<LineaASM> procesaLineas(Vector<String> lineas){
		Vector<LineaASM> resultado=new Vector<LineaASM>();
		
		for(String tempLinea:lineas) {
			if(huboEnd)
				despuesEnd=true;
			if(tempLinea.charAt(0)==';')
				resultado.add(new Comentario(tempLinea));
			else
				resultado.add(analizaLinea(tempLinea));
			if(resultado.lastElement()==null) 
				resultado.remove(resultado.size()-1);
			else
				checaResultado(resultado.elementAt(resultado.size()-1));
		}
		//System.out.println("Lineas: "+lineas.size()+" - Res: "+resultado.size());
		if(despuesEnd)
			System.out.println("Warning: Existen mas lineas despues del END");
		
		LineaASM aux= new LineaASM();
		aux.setInstruccion("END");
		resultado.add(aux);
		return resultado;
	}

	private static LineaASM analizaLinea(String tempLinea) {
		String[]tokens=null;
		LineaASM aux=new LineaASM();
		try {
			if(tempLinea.trim().equalsIgnoreCase("end")) {
				huboEnd=true;
				return null;				
			}
			if(tempLinea.replaceAll("\\s+","").length()<=0)
				return null;
			
			//Ahora vamos a analizar la linea
			tempLinea=tempLinea.replaceAll("\\s+"," ");
			if(tempLinea.charAt(0)!=' ') { //Primero si tienen etiqueta
				/*Limpiamos*/
				tempLinea=tempLinea.trim();
				tokens=tempLinea.split("\\s+");
				if(tokens.length==0)
					return null;
				
				chekaEtiqueta(tokens[0],aux);
				try {
					chekaCodOp(tokens[1],aux);
					aux.setOperando("");
					if(tokens.length>2)
						aux.setOperando(tokens[2]);
					if(tokens.length>=4)
						aux.setProblema("Se encontraron mas elementos despues del operando");
				}catch(IndexOutOfBoundsException ee) {
					aux.setOperando(tokens[1]);
				}				
			}else {//Ahora si no tienen etiqueta
				/*Limpiamos*/
				tempLinea=tempLinea.trim();
				tokens=tempLinea.split("\\s+");				
				chekaCodOp(tokens[0],aux);
				aux.setOperando("");
				if(tokens.length>1)
					aux.setOperando(tokens[1]);
				if(tokens.length>=3)
					aux.setProblema("Se encontraron mas elementos despues del operando");
			}
			
		}catch(IndexOutOfBoundsException ee) {
			JOptionPane.showMessageDialog(null, "Hubo un problema al separar la linea ''"+tempLinea
					+"''\nNo tenia todos los elementos esperados");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problema desconocido en la linea ''"+tempLinea
					+"''\n"+e.getLocalizedMessage());
		}
		return aux;
	}

	private static void chekaCodOp(String codOp, LineaASM aux) {
		if(codOp.length()<=5 && codOp.matches("^[a-zA-Z]+(([0-9]*[a-zA-Z]*)*[\\.]?([0-9]*[a-zA-Z]*)*)"))
			aux.setInstruccion(codOp);
		else
			aux.setProblema("CodOp Invalido: "+codOp+"; ");
	}

	private static void chekaEtiqueta(String etiqueta, LineaASM aux) {
		if(etiqueta.length()<=8 && etiqueta.matches("^[a-zA-Z]+([0-9]*[_]*[a-zA-Z]*)*"))
			aux.setEtiqueta(etiqueta);
		else
			aux.setProblema("Etiqueta Invalida "+etiqueta+"; ");	
	}
	
	private static void checaResultado(LineaASM elementAt) {
		try {
			for(ResultadoTabop resAux:tabop) {
				if(elementAt instanceof Comentario)
					return;
				if(elementAt.getInstruccion().equalsIgnoreCase(resAux.getInstrucc())) {
					if((resAux.isOperando() && elementAt.getOperando().length()>0) || (!resAux.isOperando() && elementAt.getOperando().length()<=0)){
						if(idenDir(elementAt,resAux)) { //aqui llamamos la rutina para ver que onda...
							//elementAt.setResult(resAux.toString());
							elementAt.setProblema("");
							return;
						}
						if(elementAt.getProblema().length()<=0)
							elementAt.setProblema("Error! No sirve tu linea feaaaaaa");
					}else {
						elementAt.setProblema("");
						elementAt.setProblema((elementAt.getResult().length()<=0)?"Error con el operando, Acepta Operando: "+resAux.isOperando()+", tiene operando: "+((elementAt.getOperando().length()>0)?elementAt.getOperando():"No"):"");
					}
				}else if(!elementAt.getProblema().contains("error 404 CodOP not Found") && elementAt.getProblema().length()<=0)
					elementAt.setProblema((elementAt.getResult().length()<=0)?"error 404 CodOP not Found":"");
			}
		}catch(Exception e) {
			if(e.getMessage()!=null)
				JOptionPane.showMessageDialog(null, "Error desconocido "+e.getMessage());
		}
	}

	//Aqui es donde vamos a ver si la linea ASM concuerda con el leido del Tabop...
	private static boolean idenDir(LineaASM elementAt, ResultadoTabop resAux) {
		boolean result=false;
		//Vamos a comparar los Operandos a ver si es cierto... vas a tener que programar un REGEX o algo
		//para cada tipo de direccionamiento... ¿será la mejor idea?
		
		
		return result;
	}

}
