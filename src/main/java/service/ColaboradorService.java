package service;

import dto.ColaboradorDTO;
import java.util.List;

public interface ColaboradorService {

    List<ColaboradorDTO> listColaborador();

    ColaboradorDTO buscarColaboradorPorId(int idColaborador);

    boolean insertarColaborador(ColaboradorDTO trabajadorDto);

    boolean actualizarColaborador(ColaboradorDTO trabajadorDto);
}
