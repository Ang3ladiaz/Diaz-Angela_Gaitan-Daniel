import daos.implementaciones.OdontologoDaoH2;
import daos.implementaciones.OdontologoDaoMemoria;
import entidades.Odontologo;
import servicios.OdontologoService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OdontologoDaoMemoria odontologoDaoMemoria = new OdontologoDaoMemoria();
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoIDao(odontologoDaoH2);
        List<Odontologo> odontologosH2 = new ArrayList<>();
        List<Odontologo> odontologosMemoria = new ArrayList<>();

        Odontologo o1 = new Odontologo("T416", "Camilo", "Sanchez");
        Odontologo o2 = new Odontologo("S58D", "Lucero", "Falla");
        Odontologo o3 = new Odontologo("K58E", "Leonel", "Alvarez");

        odontologoService.guardar(o1);
        odontologoService.guardar(o2);
        odontologoService.guardar(o3);

        odontologosH2 = odontologoService.listarTodos();

        System.out.println();
        System.out.println("Odontologos almacenados en H2:\n");
        for (Odontologo odontologo : odontologosH2) {
            System.out.println(odontologo.toString());
        }
        System.out.println();

        odontologoService.setOdontologoIDao(odontologoDaoMemoria);

        Odontologo o4 = new Odontologo("E895", "Jacinto", "Nuñez");
        o4.setId(1L);
        Odontologo o5 = new Odontologo("3589", "Antonio", "Ezquivel");
        o5.setId(2L);
        Odontologo o6 = new Odontologo("7T56", "Jorge", "Rodriguez");
        o6.setId(3L);

        odontologoService.guardar(o4);
        odontologoService.guardar(o5);
        odontologoService.guardar(o6);

        odontologosMemoria = odontologoService.listarTodos();

        System.out.println();
        System.out.println("Odontologos almacenados en Memoria:\n");
        for (Odontologo odontologo : odontologosMemoria) {
            System.out.println(odontologo.toString());
        }
    }
}
