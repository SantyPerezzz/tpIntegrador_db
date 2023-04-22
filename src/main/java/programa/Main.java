package programa;

import fulbo.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Path resultadosPath = Path.of(args[0]);
        Path configuracionesPath = Path.of(args[1]);

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