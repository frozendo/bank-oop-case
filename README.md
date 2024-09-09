# bank-oop-case
> Banking Application Case Study Using Object-Oriented Programming (OOP)

## Introduction

This case study simulates a real-world banking application to demonstrate the core principles of Object-Oriented Programming (OOP). 
Through this example, we will explore key OOP concepts such as inheritance, encapsulation, polymorphism, and composition in a practical context.

## Basic overview

To simulate a real-world banking application, we create some basic rules.

First, users must create an account by providing a _name_ and a _document_. Two types of accounts are available:
* **Individual:** account for persons
* **Enterprise:** account for companies

For an individual account, the document must contain 11 digits. 
For an enterprise accounts, 14 digits are required.

After creating an account, users can log in to the application using the document number provided during registration.

Some key differences between individual and enterprise accounts: 
1. Individual accounts receive an initial credit (as a credit card limit) of _**500.00**_, while enterprise accounts, receive a limit is _**1000.00**_.
2. Individual accounts receive a special credit of _**500.00**_, after making 5 deposits.

Both account types are limited to 5 withdrawals.

These rules simulate a functional banking application while applying OOP concepts.

## Functionalities

Once you have a valid account, there are some operations available.

### 1. Payments
Simulate different payment operations:

1. **Credit Card Payment:** a transaction that simulate a credit card, and deducts the amount from account's property **creditBalance**.
2. **Debit Card Payment:** a transaction that deducts the amount from the account's current balance. For individual accounts, this can also reduce the _special balance_.
3. **Transfer:** allow transfer funds between two accounts.

### 2. Movements
Manage money within the account:

1. **Deposit:** add money to the account. For individual accounts, after the 5th deposit, the account receives a bonus of _500.00_.
2. **Withdraw:** withdraw money from the account. It's limited to 5 withdraws, for all account types.

### 3. Current Status
View the current status of the account, including balance, credit balance, and available credit limit.

## Technical Overview 

This application is designed to demonstrate OOP concepts through a simulated real-world example.

### Constructors 

Almost all the classes have **constructors**, that guarantee the initial state of the class. 

Some constructors require parameters, while others (primarily those interacting with the user) do not.

### Access Modifiers 

We use **access modifiers** to control what a class should expose.
The main example is the _BankAccount_ class.

In this class, the most of properties and methods are private, and no other class can access them.
But we have some protected methods and properties, and this allows child classes _IndividualBankAccount_ and _EnterpriseBankAccount_ to use them.
We also have some public things, available for all objects in the application.

### Override

The _IndividualAccount_ class overrides the **updateBalance** method from BankAccount.
It does this because, for individual accounts, the debit card operation can use the **specialBalance** to execute the operation.
As this balance is available only for individual accounts, the BankAccount class doesn't need this operation.
So IndividualAccount provides his version of __updateBalance__, calling the parent class method if needed.

### Abstraction 

We have different levels of abstraction in this application.

The most high-level class is _BankAccount_.
This class represents accounts on the application, with the user's balance and credit.
BankAccount has two child classes, _IndividualAccount_, and _EnterpriseAccount_, that is the real objects manipulated on the application flow.

We also have an abstract representation of _Payment_ and _Movements_.
Payment is the base class, used in almost all cases of payment.
Movements is an interface, used as base type to create the two concrete options of account movements on the system.

### Encapsulation

All classes use this concept to hide its details.
But the _bank account_ is the one where this is more clear.
For example, is not possible for any class on the system to access the **balance** property.

For safety and control, only _BankAccount_ can change this value, and this guarantees that the user's balance doesn't change in the wrong way.
For other classes, to manipulate this property, they should use the **updateBalance** method.

### Inheritance 

We see the concept of inheritance in the classes _IndividualAccount_ and _EnterpriseAccount_.
Those classes inherit the characteristics of _BankAccount_ and, in the IndividualAccount case, extend with its own needs.

There is another case of inheritance in the application, in the classes that execute deposits and withdraws.
_Movement_ is an interface that defines a contract to the classes that will represent movements on the application.
Then, _DepositMovement_ and _WithdrawMovement_ inherit from this interface and create the correct behavior for its situations.

### Composition

Composition is an alternative for inheritance, and we show this option on payment operations.
We have a class _Payment_, that interacts with BankAccount to update the account balance, depending on if the payment is a credit or debit.
Then, _TransferPayment_ and _DebitPayment_ can use Payment to execute the basic operation and only need to specify their specific behavior if needed.

### Polymorphism

The list that store the bank accounts is a good example of polymorphism.
The type of the list is _BankAccount_, but the list stores objects from _IndividualAccount_ and _EnterpriseAccount_.

Every place that accept a BankAccount can also be use with the child classes IndividualAccount and EnterpriseAccount, as the method that prints the account status.
