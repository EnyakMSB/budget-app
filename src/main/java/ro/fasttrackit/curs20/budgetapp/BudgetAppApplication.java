package ro.fasttrackit.curs20.budgetapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs20.budgetapp.entity.Transaction;
import ro.fasttrackit.curs20.budgetapp.repository.TransactionRepository;

import java.util.List;

import static ro.fasttrackit.curs20.budgetapp.entity.Type.BUY;
import static ro.fasttrackit.curs20.budgetapp.entity.Type.SELL;

@SpringBootApplication
public class BudgetAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetAppApplication.class, args);
    }

    @Bean
    CommandLineRunner atStartup(TransactionRepository repository) {
        return args -> repository.saveAll((List.of(
                new Transaction("Monitor", SELL, 799.05),
                new Transaction("Power Supply", SELL, 599.05),
                new Transaction("Video Card", BUY, 1799.05),
                new Transaction("Motherboard", SELL, 599.05),
                new Transaction("Case", SELL, 399.15),
                new Transaction("Monitor", BUY, 799.75),
                new Transaction("Case", SELL, 791.05)
                )));
    }
}
