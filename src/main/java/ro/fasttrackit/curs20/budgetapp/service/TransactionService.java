package ro.fasttrackit.curs20.budgetapp.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20.budgetapp.entity.Transaction;
import ro.fasttrackit.curs20.budgetapp.entity.Type;
import ro.fasttrackit.curs20.budgetapp.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll(Type transactionType, Double maxAmount, Double minAmount) {
        if (transactionType == null && minAmount == null && maxAmount == null) {
            return transactionRepository.findAll();
        } else if (transactionType != null && minAmount == null && maxAmount == null) {
            return transactionRepository.findByType(transactionType);
        } else if (transactionType == null && minAmount != null && maxAmount == null) {
            return transactionRepository.findByAmountGreatherThan(minAmount);
        } else if (transactionType == null && minAmount == null && maxAmount != null) {
            return transactionRepository.findByAmountLessThan(maxAmount);
        } else if (transactionType != null && minAmount != null && maxAmount == null) {
            return transactionRepository.findByTypeAndAmountGreatherThan(transactionType, minAmount);
        } else if (transactionType != null && minAmount == null && maxAmount != null) {
            return transactionRepository.findByTypeAndAmountLessThan(transactionType, maxAmount);
        } else if (transactionType == null && minAmount != null && maxAmount != null) {
            return transactionRepository.findByAmountGreatherThanAndAmountLessThan(minAmount, maxAmount);
        } else {
            return transactionRepository.findByTypeAndAmountGreatherThanAndAmountLessThan(transactionType, minAmount, maxAmount);
        }
    }

    public Optional<Transaction> getById(Integer id) {
        return transactionRepository.findById(id);
    }

    public Transaction create(Transaction transaction) {
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer transactionId) {
        boolean exist = transactionRepository.existsById(transactionId);
        if (!exist) {
            throw new IllegalStateException("Transaction with id: " + transactionId + "don't exist");
        }
        transactionRepository.deleteById(transactionId);
    }

    public Optional<Transaction> replaceTransaction(Integer transactionId, Transaction newTransaction) {
        Optional<Transaction> replacedTransaction = transactionRepository.findById(transactionId);
        if (replacedTransaction.isPresent()) {
            transactionRepository.deleteById(transactionId);
            newTransaction.setId(transactionId);
            transactionRepository.save(newTransaction);
        }
        return replacedTransaction;
    }

    public Transaction updateTransaction(Integer transactionId, Transaction newTransaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);

        if (!existingTransaction.isPresent()) {
            throw new IllegalStateException("Transaction not found!");
        }

        Transaction result = existingTransaction.get();
        result.setId(result.getId());
        result.setProduct(newTransaction.getProduct() != null ? newTransaction.getProduct() : result.getProduct());
        result.setType(result.getType());
        result.setAmount(newTransaction.getAmount() != 0 ? newTransaction.getAmount() : result.getAmount());

        return transactionRepository.save(result);
    }
}
