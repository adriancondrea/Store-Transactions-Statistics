package ro.axonsoft.internship21.domain.entity.pay;

public class PayErrorImpl implements PayError{
    private final Integer line, type;

    public PayErrorImpl(Integer line, Integer type){
        this.line = line;
        this.type = type;
    }

    /**
     *
     * @return the line where the error occured
     */
    @Override
    public Integer line() {
        return line;
    }

    /**
     *
     * @return the type of the error:
     * <ul>
     * <li>0 line not valid</li>
     * <li>1 CNP not valid</li>
     * <li>2 transaction amount not valid</li>
     * </ul>
     */
    @Override
    public Integer type() {
        return type;
    }

    @Override
    public String toString() {
        return "\tline= " + line + "; type= " + type + "\n";
    }
}
