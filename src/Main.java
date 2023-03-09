import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main
{


    public static void main(String[] args)
    {
        Solver ex = new Solver("00-example.txt");
        ex.solve();


        ex = new Solver("01-chilling-cat.txt");
        ex.solve();

//        Solver sv1 = new Solver("01-chilling-cat.txt");
//        ex.solve();

    }


}

