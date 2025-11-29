package com.fiscos.atlas.infrastructure.config.db;

import com.fiscos.atlas.core.application.ports.output.PasswordEncoderPort;
import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.core.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public AdminSeeder(UserRepository userRepository, PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmail("admin@fiscos.com")) {
            System.out.println(">>> Seeder: Admin já existe, pulando.");
            return;
        }

        System.out.println(">>> Seeder: Criando usuário Admin inicial...");

        String encodedPassword = passwordEncoder.encode("Admin123");

        User admin = User.createAdmin(
                "Super Admin",
                "admin@fiscos.com",
                encodedPassword
        );

        userRepository.save(admin);
        
        System.out.println(">>> Seeder: Admin criado com sucesso! (admin@fiscos.com / Admin123)");
    }
}