package com.src.model;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioSecurity {

    // Gera um hash para a senha
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12); // Gere um salt aleatório
        return BCrypt.hashpw(password, salt); // Crie o hash com a senha e o salt
    }

    // Verifica se a senha fornecida corresponde ao hash
    public static boolean verifyPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }

    public static void main(String[] args) {
        String originalPassword = "senhaSegura123";
        
        // Gere um hash para a senha original
        String hashedPassword = hashPassword(originalPassword);

        // Verifique se a senha fornecida corresponde ao hash
        String candidatePassword = "senhaIncorreta";
        if (verifyPassword(candidatePassword, hashedPassword)) {
            System.out.println("Senha correta.");
        } else {
            System.out.println("Senha incorreta.");
        }
    }
    
}
