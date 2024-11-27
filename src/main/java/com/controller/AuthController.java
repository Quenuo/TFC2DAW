package com.controller;

import com.Repository.GlobalRepository;
import com.model.User;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private GlobalRepository globalRepository;

    public AuthController(){
        globalRepository=GlobalRepository.getInstance();
    }

    //para el login
    @PostMapping("/login")//indica el tipo de solicitud(Get put post delete etc
    public User login(@RequestBody Map<String,String> user){
        //Mi frontend en el auth service envia los datos en formato json
        //spring lo convierte automaticamente en un Mapa
        String email=user.get("email");
        String password=user.get("password");
        User userLogged= globalRepository.findUserByEmail(email);

        //si el usuario no es nulo(es decir si el email introducido por el usuario esta en la base de datos
        if(userLogged!=null){
            String savedEncryptedPassword=userLogged.getPassword();
            String savedSalt=userLogged.getSalt();
            String passwordEncrypted=encryptPassword(password,hexToBytes(savedSalt));//Esto es la contraseña
            //que ha introducido el usuario y luego las comparo si no son las mismas ,tiro excepcion
            if(!savedEncryptedPassword.equals(passwordEncrypted)){
                throw new IllegalArgumentException("Email o contraseña incorrectos.");
            }

        }else{
            throw new RuntimeException("No se encontró el email en la base de datos ");
        }


        return  userLogged;

    }
    //para registrarme
    @PostMapping("/register")
    public void register(@RequestBody Map<String,String> user){
        byte[] salt=generateSalt();//para generar la sal
        String email=user.get("email");
        String name=user.get("name");
        String password=user.get("password");
        String encryptedPassword=encryptPassword(password,salt);
        User userRegist=new User(encryptedPassword,email,bytesToHex(salt),name);

        List<User> userList=globalRepository.getAllUsers();
        if(userList.contains(userRegist)){
            //si ya lo contiene, entonces el email no es unico por lo que tiro excepcion
            //el criterio que he usado para que un usuario sea igual a otro es tener el mismo email

            throw new IllegalArgumentException("¡El email ya esta registrado, logueate!");
        }
        globalRepository.register(userRegist);

    }

    //este metodo lo usare para generar un salt
    //que añadire a la contraseña del usuario antes de cifrarla
    private byte[] generateSalt(){
        SecureRandom secureRandom=new SecureRandom();
        byte[] salt=new byte[16];
        secureRandom.nextBytes(salt);
        return salt;


    }

        //con esto me encargo de  cifrar la contraseña usando el algoritmo SHA-256
    private String encryptPassword(String password, byte[] salt){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] encryptedPassword = messageDigest.digest(password.getBytes());
            return bytesToHex(encryptedPassword);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    //este metodo lo vi en el tutorial de un pancho, basicamente sirve para representar el hash
    //de leible para el humano
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    //Es porque los algoritmos de cifrado(como el sha que uso) se representa en formato hexadecimal.
    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

}
