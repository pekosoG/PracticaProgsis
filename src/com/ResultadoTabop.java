package com;

public class ResultadoTabop {
	
	/****inst-tiene_Operador-direcc-codmaq-bytesCalculados-bytesPorCalcular-total*/
	
	private String instrucc=null;
	private boolean operando=false;
	private String direccionamiento=null;
	private String codmaquina=null;
	private String bytesCalculados=null;
	private String bytesPorCalcular=null;
	private int totalBytes=0;	
	
	public ResultadoTabop(String instrucc, boolean operando,
			String direccionamiento, String codmaquina, String bytesCalculados,
			String bytesPorCalcular, int totalBytes) {
		this.instrucc = instrucc;
		this.operando = operando;
		this.direccionamiento = direccionamiento;
		this.codmaquina = codmaquina;
		this.bytesCalculados = bytesCalculados;
		this.bytesPorCalcular = bytesPorCalcular;
		this.totalBytes = totalBytes;
	}
	
	public String getInstrucc() {
		return instrucc;
	}

	public boolean isOperando() {
		return operando;
	}

	public String getDireccionamiento() {
		return direccionamiento;
	}

	public String getCodmaquina() {
		return codmaquina;
	}

	public String getBytesCalculados() {
		return bytesCalculados;
	}

	public String getBytesPorCalcular() {
		return bytesPorCalcular;
	}

	public int getTotalBytes() {
		return totalBytes;
	}

	public String toString() {
		return "CodMaq: "+codmaquina+"   Op: "+operando+" Tipo: "+direccionamiento+"B-Calcs: "+bytesCalculados+" B-X-Calc: "+bytesPorCalcular+" Total: "+totalBytes+"\n";
	}
	
}
