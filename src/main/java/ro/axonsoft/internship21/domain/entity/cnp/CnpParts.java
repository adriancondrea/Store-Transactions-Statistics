package ro.axonsoft.internship21.domain.entity.cnp;

public interface CnpParts {
    /**
     * @return the sex determined according to the first CNP digit
     */
    Sex sex();

    /**
     * Is the person identified by the CNP a foreign citizen?
     *
     * @return {@code true} if foreign, {@code false} otherwise
     */
    Boolean foreigner();

    /**
     * @return the county given by the CNP as a two-letter abbreviation (ex: BU - Bucharest, SV - Suceava)
     */
    Judet judet();

    /**
     * @return the birth date as {@code CalDate}
     */
    CalDate birthDate();

    /**
     * @return the orderNumber
     */
    Short orderNumber();
}
