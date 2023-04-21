package programa;

import fulbo.*;
import java.nio.file.Path;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        Path resultadosPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\resultados");
        Path configuracionesPath= Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador_db\\src\\main\\java\\programa\\configuraciones");

        ArrayList<Ronda> rondas= new ArrayList<Ronda>();
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos= new ArrayList<Equipo>();
        ArrayList<Participante> participantes= new ArrayList<Participante>();
        
        String dbUrl="",dbUser="",dbPw="";
        int puntosPorAcierto=0,puntosExtra=0;
        
        Funciones.leerArchivoConfiguraciones(configuracionesPath,dbUrl, dbUser,dbPw,puntosPorAcierto, puntosExtra);
        Funciones.leerArchivoResultados(resultadosPath,rondas,partidos,equipos);
        
       
        for(Participante p: participantes) {
        	Funciones.mostrarParticipante(p);
        }
        
       /* for(Partido p:partidos) {
        	System.out.println(p.getEquipo1().getNombre()+" vs "+p.getEquipo2().getNombre());
        }*/
        
        /*for(Ronda r: rondas) {
        	System.out.println(r.getNro()+" "+Funciones.stringVs(r.getPartidos()));
        }*/
        
        /*for(Partido p:partidos) {
        	System.out.println("Ronda: "+p.getNroRonda()+"\t"+p.getEquipo1().getNombre()+" vs "+p.getEquipo2().getNombre());
        }*/
    }
}