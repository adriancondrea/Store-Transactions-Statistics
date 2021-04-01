package ro.axonsoft.internship21.domain.entity.pay;

import ro.axonsoft.internship21.domain.CnpException;
import ro.axonsoft.internship21.domain.entity.cnp.CnpValidator;
import ro.axonsoft.internship21.domain.entity.cnp.CnpValidatorImpl;

import java.io.*;

public class PayMetricsProcessorImpl implements PayMetricsProcessor {
    private final CnpValidator cnpValidator = new CnpValidatorImpl();
    private final PayMetrics payMetrics = new PayMetricsImpl();

    /**
     * reads the transactions from the input stream
     *
     * @param paymentsInputStream - input stream
     * @throws IOException if any I/O error appears
     */
    private void readTransactions(InputStream paymentsInputStream) throws IOException {
        int lineIndex = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(paymentsInputStream));
        while (reader.ready()) {
            String line = reader.readLine().trim();
            if (!line.isEmpty()) {
                try {
                    var arguments = line.split(";");
                    if (arguments.length != 2)
                        throw new CnpException("Invalid line!");
                    var cnp = cnpValidator.validateCnp(arguments[0]);
                    var transactionAmount = Double.parseDouble(arguments[1]);
                    payMetrics.addPayment(cnp, transactionAmount);
                } catch (CnpException e) {
                    switch (e.getMessage()) {
                        case "Invalid line!" -> payMetrics.addError(lineIndex, 0);
                        case "Invalid CNP!" -> payMetrics.addError(lineIndex, 1);
                    }
                } catch (NumberFormatException e) {
                    payMetrics.addError(lineIndex, 2);
                }
            }
            lineIndex += 1;
        }
    }

    /**
     * calls the readTransactions and writeResult methods
     *
     * @param paymentsInputStream input stream of the csv file containing the transactions
     * @param metricsOutputStream output stream of the file where the object containing the statistics and errors is
     *                            written
     * @throws IOException if any I/O error occurs
     */
    @Override
    public void process(InputStream paymentsInputStream, OutputStream metricsOutputStream) throws IOException {
        readTransactions(paymentsInputStream);
        writeResult(metricsOutputStream);
    }

    /**
     * writes the string representation of the payMetrics object to the given output stream
     *
     * @param metricsOutputStream - given output Stream
     * @throws IOException if any I/O error occurs
     */
    private void writeResult(OutputStream metricsOutputStream) throws IOException {
        byte[] bytes = payMetrics.toString().getBytes();
        byte[] endline = "\n".getBytes();
        metricsOutputStream.write(bytes);
        metricsOutputStream.write(endline);
    }
}
