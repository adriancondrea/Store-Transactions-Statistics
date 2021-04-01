package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

public class CnpPartsImpl implements CnpParts{
    private final Sex sex;
    private final Boolean foreigner;
    private final Judet judet;
    private final CalDate birthDate;
    private final Short orderNumber;

    public CnpPartsImpl(Sex sex, Boolean foreigner, Judet judet, CalDate birthDate, Short orderNumber) {
        this.sex = sex;
        this.foreigner = foreigner;
        this.judet = judet;
        this.birthDate = birthDate;
        this.orderNumber = orderNumber;
    }

    @Override
    public Sex sex() {
        return sex;
    }

    @Override
    public Boolean foreigner() {
        return foreigner;
    }

    @Override
    public Judet judet() {
        return judet;
    }

    @Override
    public CalDate birthDate() {
        return birthDate;
    }

    @Override
    public Short orderNumber() {
        return orderNumber;
    }

    @Override
    public String toString() {
        return "CnpPartsImpl{" +
                "sex=" + sex +
                ", foreigner=" + foreigner +
                ", judet=" + judet +
                ", birthDate=" + birthDate +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
