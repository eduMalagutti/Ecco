package org.example.projeto_trainee.config;

import lombok.RequiredArgsConstructor;
import org.example.projeto_trainee.entities.*;
import org.example.projeto_trainee.enums.PeriodEnum;
import org.example.projeto_trainee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Profile ("test")
@Configuration
public class IntegrationTestsSeed implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    private final MockEntities mockEntities;

    @Autowired
    private ServiceDayRepository serviceDayRepository;

    @Override
    public void run(String... args) {

        User user1 = new User();
        user1.setName("Alice James");
        user1.setEmail("alicejames@gmail.com");
        String encodedPassword1 = passwordEncoder.encode("Senha@123456");
        user1.setPassword(encodedPassword1);
        user1.setTokenVerification("verificationToken1");
        user1.setVerified(true);
        user1.setChangePasswordToken("123456");
        user1.setChangePasswordTokenExpirationDate(Instant.now().plus(1, ChronoUnit.DAYS));
        userRepository.save(user1);
        mockEntities.setMockUser1(user1);

        User user2 = new User();
        user2.setName("Zoe Brown");
        user2.setEmail("zoebrown@gmail.com");
        String encodedPassword2 = passwordEncoder.encode("Senha@123456");
        user2.setPassword(encodedPassword2);
        user2.setTokenVerification("verificationToken2");
        user2.setVerified(false);
        userRepository.save(user2);

        User user3 = new User();
        user3.setName("Thomas Jefferson");
        user3.setEmail("thomasjefferson@gmail.com");
        String encodedPassword3 = passwordEncoder.encode("Senha@123456");
        user3.setPassword(encodedPassword3);
        user3.setTokenVerification("verificationToken3");
        user3.setVerified(true);
        user3.setChangePasswordToken("123457");
        user3.setChangePasswordTokenExpirationDate(Instant.now().plus(1, ChronoUnit.DAYS));
        userRepository.save(user3);

        User user4 = new User();
        user4.setName("Serena Williams");
        user4.setEmail("serenawilliams@gmail.com");
        String encodedPassword4 = passwordEncoder.encode("Senha@123456");
        user4.setPassword(encodedPassword4);
        user4.setTokenVerification("verificationToken4");
        user4.setVerified(true);
        user4.setChangePasswordToken("456789");
        user4.setChangePasswordTokenExpirationDate(Instant.now().minus(1, ChronoUnit.DAYS));
        userRepository.save(user4);

        Category category1 = new Category();
        category1.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        category1.setName("MANUTENCAO");
        categoryRepository.save(category1);

        SubCategory subCategory1 = new SubCategory();
        subCategory1.setId(UUID.fromString("33333333-3333-3333-3333-333333333333"));
        subCategory1.setName("PINTURA");
        subCategory1.setCategory(category1);
        subCategoryRepository.save(subCategory1);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.setId(UUID.fromString("44444444-4444-4444-4444-444444444444"));
        subCategory2.setName("ELÉTRICA");
        subCategory2.setCategory(category1);
        subCategoryRepository.save(subCategory2);

        // Creating and saving ServiceEntities
        ServiceEntity service1 = new ServiceEntity();
        service1.setFixedPrice(100.0);
        service1.setMinPrice(80.0);
        service1.setMaxPrice(120.0);
        service1.setSubCategory(subCategory1);
        service1.setUser(user2);

        List<ServiceDay> serviceDays = new ArrayList<>();
        ServiceDay day1 = new ServiceDay(LocalDate.now(), PeriodEnum.Tarde, service1);
        ServiceDay day2 = new ServiceDay(LocalDate.now().plusDays(10), PeriodEnum.Manha, service1);
        serviceDays.add(day1);
        serviceDays.add(day2);
        service1.setServiceDays(serviceDays);

        serviceEntityRepository.save(service1);
        serviceDayRepository.saveAll(serviceDays);
        mockEntities.setMockServiceEntity(service1);
    }
}
