import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment {



    public static void main(String[] args) {

        System.out.println("Choose from the following menu");
        System.out.println("D, R, or I");
        String choice = getInput("please choose a letter.");
        Choice month=null;
        for (Choice i : Choice.values()) {
            if (i.name().equals(choice.toUpperCase())) {
                month = Choice.valueOf(i.name());
                break;
            }
        }
        if (month==null) month = Choice.NONE_SELECTED;

        switch(month){
            case D:
                System.out.println("You get a Democratic Donkey");
                break;
            case R:
                System.out.println("You get a Republican Elephant");
                break;
            case I:
                System.out.println("You get an Independent Person");
                break;
            default:
                System.out.println("You get a unicorn");
                break;
        }
    }

    public enum Choice{
        D, R, I, NONE_SELECTED
    }

    private static String getInput(String prompt) throws IOException
    {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println(prompt);
        System.out.flush();
        try{
            return stdin.readLine();
        } catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }


}
