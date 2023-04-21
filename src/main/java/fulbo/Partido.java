package fulbo;

public class Partido {
	
	public enum Resultado{gano,perdio,empato}
	
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	public Partido() {
		this.equipo1=new Equipo();
		this.equipo2=new Equipo();
		this.golesEquipo1=0;
		this.golesEquipo2=0;
	}

	public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	
	// Completar metodo resultado(Equipo)
	public Resultado resultado(Equipo equipo) {
		Resultado res=null;
		if(equipo==equipo1) {
			if(golesEquipo1>golesEquipo2) {
				res= Resultado.gano;
			}else if(golesEquipo1==golesEquipo2) {
				res= Resultado.empato;
			}else {
				res= Resultado.perdio;
			}
		}else if(equipo==equipo2) {
			if(golesEquipo1<golesEquipo2) {
				res= Resultado.gano;
			}else if(golesEquipo1==golesEquipo2) {
				res= Resultado.empato;
			}else {
				res= Resultado.perdio;
			}
		}
		return res;
	}
}