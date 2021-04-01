package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

public interface CnpValidator {
    /**
     * Validates the CNP received as parameter and returns its component parts
     *
     * @param cnp
     *            the CNP to validate
     * @throws CnpException
     *             if the CNP is not valid
     */
    CnpParts validateCnp(String cnp) throws CnpException;

}
