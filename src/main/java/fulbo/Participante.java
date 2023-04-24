package fulbo;

import java.util.ArrayList;

public class Participante {
	private String nombre;
	private ArrayList<Pronostico> pronosticos;
	private int puntosExtra;
	private int puntosExtraFase;
	
	public Participante(String nombre,int puntosExtra, int puntosExtraFase) {
		this.nombre=nombre;
		this.pronosticos=new ArrayList<Pronostico>();
		this.puntosExtra=puntosExtra;
		this.puntosExtraFase=puntosExtraFase;
	}
	
	public Participante(String nombre, ArrayList<Pronostico> pronosticos,int puntosExtra,int puntosExtraFase) {
		this.nombre = nombre;
		this.pronosticos = pronosticos;
		this.puntosExtra=puntosExtra;
		this.puntosExtraFase=puntosExtraFase;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Pronostico> getPronosticos() {
		return pronosticos;
	}
	
	public void setPronosticos(ArrayList<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}
	
	public void agregarPronostico(Pronostico pron) {
		pronosticos.add(pron);
	}
	
	public ArrayList<Pronostico> pronosticosAcertados(){
		ArrayList<Pronostico> aciertos= new ArrayList<>();
		for(Pronostico p:pronosticos) {
			if(p.puntos()>0) {
				aciertos.add(p);
			}
		}
		
		return aciertos;
	}
	
	public boolean acertoPronosticosDeRonda(Ronda ronda) {
		boolean r = true;
		ArrayList<Partido> partidosAcertados = new ArrayList<Partido>();
		for (Pronostico p : pronosticosAcertados()) {
			partidosAcertados.add(p.getPartido());
		}
		for (Partido p : ronda.getPartidos()) {
			if (!partidosAcertados.contains(p)) {
				r = false;
			}
		}
		return r;
	}
	
	public boolean acertoPronosticosRondaEquipo(Ronda r,Equipo eq) {
		boolean ret=true;
		ArrayList<Partido> partidosAcertados = new ArrayList<Partido>();
		ArrayList<Partido> partidosEq= r.partidosEquipo(eq);
		for(Pronostico p:pronosticosAcertados()) {
			partidosAcertados.add(p.getPartido());
		}
		for(Partido p: partidosEq) {
			if(!partidosAcertados.contains(p)) {
				ret=false;
			}
		}
		
		return ret;
	}
	
	public boolean acertoPronosticosDeFaseEquipo(Fase fase, Equipo eq) {
		boolean ret=true;
		
		for(Ronda r: fase.getRondas()) {
			if(!acertoPronosticosRondaEquipo(r,eq)) {
				ret=false;
			}
		}
		
		return ret;
	}
	
	public int puntosTotales(ArrayList<Ronda> rondas,ArrayList<Fase> fases,ArrayList<Equipo> equipos) {
		int puntos=0;
		for(Pronostico p:pronosticos) {
			puntos+=p.puntos();
		}
		
		for(Ronda r:rondas) {
			if(acertoPronosticosDeRonda(r)) {
				puntos+=puntosExtra;
			}
		}
		
		for(Equipo eq:equipos) {
			for(Fase f:fases) {
				if(acertoPronosticosDeFaseEquipo(f, eq)) {
					puntos+=puntosExtraFase;
				}
			}
		}
		
		return puntos;
	}
}
