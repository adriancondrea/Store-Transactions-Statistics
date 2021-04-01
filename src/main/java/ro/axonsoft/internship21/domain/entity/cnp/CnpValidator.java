package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

public interface CnpValidator {
    /**
     * Valideza CNP-ul primit ca parametru si returneaza partile componente ale
     * acestuia.
     *
     * @param cnp
     *            CNP-ul de validat
     * @return partile componente ale CNP-ului
     * @throws CnpException
     *             daca CNP-ul nu este valid
     */
    CnpParts validateCnp(String cnp) throws CnpException;

}
