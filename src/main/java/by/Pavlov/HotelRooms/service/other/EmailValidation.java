package by.Pavlov.HotelRooms.service.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private static final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    private static Pattern pattern;

    public static boolean isEmailValid(String email){

        pattern =  Pattern.compile(regex);


        Matcher matcher = pattern.matcher(email);


        return matcher.matches();

    }





}
