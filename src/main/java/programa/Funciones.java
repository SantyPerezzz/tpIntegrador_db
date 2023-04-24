package programa;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

import fulbo.*;
import fulbo.Partido.Resultado;
import java.sql.*;

public class Funciones {

	public static Partido buscarPartido(ArrayList<Partido> partidos, Equipo team1, Equipo team2) {
		Partido aux = new Partido();
		for (Partido part : partidos) {
			if ((part.getEquipo1() == team1 || part.getEquipo2() == team1)
					&& (part.getEquipo1() == team2 || part.getEquipo2() == team2)) {
				aux = part;
			}
		}

		return aux;
	}

	public static int posEquipo(ArrayList<Equipo> equipos, String nombre) {
		int pos = -1;
		for (Equipo eq : equipos) {
			if (eq.getNombre().equals(nombre)) {
				pos = equipos.indexOf(eq);
			}
		}

		return pos;
	}

	public static int posParticipante(ArrayList<Participante> participantes, String nombre) {
		int pos = -1;
		for (Participante part : participantes) {
			if (part.getNombre().equals(nombre)) {
				pos = participantes.indexOf(part);
			}
		}

		return pos;
	}

	public static int posRonda(ArrayList<Ronda> rondas, String nro) {
		int pos = -1;
		for (Ronda r : rondas) {
			if (r.getNro().equals(nro)) {
				pos = rondas.indexOf(r);
			}
		}

		return pos;
	}

	public static int posFase(ArrayList<Fase> fases, String nom) {
		int pos = -1;
		for (Fase f : fases) {
			if (f.getNombre().equals(nom)) {
				pos = fases.indexOf(f);
			}
		}

		return pos;
	}
	

	public static Resultado resEquipo1(int resultado) {
		Resultado res = Resultado.perdio;
		if (resultado == 1) {
			res = Resultado.gano;
		} else if (resultado == 0) {
			res = Resultado.empato;
		}

		return res;
	}

	public static boolean verificarLineaResultado(String linea) {
		boolean b = false;
		String[] lineaSpliteada = linea.split(",");
		if (lineaSpliteada.length == 6) {
			if (Integer.parseInt(lineaSpliteada[2]) >= 0) {
				if (Integer.parseInt(lineaSpliteada[3]) >= 0) {
					b = true;
				}
			}
		}

		return b;
	}

	public static void leerArchivoConfiguraciones(Path configuracionesPath, ArrayList<String> configuraciones) {
		try {
			for(String linea: Files.readAllLines(configuracionesPath)) {
				configuraciones.add(linea);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void leerArchivoResultados(Path resultadosPath, ArrayList<Ronda> rondas, ArrayList<Partido> partidos,ArrayList<Equipo> equipos,ArrayList<Fase> fases) {
		try {
			for (String linea : Files.readAllLines(resultadosPath)) {
				if (verificarLineaResultado(linea)) {
					String nomFase= linea.split(",")[5];
					Fase auxFase= new Fase(nomFase);
					if(posFase(fases, nomFase) != -1) {
						auxFase=fases.get(posFase(fases,nomFase));
					}else {
						fases.add(auxFase);
					}
					String nroRonda = linea.split(",")[0];
					Ronda auxRonda = new Ronda(nroRonda);
					if (posRonda(rondas, nroRonda) != -1) {
						auxRonda = rondas.get(posRonda(rondas, nroRonda));
					} else {
						rondas.add(auxRonda);
					}
					auxFase.agregarRonda(auxRonda);
					
					String nomEq1 = linea.split(",")[1];
					String nomEq2 = linea.split(",")[4];
					Equipo aux1 = new Equipo(nomEq1, "");
					if (posEquipo(equipos, nomEq1) != -1) {
						aux1 = equipos.get(posEquipo(equipos, nomEq1));
					} else {
						equipos.add(aux1);
					}
					Equipo aux2 = new Equipo(nomEq2, "");
					if (posEquipo(equipos, nomEq2) != -1) {
						aux2 = equipos.get(posEquipo(equipos, nomEq2));
					} else {
						equipos.add(aux2);
					}
					Partido auxPart = new Partido(aux1, aux2, Integer.parseInt(linea.split(",")[2]),
							Integer.parseInt(linea.split(",")[3]));
					partidos.add(auxPart);
					auxRonda.agregarPartido(auxPart);
					
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public static void leerBaseDatosPronosticos(ArrayList<String> configuraciones,ArrayList<Participante> participantes, ArrayList<Equipo> equipos,ArrayList<Partido> partidos) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(configuraciones.get(0), configuraciones.get(1),configuraciones.get(2));
			Statement stmt = con.createStatement();
			
			int puntosPorAcierto= Integer.parseInt(configuraciones.get(3));
			int puntosExtra= Integer.parseInt(configuraciones.get(4));
			int puntosExtraFase= Integer.parseInt(configuraciones.get(5));
			
			ResultSet rs = stmt.executeQuery("select participante,equipo1,resultado,equipo2 from pronosticos");
			while (rs.next()) {
				String nomPart = rs.getString(1);
				Participante auxPart = new Participante(nomPart,puntosExtra,puntosExtraFase);
				if (posParticipante(participantes, nomPart) != -1) {
					auxPart = participantes.get(posParticipante(participantes, nomPart));
				} else {
					participantes.add(auxPart);
				}
				String nomEq1 = rs.getString(2);
				String nomEq2 = rs.getString(4);
				Equipo eqAux1 = equipos.get(posEquipo(equipos, nomEq1));
				Equipo eqAux2 = equipos.get(posEquipo(equipos, nomEq2));

				Pronostico aux = new Pronostico(buscarPartido(partidos, eqAux1, eqAux2), eqAux1,resEquipo1(rs.getInt(3)),puntosPorAcierto);

				auxPart.agregarPronostico(aux);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void mostrarParticipante(Participante p,ArrayList<Ronda> rondas,ArrayList<Fase> fases,ArrayList<Equipo> equipos) {
		System.out.println("Participante: " + p.getNombre() + "\t" + "Puntos: " + p.puntosTotales(rondas,fases,equipos) + "\t"
				+ "Pronosticos acertados: " + p.pronosticosAcertados().size());
	}

}
