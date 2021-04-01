package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

final class CNPUtils {
    private CNPUtils(){
    }

    /**
     * Checks if the CNP provided as parameter is valid, by checking its length, checking if it's composed only of
     * digits and calls controlValidation and structureValidation on it.
     * @param cnp the cnp to check
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean isValidCNP(String cnp) {
        if (cnp != null) {
            cnp = initializeCNP(cnp);
        } else {
            return false;
        }
        return cnp.length() == 13 && cnp.matches("\\d+") && controlValidation(cnp) && structureValidation(cnp);
    }

    /**
     * Structure validation for cnp, calls birthdate validation, county validation and register number validation
     * @param cnp to check
     * @return {@code true} if valid, {@code false} otherwise
     */
    private static boolean structureValidation(String cnp) {
        String yymmdd = cnp.substring(1, 7);
        String county = cnp.substring(7, 9);
        String registerNumber = cnp.substring(9, 12);
        return cnp.charAt(0) != '0' && birthdateValidation(yymmdd) && countyValidation(county) && registerNumberValidation(registerNumber);
    }

    /**
     * Birthdate validation for cnp
     * @param yymmdd cnp birthdate digits
     * @return {@code true} if valid birthdate, {@code false} otherwise
     */
    private static boolean birthdateValidation(String yymmdd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd", Locale.getDefault());
        sdf.setLenient(false);
        try {
            sdf.parse(yymmdd);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * county validation for CNP
     * @param county digits from cnp
     * @return {@code true} if valid county, {@code false} otherwise
     */
    private static boolean countyValidation(String county) {
        int countyIndex = Integer.parseInt(county);
        return (countyIndex >= 1 && countyIndex <= 46) || countyIndex == 51 || countyIndex == 52;
    }

    /**
     * register number validation for cnp
     * @param registerNumber digits from cnp
     * @return {@code true} if valid register number, {@code false} otherwise
     */
    private static boolean registerNumberValidation(String registerNumber) {
        return !registerNumber.equals("000");
    }

    /**
     * control validation for cnp
     * @param cnp entire cnp as {@code String}
     * @return {@code true} if valid, {@code false} otherwise
     */
    private static boolean controlValidation(String cnp) {
        final String controlSequence = "279146358279";
        final int controlDivider = 11;
        int controlSum = 0;
        int controlDigit;
        for (int charIndex = 0; charIndex < controlSequence.length(); charIndex++) {
            controlSum += Character.getNumericValue(cnp.charAt(charIndex)) * Character.getNumericValue(controlSequence.charAt(charIndex));
        }
        if (controlSum % controlDivider == 10) {
            controlDigit = 1;
        } else {
            controlDigit = controlSum % 11;
        }
        return controlDigit + '0' == cnp.charAt(cnp.length() - 1);
    }

    /**
     * trims the cnp, removing whitespace from its ends
     * @param cnp as {@code String}
     * @return trimmed cnp
     */
    public static String initializeCNP(String cnp) {
        return cnp.trim();
    }

    /**
     * @param cnp cnp as {@code String}
     * @return {@code Sex} representing the sex of the citizen, U if unknown
     */
    public static Sex initializeSex(String cnp) {
        int sexPrameter = cnp.charAt(0) - '0';
        if (sexPrameter == 9) {
            return Sex.U;
        } else if (sexPrameter % 2 == 1) {
            return Sex.M;
        } else {
            return Sex.F;
        }
    }

    /**
     * @param cnp cnp as {@code String}
     * @return {@code CalDate} birthdate as it results from cnp
     * Obs: for foreign citizens, we can only determine the last two digits of their birth date
     * @throws CnpException if first digit is invalid
     */
    public static CalDate initializeDate(String cnp) {
        int yearIndex = cnp.charAt(0) - '0';
        String yymmdd = cnp.substring(1, 7);
        return switch (yearIndex) {
            case 1, 2 -> new CalDateImpl("19" + yymmdd);
            case 3, 4 -> new CalDateImpl("18" + yymmdd);
            case 5, 6 -> new CalDateImpl("20" + yymmdd);
            case 7, 8 -> new CalDateImpl("00" + yymmdd);
            default -> throw new CnpException("Invalid First CNP Digit!");
        };
    }

    /**
     * checks if the citizen is foreign
     * @param cnp {@code String} cnp
     * @return {@code true} if foreign, {@code false} otherwise
     */
    public static boolean isForeign(String cnp) {
        char citizenshipParameter = cnp.charAt(0);
        return citizenshipParameter == '7' || citizenshipParameter == '8' || citizenshipParameter == '9';
    }

    /**
     * @param cnp {@code String} cnp
     * @return {@code Judet} - county of residence
     */
    public static Judet initializeCounty(String cnp) {
        int countyIndex = Integer.parseInt(cnp.substring(7, 9));
        if(countyIndex >= 40 && countyIndex <= 46)
            return Judet.BU;
        if(countyIndex == 51)
            return Judet.CL;
        if(countyIndex == 52)
            return Judet.GR;
        else
            return Judet.values()[countyIndex - 1];
    }

    /**
     * @param cnp {@code String} cnp
     * @return {@code Short} representing the order nubmer
     */
    public static Short initializeOrderNumber(String cnp) {
        return Short.parseShort(cnp.substring(9, 12));
    }
}
