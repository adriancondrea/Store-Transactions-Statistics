package ro.axonsoft.internship21.domain.entity.pay;

import ro.axonsoft.internship21.domain.entity.cnp.CnpParts;

import java.math.BigDecimal;
import java.util.Set;

public interface PayMetrics {
    /**
     * Numarul de cetateni straini care au efectuat plati.
     */
    Integer foreigners();

    /**
     * Numarul de plati efectuate de catre minori.
     */
    Integer paymentsByMinors();

    /**
     * Numarul de plati peste pragul de 5000RON.
     */
    Integer bigPayments();

    /**
     * Numarul de plati pana in 5000RON inclusiv.
     */
    Integer smallPayments();

    /**
     * Media sumelor tuturor platilor. Valoarea are maxim doua zecimale.
     */
    BigDecimal averagePaymentAmount();

    /**
     * Totalul platilor efectuate de cetatenii romani nascuti in Bucuresti.
     */
    BigDecimal totalAmountCapitalCity();

    /**
     * Erorile de procesare.
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
