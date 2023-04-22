package programa;

import fulbo.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Path resultadosPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador_db\\src\\main\\java\\programa\\resultados");
        Path configuracionesPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador_db\\src\\main\\java\\programa\\configuraciones");

        ArrayList<Ronda> rondas = new ArrayList<Ronda>();
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        ArrayList<Participante> participantes = new ArrayList<Participante>();
        ArrayList<String> configuraciones = new ArrayList<String>();

        Funciones.leerArchivoConfiguraciones(configuracionesPath, configuraciones);
        Funciones.leerArchivoResultados(resultadosPath, rondas, partidos, equipos);
        Funciones.leerBaseDatosPronosticos(configuraciones, participantes, equipos, partidos);
     
        for (Participante p : participantes) {
        	Funciones.mostrarParticipante(p,rondas);
        }

    }
}