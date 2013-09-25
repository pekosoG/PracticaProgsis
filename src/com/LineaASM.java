package com;

public class LineaASM {
	
	private String etiqueta=null;
	private String instruccion=null;
	private String operando=null;
	private String problema="";
	
	
	
	public LineaASM(String etiqueta, String instruccion, String operando) {
		if(etiqueta.length()>1)
			this.etiqueta = etiqueta;
		if(instruccion.length()>1)
			this.instruccion = instruccion;
		if(operando.length()>1)
			this.operando = operando;
	}
	
	public LineaASM() {
		//Constructor vac�o
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public String getInstruccion() {
		return instruccion;
	}

	public String getOperando() {
		return operando;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	public void setOperando(String operando) {
		this.operando = operando;
	}
	
	public void setProblema(String problema) {
		if(problema.length()>0)
			this.problema+=problema;
	}
	
	public String toString() {
		if(problema.length()>0)
			return "Et:"+this.etiqueta+" - Inst: "+this.instruccion+" - Op: "+this.operando+" "+" Problema: "+this.problema;
		else
			return "Et:"+this.etiqueta+" - Inst: "+this.instruccion+" - Op: "+this.operando;
	}

}

class Comentario extends LineaASM{
	private String comentario;
	
	public Comentario(String cadena) {
		super();
		if(cadena.length()>0)
			this.comentario=cadena.substring(1,cadena.length());
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String toString() {
		return "Comentario: "+this.comentario;
	}
	
}