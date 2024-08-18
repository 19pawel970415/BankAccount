package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @Test
    void shouldNotConstructBankAccountWithBalanceSmallerThan0() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BankAccount(-19243.34))
                .withMessage("Initial balance cannot be negative")
                .withNoCause();
    }

    @ParameterizedTest
    @CsvSource({"1000.0,1000.0", "430.25,430.25", "532.11,532.11", "8594.99,8594.99", "0,0", "1.7976931348623157e+308,1.7976931348623157e+308"})
    void shouldConstructBankAccountWithBalanceEqualToOrGreaterThan0(String inputBalance, String expectedBalance) {
        account = new BankAccount(Double.valueOf(inputBalance));

        assertThat(account.getBalance())
                .isNotNull()
                .isEqualTo(Double.valueOf(expectedBalance));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDepositSmallerThat0() {
        account = new BankAccount(0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> account.deposit(-0.01))
                .withMessage("Deposit amount cannot be negative")
                .withNoCause();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1, delimiter = ';', lineSeparator = ",")
    void shouldDepositGreaterThanOrEqualTo0(String balanceBefore, String deposit, String balanceAfter) {
        account = new BankAccount(Double.valueOf(balanceBefore));

        account.deposit(Double.valueOf(deposit));

        assertEquals(Double.valueOf(balanceAfter), account.getBalance());
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldWithdrawGreaterThanOrEqualTo0AndSmallerThanOrEqualToBalance(double balanceBefore, double withdrawal, double balanceAfter) {
        account = new BankAccount(balanceBefore);

        account.withdraw(withdrawal);

        assertEquals(balanceAfter, account.getBalance());
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(0.0, 0.0, 0.0),
                Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE, 0.0),
                Arguments.of(5923926.9, 926.5, 5923000.4),
                Arguments.of(0.02, 0.01, 0.01)
        );
    }

    @ParameterizedTest
    @ArgumentsSource(Provider.class)
    void shouldWithdrawGreaterThanOrEqualTo0AndSmallerThanOrEqualToBalanceUsingClass(double balanceBefore, double withdrawal, double balanceAfter) {
        account = new BankAccount(balanceBefore);

        account.withdraw(withdrawal);

        assertEquals(balanceAfter, account.getBalance());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenWithdrawalSmallerThat0() {
        account = new BankAccount(0);

        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> account.withdraw(-0.01));



        assertThat(actualException)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Withdrawal amount cannot be negative")
                .hasNoCause();
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenWithdrawalGreaterThanBalance() {
        account = new BankAccount(100);

        assertThatThrownBy(() -> account.withdraw(100.01))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient funds")
                .hasNoCause();
    }

    @ParameterizedTest
    @ValueSource(doubles = {1000.0, 430.25, 532.11, 8594.99, 0, 0.01, Double.MAX_VALUE})
    void shouldGetBalanceGreaterThanOrEqualTo0(double balance) {
        account = new BankAccount(balance);

        double actualBalance = account.getBalance();

        assertThat(actualBalance)
                .isGreaterThanOrEqualTo(0)
                .isEqualTo(balance);
    }
}