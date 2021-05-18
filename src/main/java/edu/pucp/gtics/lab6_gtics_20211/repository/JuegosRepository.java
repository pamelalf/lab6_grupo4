package edu.pucp.gtics.lab6_gtics_20211.repository;

import edu.pucp.gtics.lab6_gtics_20211.entity.Juegos;
import edu.pucp.gtics.lab6_gtics_20211.entity.JuegosUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface JuegosRepository extends JpaRepository<Juegos,Integer> {
    @Query(value = "select j.nombre, j.descripcion, g.nombre as genero, j.image \n" +
            "            FROM juegosxusuario jxu \n" +
            "             inner join juegos j on (jxu.idjuego = j.idjuego)\n" +
            "            inner join usuarios u on (jxu.idusuario = u.idusuario) \n" +
            "            inner join generos g on (g.idgenero = j.idgenero)\n" +
            "            where u.idusuario=?1 order by  j.nombre desc;",nativeQuery = true)
    List<JuegosUserDto> obtenerJuegosPorUser(int idusuario);
}
