package ro.axonsoft.internship21;

import ro.axonsoft.internship21.domain.entity.cnp.CnpValidatorImpl;
import ro.axonsoft.internship21.domain.entity.cnp.Judet;
import ro.axonsoft.internship21.domain.entity.pay.PayMetrics;
import ro.axonsoft.internship21.domain.entity.pay.PayMetricsProcessor;
import ro.axonsoft.internship21.domain.entity.pay.PayMetricsProcessorImpl;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello!");
        PayMetricsProcessor payMetricsProcessor = new PayMetricsProcessorImpl();
        File initialFile = new File("test.in");
        InputStream sourceStream = new FileInputStream(initialFile);
        File finalFile = new File("test.out");
        OutputStream targetStream = new FileOutputStream(finalFile, false);
        payMetricsProcessor.process(sourceStream, targetStream);
    }
}
