package ro.axonsoft.internship21.domain.entity.cnp;

public interface CalDate {
    /**
     *
     * @return the year of the CalDate
     */
    Short year();

    /**
     *
     * @return the month of the CalDate as byte
     */
    Byte month();

    /**
     *
     * @return the day of the CalDate as day
     */
    Byte day();
}
