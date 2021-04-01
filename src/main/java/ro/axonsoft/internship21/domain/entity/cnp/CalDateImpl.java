package ro.axonsoft.internship21.domain.entity.cnp;

public class CalDateImpl implements CalDate {
    private final Short year;
    private final Byte month, day;

    public CalDateImpl(String stringDate) {
        year = Short.parseShort(stringDate.substring(0, 4));
        month = Byte.parseByte(stringDate.substring(4, 6));
        day = Byte.parseByte(stringDate.substring(6));
    }

    public CalDateImpl(Short year, Byte month, Byte day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "CalDateImpl{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public Short year() {
        return year;
    }

    @Override
    public Byte month() {
        return month;
    }

    @Override
    public Byte day() {
        return day;
    }
}
