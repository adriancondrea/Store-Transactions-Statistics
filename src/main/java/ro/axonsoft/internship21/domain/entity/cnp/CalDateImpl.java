package ro.axonsoft.internship21.domain.entity.cnp;

public class CalDateImpl implements CalDate {
    private final Short year;
    private final Byte month, day;

    /**
     * Constructs CalDate from a string with format yyyymmdd
     * @param stringDate the string from which we construct CalDate
     */
    public CalDateImpl(String stringDate) {
        year = Short.parseShort(stringDate.substring(0, 4));
        month = Byte.parseByte(stringDate.substring(4, 6));
        day = Byte.parseByte(stringDate.substring(6));
    }

    /**
     * Constructs CalDate from arguments
     * @param year - CalDate year
     * @param month - CalDate month
     * @param day - CalDate day
     */
    public CalDateImpl(Short year, Byte month, Byte day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     *
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return "CalDateImpl{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    /**
     *
     * @return the year of CalDate
     */
    @Override
    public Short year() {
        return year;
    }

    /**
     *
     * @return the month of CalDate
     */
    @Override
    public Byte month() {
        return month;
    }

    /**
     *
     * @return the day of CalDate
     */
    @Override
    public Byte day() {
        return day;
    }
}
