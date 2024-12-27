package com.services;

import com.model.Park;
import com.model.User;
import com.repository.ParkRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//este servicio lo use para crear parke que este asociado al usuario recien creado
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ParkRepository parkRepository;

    @Autowired
    public UserService(UserRepository userRepository,ParkRepository parkRepository){
        this.userRepository=userRepository;
        this.parkRepository=parkRepository;
    }

    public User createUserWithPark(String name,String email,String password,byte[] salt){
        String encriptedPassword=encryptPassword(password,salt);
        User user=new User(encriptedPassword,email,bytesToHex(salt),name);
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("¡The email is already registered, log in!");
        }
        User saveUser=userRepository.save(user);
        Park park=new Park();
        park.setCoin(0.0);
        park.setUser(saveUser);
        parkRepository.save(park);

        return saveUser;
    }

    public String encryptPassword(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
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
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
