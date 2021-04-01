package ro.axonsoft.internship21.domain.entity.pay;

import ro.axonsoft.internship21.domain.entity.cnp.CnpParts;
import ro.axonsoft.internship21.domain.entity.cnp.Judet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class PayMetricsImpl implements PayMetrics, java.io.Serializable{
    Set<PayError> errors = new HashSet<>();
    List<AbstractMap.SimpleEntry<CnpParts, Double>> payments = new ArrayList<>();

    @Override
    public Integer foreigners() {
        return (int) payments.stream()
                .filter(e -> e.getKey().foreigner())
                .map(AbstractMap.SimpleEntry::getKey)
                .distinct()
                .count();
    }

    @Override
    public Integer paymentsByMinors() {
        return (int) payments.stream()
                .filter(e ->{
                    LocalDate currentDate, birthDate;
                    currentDate = LocalDate.now();
                    var userBirthDate = e.getKey().birthDate();
                    birthDate = LocalDate.of(userBirthDate.year(), userBirthDate.month(), userBirthDate.day());
                    return Period.between(birthDate, currentDate).getYears() < 18;
                })
                .count();
    }

    @Override
    public Integer bigPayments() {
        return (int) payments.stream()
                .filter(e -> e.getValue() > 5000)
                .count();
    }

    @Override
    public Integer smallPayments() {
        return (int) payments.stream()
                .filter(e -> e.getValue() <= 5000)
                .count();
    }

    @Override
    public BigDecimal averagePaymentAmount() {
        BigDecimal numberOfTransactions = new BigDecimal((long) payments.size());
        BigDecimal transactionsAmount = BigDecimal.valueOf(payments.stream()
                .map(AbstractMap.SimpleEntry::getValue)
                .reduce(0d, Double::sum));
        try {
            return transactionsAmount.divide(numberOfTransactions, RoundingMode.DOWN);
        }
        catch (ArithmeticException e){
            return BigDecimal.valueOf(0);
        }
    }

    @Override
    public BigDecimal totalAmountCapitalCity() {
        return BigDecimal.valueOf(
                payments.stream()
                .filter(e -> e.getKey().judet() == Judet.BU)
                .map(AbstractMap.SimpleEntry::getValue)
                .reduce(0d, Double::sum)
        );
    }

    @Override
    public Set<PayError> errors() {
        return errors;
    }

    @Override
    public void addPayment(CnpParts cnp, Double value) {
        var entry = new AbstractMap.SimpleEntry<>(cnp, value);
        payments.add(entry);
    }

    @Override
    public void addError(int lineIndex, int errorType) {
        var error = new PayErrorImpl(lineIndex, errorType);
        errors.add(error);
    }

    @Override
    public String toString() {
        StringBuilder errorsString = new StringBuilder();
        errors.forEach(e -> errorsString.append(e.toString()));

        return "PayMetricsImpl {" + "\n" +
                "foreigners=" + foreigners() + "\n" +
                "paymentsByMinors=" + paymentsByMinors() + "\n" +
                "bigPayments=" + bigPayments() + "\n" +
                "smallPayments=" + smallPayments() + "\n" +
                "averagePaymentAmount=" + averagePaymentAmount() + "\n" +
                "totalAmountCapitalCity=" + totalAmountCapitalCity() + "\n" +
                "errors=\n" + errorsString.toString()+ "\n" +
                '}';
    }
}
