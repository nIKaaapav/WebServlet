package helpers;

import java.util.Optional;

public class StringToInt {
    public static int toInt (String s){
        return Integer.parseInt(s);
    }

    public static Optional<Integer> parseInt(String s){
        try{
            int value = toInt(s);
            return Optional.of(value);
        } catch (NumberFormatException x){
            return  Optional.empty();
        }
    }

}
