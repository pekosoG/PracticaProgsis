import java.util.Vector;

import com.AnalizadorLineas;
import com.LineaASM;
import com.ManejaArchivo;

public class Principal {

	public static Vector<LineaASM> lineasASM;
	
	static {
		lineasASM=new Vector<LineaASM>();
	}
	
	public static void main(String[] args) {
		lineasASM=AnalizadorLineas.procesaLineas(ManejaArchivo.leeArchivo("prueba.txt"));
		
		for(LineaASM tmp:lineasASM)
			System.out.println(tmp);
	}

}
