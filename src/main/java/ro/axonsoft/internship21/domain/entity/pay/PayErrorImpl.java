package ro.axonsoft.internship21.domain.entity.pay;

public class PayErrorImpl implements PayError{
    private final Integer line, type;

    public PayErrorImpl(Integer line, Integer type){
        this.line = line;
        this.type = type;
    }

    @Override
    public Integer line() {
        return line;
    }

    @Override
    public Integer type() {
        return type;
    }

    @Override
    public String toString() {
        return "\tline= " + line + "; type= " + type + "\n";
    }
}
