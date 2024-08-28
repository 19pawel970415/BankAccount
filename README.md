Task: Testing Validation and Business Logic in a Banking Application

Application Description:

For this task, you will prepare unit tests for a banking application that manages bank accounts. The application allows creating accounts, depositing and withdrawing funds, and checking account balances. Additionally, the application should validate various scenarios, such as insufficient funds or invalid input data. Your task is to write unit tests using knowledge of parameterized tests and exception testing.

Application Specification:

Class BankAccount:

Field balance: of type double.

Constructor:

java
Skopiuj kod
public BankAccount(double initialBalance)
Throws IllegalArgumentException if initialBalance is less than 0.
Method deposit(double amount):

Adds amount to the balance.
Throws IllegalArgumentException if amount is less than 0.
Method withdraw(double amount):

Subtracts amount from the balance.
Throws IllegalArgumentException if amount is less than 0.
Throws IllegalStateException if amount is greater than the current balance.
Method getBalance():

Returns the current balance.
Your Task:

Create a set of unit tests for the BankAccount class covering various scenarios.

Testing Requirements:

Parameterized Tests:

Positive Tests: Use parameterized tests to test different values for the deposit and withdraw methods.
Negative Tests: Use parameterized tests to test cases where amount is less than 0 for the deposit and withdraw methods.
Exception Testing:

Verify that appropriate exceptions are thrown for invalid operations.
Use different overloads of the assertThrows method to check that the method throws the expected exceptions with appropriate messages.
Utilize the AssertJ library for advanced exception checking (e.g., checking the exception message and absence of a cause).





