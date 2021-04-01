package ro.axonsoft.internship21.domain.entity.pay;

public interface PayError {
    /**
     * The number of the line where the error occured.
     */
    Integer line();

    /**
     * Type of the error:
     * <ul>
     * <li>0 line not valid</li>
     * <li>1 CNP not valid</li>
     * <li>2 transaction amount not valid</li>
     * </ul>
     */
    Integer type();
}
