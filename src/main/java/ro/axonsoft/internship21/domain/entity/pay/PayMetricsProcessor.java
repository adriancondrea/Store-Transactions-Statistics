package ro.axonsoft.internship21.domain.entity.pay;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface PayMetricsProcessor {
    /**
     * Processes the transactions from (@code paymentsInputStream} and
     * writes the statistics in {@code metricsOutputStream}
     *
     * @param paymentsInputStream input stream of the csv file containing the transactions
     * @param metricsOutputStream output stream of the file where the object containing the statistics and errors is
     *                            written
     * @throws IOException if any I/O error appears
     */
    void process(InputStream paymentsInputStream, OutputStream metricsOutputStream) throws IOException;
}
