package fulbo;

import fulbo.Partido.Resultado;
import programa.Configuracion;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private Resultado resultado;
	
	public Pronostico() {
		this.partido= new Partido();
		this.equipo= new Equipo();
		this.resultado= null;
	}

	public Pronostico(Partido partido, Equipo equipo, Resultado resultado) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	// Completar metodo puntos()
	public int puntos() {
		int p=0;
		
		if(partido.resultado(equipo)==resultado) {
			p=Configuracion.getPuntosPorAcierto();
		}
		
		return p;
	}
}
