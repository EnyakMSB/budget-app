package ro.fasttrackit.curs20.budgetapp.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs20.budgetapp.entity.Transaction;
import ro.fasttrackit.curs20.budgetapp.entity.Type;
import ro.fasttrackit.curs20.budgetapp.service.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAll(
            @RequestParam(required = false) Type transactionType,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) Double minAmount) {
        return transactionService.getAll(transactionType,maxAmount,minAmount);
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getById(@PathVariable Integer id){
        return transactionService.getById(id);
    }

    @PostMapping
    Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }

    @PutMapping("{transactionId}")
    public Optional<Transaction> replaceTransaction(@PathVariable Integer transactionId,
                                                    @RequestBody Transaction newTransaction){
        return transactionService.replaceTransaction(transactionId,newTransaction);
    }

    @PatchMapping("{transactionId}")
    public Transaction updateTransaction(@PathVariable Integer transactionId,
                                         @RequestBody Transaction newTransaction){
        return transactionService.updateTransaction(transactionId,newTransaction);
    }

    @DeleteMapping("{transactionId}")
    void deleteTransaction(@PathVariable Integer transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
