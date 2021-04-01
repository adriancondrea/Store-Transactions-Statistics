package ro.axonsoft.internship21.domain.entity.pay;

import ro.axonsoft.internship21.domain.entity.cnp.CnpParts;

import java.math.BigDecimal;
import java.util.Set;

public interface PayMetrics {
    /**
     * Number of foreign citizens that made transactions.
     */
    Integer foreigners();

    /**
     * Number of transactions made by minors.
     */
    Integer paymentsByMinors();

    /**
     * Number of transactions over 5000RON.
     */
    Integer bigPayments();

    /**
     * Number of transactions under or equal to 5000RON
     */
    Integer smallPayments();

    /**
     * The average of all transaction. Value has at most 2 decimals.
     */
    BigDecimal averagePaymentAmount();

    /**
     * The total amount of transactions made by romanian citizens born in Bucharest
     */
    BigDecimal totalAmountCapitalCity();

    /**
     * Processing errors
     */
    Set<PayError> errors();

    /**
     * Adds a payment to the list of payments
     */
    void addPayment(CnpParts cnp, Double value);

    /**
     * Adds an error to the list of errors
     */
    void addError(int lineIndex, int errorType);
}
