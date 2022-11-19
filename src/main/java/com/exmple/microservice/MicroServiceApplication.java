package com.exmple.microservice;

import com.exmple.microservice.entities.BankAccount;
import com.exmple.microservice.enums.AccountType;
import com.exmple.microservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class MicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {
            for (int i = 0; i < 10; i++) {
                BankAccount account = BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .balance(Math.random() * 10000)
                        .createdAt(new Date())
                        .currency(Math.random()<0.5?"MAD":"USD")
                        .type(Math.random()<0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .build();
                bankAccountRepository.save(account);
            }
        };
    }
}
