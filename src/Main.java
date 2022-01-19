import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        Set<Character> uniqueChars = new HashSet<>();
        HashMap<Character, Double> lowRange = new HashMap<>();
        HashMap<Character, Double> highRange = new HashMap<>();
        HashMap<Character, Double> currLowRange = new HashMap<>();
        HashMap<Character, Double> currHighRange = new HashMap<>();
        HashMap<Character, Double> probs = new HashMap<>();

        for(int i=0;i<str.length();i++){
            uniqueChars.add(str.charAt(i));
        }
        for(Character c:uniqueChars){
            System.out.print("Enter probability of "+c+" : ");
            double prob = scan.nextDouble();
            probs.put(c, prob);
        }

        double intervalLow = 0.0D, intervalHigh = 0.0D;
        for(Character c:uniqueChars){
            intervalHigh = intervalHigh+probs.get(c);
            lowRange.put(c, intervalLow);
            highRange.put(c, intervalHigh);
            currLowRange.put(c, intervalLow);
            currHighRange.put(c, intervalHigh);
            intervalLow = intervalHigh;
        }

        System.out.println(probs);
        System.out.println(lowRange);
        System.out.println(highRange);

        double lower = 0.0D, higher = 1.0D;
        for(int i=0;i<str.length();i++){
            //System.out.println("lower = "+lower);
            //System.out.println("higher = "+higher);
            double range = higher-lower;
            //System.out.println("range = "+range);
            for (Character c:uniqueChars){
                //System.out.println("curr char = "+c);
                double newLow = lower+(range*lowRange.get(c));
                double newHigh = lower+(range*highRange.get(c));
                //System.out.println("new curr low = "+newLow);
               //System.out.println("new curr high = "+newHigh);
                currLowRange.put(c, newLow);
                currHighRange.put(c, newHigh);
            }
            //System.out.println("curr low = "+currLowRange);
            //System.out.println("curr high = "+currHighRange);
            lower = currLowRange.get(str.charAt(i));
            higher = currHighRange.get(str.charAt(i));
        }

        double A = currHighRange.get(str.charAt(str.length()-1));
        double B = currLowRange.get(str.charAt(str.length()-1));
        double floatingValue = (A+B)/2.0;
        System.out.println("floating code : "+floatingValue);

        String decompValue = "";
        for(int i=0;i<str.length();i++) {
            for (Character c : uniqueChars) {
                if(lowRange.get(c) <= floatingValue && floatingValue <=  highRange.get(c)){
                    //System.out.println("curr char is "+c);
                    floatingValue = (floatingValue-lowRange.get(c))/(highRange.get(c)-lowRange.get(c));
                    //System.out.println("new floating is "+floatingValue);
                    decompValue += c;
                    break;
                }
            }
        }

        System.out.println("decompression : "+decompValue);

    }
}
