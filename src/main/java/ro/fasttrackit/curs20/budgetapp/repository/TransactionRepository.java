package ro.fasttrackit.curs20.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs20.budgetapp.entity.Transaction;
import ro.fasttrackit.curs20.budgetapp.entity.Type;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByType(Type transactionType);

    List<Transaction> findByAmountGreatherThan(Double minAmount);

    List<Transaction> findByAmountLessThan(Double maxAmount);

    List<Transaction> findByTypeAndAmountGreatherThan(Type transactionType, Double minAmount);

    List<Transaction> findByTypeAndAmountLessThan(Type transactionType, Double maxAmount);

    List<Transaction> findByAmountGreatherThanAndAmountLessThan(Double minAmount, Double maxAmount);

    List<Transaction> findByTypeAndAmountGreatherThanAndAmountLessThan(Type transactionType, Double minAmount, Double maxAmount);

}
