import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by admin on 25.09.2018.
 */
public class Class1 {
    private ArrayList<Double> x_In = new ArrayList<>();
    private ArrayList<Double> y_In = new ArrayList<>();
    private double XX_In;
    private double EPS;
    private int N;


    private int left, right;//левая и правая границы участка вычисления(индексы)

    public void InputFromFile() throws IOException {
        String path = "laba1\\src\\InputData.txt";
        char c = '\n';
        Scanner scan = new Scanner(new File(path)).useDelimiter(";");
        int i = 0;
        while (scan.hasNext()) {
            String s = scan.next();
            System.out.println(s);
            if (i == 0) {
                Scanner sc = new Scanner(s).useDelimiter(",");
                //System.out.println(s);
                while (sc.hasNext()) {
                    x_In.add(Double.parseDouble(sc.next()));
                }
                i++;
            } else if (i == 1) {
                Scanner sc = new Scanner(s).useDelimiter(",");
                while (sc.hasNext()) {
                    y_In.add(Double.parseDouble(sc.next()));
                }
                i++;
            } else if (i == 2) {
                Scanner sc = new Scanner(s).useDelimiter(",");
                XX_In = (Double.parseDouble(sc.next()));
                i++;
            }
        }
        System.out.println(y_In);
    }

    public void ErrorFromInput() throws FileNotFoundException {
        PrintWriter brWriter = new PrintWriter("OutData.txt");
        double keepValue;
        keepValue = x_In.get(0);
        for (int i = 1; i < x_In.size(); i++) {
            if (keepValue > x_In.get(i)) {
                brWriter.print(Error.IER3);
                brWriter.close();
                System.out.println(Error.IER3);
                System.exit(1);
            }
            keepValue = x_In.get(i);
        }
        if (XX_In < x_In.get(0) || XX_In > x_In.get(x_In.size() - 1)) {
            brWriter.print(Error.IER4);
            brWriter.close();
            System.out.println(Error.IER4);
            System.exit(1);
        }
    }


    /**
     * @return значение полинома Лагранжа в заданной степени для точки XX_In
     */
    private double Lagrange() {
        double sum = 0;
        for (int i = left; i <= right; i++)
            sum += y_In.get(i) * l(i);
        return sum;
    }

    /**
     * @param number - номер вычисляемого многочлена для полинома Лагранжа number >= 1 && number < N
     * @return значение многочлена степени N
     */
    private double l(int number) {
        double top = 1, bot = 1, x_k = x_In.get(number);
        for (int i = left; i <= right; i++) {
            if (i != number) {
                double x = x_In.get(i);
                top *= (XX_In - x);
                bot *= (x_k - x);
            }

        }
        return top / bot;
    }

    private void addNearestPoint() {
        if (right - left != N - 1) {

        }
    }

    Class1() {
        for (Error i : Error.values())
            System.out.println(i);
    }

    public static void main(String[] args) throws IOException {
        Class1 a = new Class1();
        a.InputFromFile();
        a.ErrorFromInput();
    }
}