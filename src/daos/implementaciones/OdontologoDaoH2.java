package daos.implementaciones;

import daos.IDao;
import entidades.Odontologo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    public static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:./db/database;INIT=RUNSCRIPT FROM 'sql/create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static String SQL_INSERT = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private final static String SQL_SELECT = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        File log4jfile = new File("./log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();
            preparedStatement.close();
            logger.info("Nuevo registro cargado H2: " + odontologo.getNombre() + " " + odontologo.getApellido());
        } catch (Exception e) {
            logger.error("Error: ", e);
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        File log4jfile = new File("./log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Listado cargado con exito");

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);

                odontologo = new Odontologo(matricula, nombre, apellido);
                odontologo.setId(id);

                odontologos.add(odontologo);
            }

            preparedStatement.close();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }

        return odontologos;
    }

    private static Connection getConnection() throws Exception {
        Class.forName(DB_JDBC_DRIVER).newInstance();
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
