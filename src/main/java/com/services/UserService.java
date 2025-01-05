package com.services;

import com.model.Park;
import com.model.User;
import com.repository.ParkRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

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

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }

    public Park getParkByUser(User user) {
        return parkRepository.findByUser(user);
    }

    public void updateParkName(Long userId, String parkName) {
        User user = getUserById(userId);
        Park park = getParkByUser(user);
        if (park != null) {
            park.setName(parkName);
            parkRepository.save(park);
        }
    }

    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = getUserById(userId);

        String savedEncryptedPassword = user.getPassword();
        String salt = user.getSalt();

        String currentPasswordEncrypted = encryptPassword(currentPassword, hexToBytes(salt));
        if (!savedEncryptedPassword.equals(currentPasswordEncrypted)) {
            throw new IllegalArgumentException("La contraseña actual es incorrecta.");
        }

        String newEncryptedPassword = encryptPassword(newPassword, hexToBytes(salt));
        user.setPassword(newEncryptedPassword);
        userRepository.save(user);
    }

    //Es porque los algoritmos de cifrado(como el sha que uso) se representa en formato hexadecimal.
    public byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }


    public void resetPark(Long userId) {
        User user = getUserById(userId);
        Park park = getParkByUser(user);
        if (park != null) {
            park.setCoin(0.0);
            park.getEnclosures().clear();
            park.getDinosaurList().clear();
            parkRepository.save(park);
        }
    }

    public void banUser(Long userId, String reason, int durationInDays) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBanned(true);
            user.setBanReason(reason);
            user.setBanExpirationDate(LocalDateTime.now().plusDays(durationInDays));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void unbanUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBanned(false);
            user.setBanReason(null);
            user.setBanExpirationDate(null);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public boolean isBanned(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getBanned() != null && user.getBanned()
                    && (user.getBanExpirationDate() == null || user.getBanExpirationDate().isAfter(LocalDateTime.now()));
        }
        return false;
    }




}
