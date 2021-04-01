package ro.axonsoft.internship21.domain.entity.cnp;

import ro.axonsoft.internship21.domain.CnpException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

final class CNPUtils {
    private CNPUtils(){
    }

    public static boolean isValidCNP(String cnp) {
        if (cnp != null) {
            cnp = initializeCNP(cnp);
        } else {
            return false;
        }
        return cnp.length() == 13 && cnp.matches("\\d+") && controlValidation(cnp) && structureValidation(cnp);
    }

    private static boolean structureValidation(String cnp) {
        String yymmdd = cnp.substring(1, 7);
        String county = cnp.substring(7, 9);
        String registerNumber = cnp.substring(9, 12);
        return cnp.charAt(0) != '0' && birthdateValidation(yymmdd) && countyValidation(county) && registerNumberValidation(registerNumber);
    }

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

    private static boolean countyValidation(String county) {
        int countyIndex = Integer.parseInt(county);
        return (countyIndex >= 1 && countyIndex <= 46) || countyIndex == 51 || countyIndex == 52;
    }

    private static boolean registerNumberValidation(String registerNumber) {
        return !registerNumber.equals("000");
    }

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

    public static String initializeCNP(String cnp) {
        return cnp.trim();
    }

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

    public static boolean isForeign(String cnp) {
        char citizenshipParameter = cnp.charAt(0);
        return citizenshipParameter == '7' || citizenshipParameter == '8' || citizenshipParameter == '9';
    }

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

    public static Short initializeOrderNumber(String cnp) {
        return Short.parseShort(cnp.substring(9, 12));
    }
}
