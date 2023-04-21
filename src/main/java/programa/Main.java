package programa;

import fulbo.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        Path resultadosPath = Path
                .of("C:\\Users\\Fer\\eclipse-workspace\\Java_curso\\src\\tpIntegrador_db\\src\\main\\java\\programa\\resultados");
        Path configuracionesPath = Path
                .of("C:\\Users\\Fer\\eclipse-workspace\\Java_curso\\src\\tpIntegrador_db\\src\\main\\java\\programa\\configuraciones");

        ArrayList<Ronda> rondas = new ArrayList<Ronda>();
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        ArrayList<Participante> participantes = new ArrayList<Participante>();

        ArrayList<String> configuraciones = new ArrayList<String>();
        /* dbUrl = "", dbUser = "", dbPw = ""; */

        Integer puntosPorAcierto = 0, puntosExtra = 0;

        Funciones.leerArchivoConfiguraciones(configuracionesPath, configuraciones, puntosPorAcierto, puntosExtra);

        Funciones.leerArchivoResultados(resultadosPath, rondas, partidos, equipos);

        Funciones.leerBaseDatosPronosticos(configuraciones, participantes, equipos, partidos);

        // Imprimo los datos que deberian modificarse en la funcion
        // "Funciones.leerArchivoConfiguraciones"
        System.out.println(configuraciones.get(0));
        System.out.println(configuraciones.get(1));
        System.out.println(configuraciones.get(2));
        System.out.println(configuraciones.get(3));

        for (Participante p : participantes) {
            Funciones.mostrarParticipante(p);
        }

        // Funciones de system out para pruebas

        /*
         * for(Partido p:partidos) {
         * System.out.println(p.getEquipo1().getNombre()+" vs "+p.getEquipo2().getNombre
         * ());
         * }
         */

        /*
         * for(Ronda r: rondas) {
         * System.out.println(r.getNro()+" "+Funciones.stringVs(r.getPartidos()));
         * }
         */

        /*
         * for(Partido p:partidos) {
         * System.out.println("Ronda: "+p.getNroRonda()+"\t"+p.getEquipo1().getNombre()
         * +" vs "+p.getEquipo2().getNombre());
         * }
         */
    }
}