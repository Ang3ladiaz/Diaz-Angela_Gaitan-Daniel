package servicios;

import daos.implementaciones.OdontologoDaoH2;
import entidades.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OdontologoServiceTest {
    @Test
    public void TestListarTodos() {
        List<Odontologo> odontologosEsperados = new ArrayList<>();
        List<Odontologo> odontologosH2 = new ArrayList<>();
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoIDao(odontologoDaoH2);

        Odontologo o1 = new Odontologo("0001", "Rolando", "Garcia");
        Odontologo o2 = new Odontologo("0D25", "Catalina", "Suarez");
        Odontologo o3 = new Odontologo("T258", "Julio", "Estanco");

        odontologoService.guardar(o1);
        odontologoService.guardar(o2);
        odontologoService.guardar(o3);

        odontologosH2 = odontologoService.listarTodos();

        o1.setId(1L);
        o2.setId(2L);
        o3.setId(3L);

        odontologosEsperados.add(o1);
        odontologosEsperados.add(o2);
        odontologosEsperados.add(o3);

        for (int i=0; i < odontologosEsperados.size(); i++) {
            Assertions.assertEquals(odontologosEsperados.get(i).toString(), odontologosH2.get(i).toString());
        }
    }
}
