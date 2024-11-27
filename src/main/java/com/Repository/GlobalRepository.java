package com.Repository;

import jakarta.persistence.*;
import com.model.User;

import java.util.List;

//He decidido hacer un repositorio global y no un repositorio por cada clase debido a que este proyecto
//no es tan grande para hacer un repositorio por cada clase
public class GlobalRepository {
    private static GlobalRepository instance;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private GlobalRepository(){
        entityManagerFactory= Persistence.createEntityManagerFactory("parque-jurasico");
        entityManager=entityManagerFactory.createEntityManager();

    }



    public static GlobalRepository getInstance(){
        if(instance==null){
            instance=new GlobalRepository();
        }
        return instance;
    }


    //como dice el nombre para encontrar usuarios por correro electronico
    public User findUserByEmail(String email){
        User user;
        try{
            String querySQL="SELECT u FROM User u WHERE u.email = :email";
            // busco el usuario por email
            Query query=entityManager.createQuery(querySQL).setParameter("email",email);
             user= (User) query.getSingleResult();


        } catch (NoResultException noResultException){
            throw new RuntimeException("No se encontró ningún usuario con el correo proporcionado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;

    }



    //metodo para registrarse, debo de hacer pruebas de como reacciona, si me  registro
    //con el mismo email más de una vez
    public void register(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    //este metodo obtiene todos los usuarios, lo uso para comprobar usuarios unicos(los que tiene distinto email)
    public List<User> getAllUsers(){
        String querySQL="SELECT u FROM User u";
        Query query=entityManager.createQuery(querySQL);
        List<User> userList=query.getResultList();
        return userList;
    }




}
