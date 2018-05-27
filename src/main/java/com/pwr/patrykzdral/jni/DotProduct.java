import java.util.Scanner;

class DotProduct {
    static {
        System.loadLibrary("dotproduct"); // Load native library at runtime
        // hello.dll (Windows) or libhello.so (Unixes)
    }

    private double[] a;
    private double[] b;
    private double c;

    public double[] getA() {
        return a;
    }

    public void setA(double[] a) {
        this.a = a;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    /*
    zakładamy, że po stronie kodu natywnego wyliczony zostanie iloczyn skalarny dwóch wektorów
    */
    public native double multi01(double[] a, double[] b);

    /*
    zakładamy, że drugi atrybut będzie pobrany z obiektu przekazanego do metody natywnej
    */
    public native double multi02(double[] a);

    /*
    zakładamy, że po stronie natywnej utworzone zostanie okienko na atrybuty,
    a po ich wczytaniu i przepisaniu do a,b obliczony zostanie wynik.
    Wynik powinna wyliczać metoda Javy multi04
    (korzystająca z parametrów a,b i wpisująca wynik do c).
    */
    public native void multi03();

    private void multi04() {
        double sum = 0;
        for (int i = 0; i < getB().length; i++)
        {
            sum += getA()[i] * getB()[i];
        }

        setC(sum);
    }



    // Test Driver
    public static void main(String[] args) {
        System.out.println("Program należy uruchomić z różnymi parametrami\n"+
                "Opis parametrów wejściowych:\n" +
                "\t\tJAV - multi03() okno do wprowadzania wektorów z poziomu C++ i obliczenia z poziomu Javy\n" +
                "\t\tNAT - multi04() okno do wprowadzania wektorów z poziomu Javy i obliczenia z poziomu C++\n"+
                "\t\tNATJAV - multi02() okno do wprowadzania wektorów z poziomu Javy i obliczenia z poziomu C++(Jeden wektor pobierany przez getter)\n");

        if (args.length == 1 && args[0].equals("NAT")) {
            DotProduct d = new DotProduct();
            d.multi03();
            System.out.println("git");
            d.multi04();
            System.out.println("ILOCZYN SKALARNY WYNOSI: "+d.getC());
        }
        else if (args.length == 1 && args[0].equals("JAV")) {
            DotProduct d = new DotProduct();
            Scanner scanner = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            System.out.print("Podaj rozmiar wektorów: ");
            int size = scanner.nextInt();
            double [] vectorA = new double[size];
            double [] vectorB = new double[size];
            double num;
            System.out.print("");
            for(int i =0;i<vectorA.length;i++){
                System.out.print("Podaj "+i+" element vectora A: ");
                num=scanner.nextDouble();
                vectorA[i]=num;
            }

            for(int i =0;i<vectorB.length;i++){
                System.out.print("Podaj "+i+" element vectora B: ");
                num=scanner.nextDouble();
                vectorB[i]=num;

            }
            d.multi01(vectorA,vectorB);
            System.out.println("ILOCZYN SKALARNY WYNOSI: "+d.getC());


        }
        else if (args.length == 1 && args[0].equals("NATJAV")) {
            DotProduct d = new DotProduct();
            Scanner scanner = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            System.out.print("Podaj rozmiar wektorów: ");
            int size = scanner.nextInt();
            double [] vectorA = new double[size];
            double [] vectorB = new double[size];
            double num;
            System.out.print("");
            for(int i =0;i<vectorA.length;i++){
                System.out.print("Podaj "+i+" element vectora A: ");
                num=scanner.nextDouble();
                vectorA[i]=num;
            }

            for(int i =0;i<vectorB.length;i++){
                System.out.print("Podaj "+i+" element vectora B: ");
                num=scanner.nextDouble();
                vectorB[i]=num;

            }
            d.setB(vectorB);
            System.out.println("ILOCZYN SKALARNY WYNOSI: "+d.multi02(vectorA));


        }
        else{
            System.out.println("Nieprawidłowa ilość arguementów");
        }

    }
}