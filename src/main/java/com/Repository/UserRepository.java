package com.Repository;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//he tenido errores a la hora de obtener datos de la base de datos
//se visualizaba corrrectamente en la el backend pero pasaba objetos vacios en el fronted
//se arreglaba simpelemente usando getter y setter
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
