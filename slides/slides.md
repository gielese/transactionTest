# Transaction management

With spring

---

## Transaction

|>

![No transaction](images/NoTransaction.jpg)

Note:
No transaction, so your money will be deducted

|>

![Transaction](images/Transaction.jpg)

Note: Transaction, so nothing happens with your money

---

## Local vs Global Transactions

Note:
Local transactions is a resource specific transaction only connected to a single resource</br>
Global transaction is a transaction managed by a separate dedicated transaction manager.
Used for multiple transactions 

---

## Why Spring Transaction management?

Note:
Seperation of the demarcation and implementation
Abstraction of the implementation details
Global and local transaction have the same interface

---

## How does it work?

Note:
`` @Transactional `` </br>
`` @EnableTransactionManagement ``
TransactionManager defined

|>

```java
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(
            TransactionDefinition definition) 
                throws TransactionException;

    void commit(TransactionStatus status) 
                throws TransactionException;

    void rollback(TransactionStatus status) 
                throws TransactionException;
}
```

|>

```java
public interface TransactionStatus extends SavepointManager{
    boolean isNewTransaction();
    boolean hasSavepoint();
    void setRollbackOnly();
    boolean isRollbackOnly();
    void flush();
    boolean isCompleted();
}
```

|>

![Transactional proxy](images/tx.png)

|>

```java
public @interface Transactional {
	@AliasFor("transactionManager")
	String value() default "";
	@AliasFor("value")
	String transactionManager() default "";
	Propagation propagation() default Propagation.REQUIRED;
	Isolation isolation() default Isolation.DEFAULT;
	int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;
	boolean readOnly() default false;
	Class<? extends Throwable>[] rollbackFor() default {};
	String[] rollbackForClassName() default {};
	Class<? extends Throwable>[] noRollbackFor() default {};
	String[] noRollbackForClassName() default {};
}
```

---

## Progagation

Note:
How transaction are related to eachother 

|>

### Difference between physical and logical transactions

Note:
Physical transaction is the actual transaction that will be handled over the complete system.
Logical transaction is an