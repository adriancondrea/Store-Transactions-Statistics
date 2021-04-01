package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

import static ro.axonsoft.internship21.domain.entity.cnp.CNPUtils.*;

public class CnpValidatorImpl implements CnpValidator {
    @Override
    public CnpParts validateCnp(String cnp) throws CnpException {
        if (!isValidCNP(cnp)) {
            throw new CnpException("Invalid CNP!");
        }
        return new CnpPartsImpl(initializeSex(cnp),
                isForeign(cnp),
                initializeCounty(cnp),
                initializeDate(cnp),
                initializeOrderNumber(cnp));
    }
}
