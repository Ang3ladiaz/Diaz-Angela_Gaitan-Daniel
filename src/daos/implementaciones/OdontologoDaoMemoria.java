package daos.implementaciones;

import daos.IDao;
import entidades.Odontologo;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    public static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        File log4jfile = new File("./log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        logger.info("Nuevo registro cargado: " + odontologo.getNombre() + " " + odontologo.getApellido());
        odontologos.add(odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        File log4jfile = new File("./log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        logger.debug("Listado cargado con exito");

        return odontologos;
    }
}
