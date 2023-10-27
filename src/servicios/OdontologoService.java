package servicios;

import daos.IDao;
import entidades.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }
}
