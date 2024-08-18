package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    @CsvSource({"1000.0,1000.0", "430.25,430.25" , "532.11,532.11", "8594.99,8594.99", "0,0", "1.7976931348623157e+308,1.7976931348623157e+308"})
    void shouldConstructBankAccountWithBalanceEqualToOrGreaterThan0(String inputBalance, String expectedBalance) {
        account = new BankAccount(Double.valueOf(inputBalance));

        assertThat(account.getBalance())
                .isNotNull()
                .isEqualTo(Double.valueOf(expectedBalance));
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @ParameterizedTest
    @ValueSource(doubles = {1000.0, 430.25, 532.11, 8594.99, 0, Double.MAX_VALUE})
    void shouldGetBalanceGreaterThanOrEqualTo0(double balance) {
        account = new BankAccount(balance);

        double returnedBalance = account.getBalance();

        assertTrue(returnedBalance >= 0);
    }
}