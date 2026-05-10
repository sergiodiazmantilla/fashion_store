package dao.interfaces;

import java.util.List;

public interface Crud<T> {

    void registrar(T obj);

    List<T> listar();

    T buscarPorId(int id);

    void actualizar(T obj);

    void eliminar(int id);
}