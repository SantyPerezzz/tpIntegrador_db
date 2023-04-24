package fulbo;

import java.util.ArrayList;

public class Ronda {
	private String nro;
	private ArrayList<Partido> partidos;

	public Ronda(String nro) {
		this.nro = nro;
		this.partidos = new ArrayList<>();
	}

	public Ronda(String nro, ArrayList<Partido> partidos) {
		super();
		this.nro = nro;
		this.partidos = partidos;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	public void agregarPartido(Partido part) {
		partidos.add(part);
	}
	
	public ArrayList<Partido> partidosEquipo(Equipo eq){
		ArrayList<Partido> r= new ArrayList<Partido>();
		for(Partido p: partidos) {
			if(p.getEquipo1().getNombre().equals(eq.getNombre()) || p.getEquipo2().getNombre().equals(eq.getNombre())) {
				r.add(p);
			}
		}
		return r;
	}
}
