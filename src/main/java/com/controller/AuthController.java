package com.controller;

import com.repository.UserRepository;
import com.model.User;
import com.services.UserService;
import com.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.security.SecureRandom;
import java.util.HashMap;

import java.util.Map;

//TODO pasar mensajes de error de autenticacion al frontend
//TODO ampliaciones alimentacion, muerte por no alimentacion, , mas interrracciones
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Autowired
    public AuthController(UserRepository userRepository,JwtUtil jwtUtil,UserService userService) {
        this.userRepository = userRepository;
        this.jwtUtil=jwtUtil;
        this.userService=userService;
    }


    //para el login
    @PostMapping("/login")//indica el tipo de solicitud(Get put post delete etc
    public Map<String,Object> login(@RequestBody Map<String,String> user){
        //Mi frontend en el auth service envia los datos en formato json
        //spring lo convierte automaticamente en un Mapa
        String email=user.get("email");
        String password=user.get("password");
        User userLogged= userRepository.findByEmail(email);

        //si el usuario no es nulo(es decir si el email introducido por el usuario esta en la base de datos
        if(userLogged==null){
            throw new RuntimeException("No se encontró el email en la base de datos ");
        }

//        //De momento no podra iniciar sesion
//        //mas adelante a lo mejor pondre una pantalla para los usuario que hayan sido baneados de manera temporal
//        //y que no pueda iniciar sesion para los usuario que hayan sido baneados para siempre
//        //con esto de momento los baneos ya son permantes debido a que no actualizo e isbannaded=false
//        if (userLogged.getBanned() != null && userLogged.getBanned()) {
//            throw new IllegalArgumentException("El usuario está baneado y no puede iniciar sesión.");
//        }

        String savedEncryptedPassword=userLogged.getPassword();
        String savedSalt=userLogged.getSalt();
        String passwordEncrypted=this.userService.encryptPassword(password,userService.hexToBytes(savedSalt));//Esto es la contraseña
        //que ha introducido el usuario y luego las comparo si no son las mismas ,tiro excepcion
        if(!savedEncryptedPassword.equals(passwordEncrypted)){
            throw new IllegalArgumentException("Email o contraseña incorrectos.");
        }

        String token= jwtUtil.generateToken(userLogged.getId().toString());
        System.out.println("Token generado en el backend "+token);
        Map<String, Object> response=new HashMap<>();
        response.put("user",userLogged);
        response.put("token",token);



        return  response;

    }
    //para registrarme
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody Map<String,String> user){
        byte[] salt=generateSalt();//para generar la sal
        String email=user.get("email");
        String name=user.get("name");
        String password = user.get("password");
        User userRegist=userService.createUserWithPark(name,email,password,salt);
        Map<String, Object> response=new HashMap<>();
        String token= jwtUtil.generateToken(userRegist.getId().toString());
        response.put("user",userRegist);
        response.put("token",token);
        return response;
    }

    //este metodo lo usare para generar un salt
    //que añadire a la contraseña del usuario antes de cifrarla
    private byte[] generateSalt(){
        SecureRandom secureRandom=new SecureRandom();
        byte[] salt=new byte[16];
        secureRandom.nextBytes(salt);
        return salt;


    }




}
