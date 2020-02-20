import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> salida_buena = new ArrayList<>();
    public static int types;

    public static String rutaSalida;
    public static String rutaEntrada;

    


    public static void main(String[] args) throws Exception{

        String entrada;

        int slices;
        StringBuilder buffer = new StringBuilder();
        //int types;
        ArrayList<Integer> slices_types = new ArrayList<>();
        int i;


        String linea;

        Scanner sc = new Scanner(System.in);
        entrada = sc.nextLine();

        int books;
        int libraries;
        int days;
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<Library> libraries_list = new ArrayList<>();

        switch (entrada){
            case "a": rutaEntrada = "a_example.txt"; rutaSalida = "A.txt"; break;
            case "b": rutaEntrada = "b_read_on.txt"; rutaSalida = "B.txt"; break;
            case "c": rutaEntrada = "c_incunabula.txt"; rutaSalida = "C.txt"; break;
            case "d": rutaEntrada = "d_tough_choices.txt"; rutaSalida = "D.txt"; break;
            case "e": rutaEntrada = "e_so_many_books.txt"; rutaSalida = "E.txt"; break;
            default : rutaEntrada = "f_libraries_of_the_world.txt"; rutaSalida = "F.txt"; break;
        }

        FileReader file = new FileReader(rutaEntrada);
        BufferedReader buff = new BufferedReader(file);

        // ----------------------------

        linea = buff.readLine();
        for(i = 0; linea.charAt(i) != ' '; i++){
            buffer.append(linea.charAt(i));
        }
        books = Integer.valueOf(buffer.toString());
        buffer = new StringBuilder();
        int k = i;
        for(i = k + 1; linea.charAt(i) != ' '; i++){
            buffer.append(linea.charAt(i));
        }
        libraries = Integer.valueOf(buffer.toString());
        buffer = new StringBuilder();
        for(int j = i+1; j<linea.length(); j++){
            buffer.append(linea.charAt(j));
        }
        days = Integer.valueOf(buffer.toString());
        buffer = new StringBuilder();

        System.out.println(books + " " + libraries + " " + days);

        // ----------------------------

        linea = buff.readLine();

        i = 0;
        for(int j = 0; j<books-1; j++){
            while(linea.charAt(i) != ' '){
                buffer.append(linea.charAt(i));
                i++;
            }
            if(linea.charAt(i) == ' ')
                i++;

            if(!buffer.toString().equals(""))
                scores.add(Integer.valueOf(buffer.toString()));
            buffer = new StringBuilder();
        }

        for(int j = i; j<linea.length(); j++){
            buffer.append(linea.charAt(j));
        }
        if(!buffer.toString().equals(""))
            scores.add(Integer.valueOf(buffer.toString()));
        buffer = new StringBuilder();

        System.out.println(scores);

        // ===================================

        for(int a = 0; a<libraries; a++){

            int l_books;
            int l_days;
            int l_bpd;
            ArrayList<Integer> l_listbooks = new ArrayList<>();

            linea = buff.readLine();
            for(i = 0; linea.charAt(i) != ' '; i++){
                buffer.append(linea.charAt(i));
            }
            l_books = Integer.valueOf(buffer.toString());
            buffer = new StringBuilder();
            k = i;
            for(i = k + 1; linea.charAt(i) != ' '; i++){
                buffer.append(linea.charAt(i));
            }
            l_days = Integer.valueOf(buffer.toString());
            buffer = new StringBuilder();
            for(int j = i+1; j<linea.length(); j++){
                buffer.append(linea.charAt(j));
            }
            l_bpd = Integer.valueOf(buffer.toString());
            buffer = new StringBuilder();

            System.out.println(l_books + " " + l_days + " " + l_bpd);




            // ----------------------------

            linea = buff.readLine();

            i = 0;
            for(int j = 0; j<l_books-1; j++){
                while(linea.charAt(i) != ' '){
                    buffer.append(linea.charAt(i));
                    i++;
                }
                if(linea.charAt(i) == ' ')
                    i++;

                if(!buffer.toString().equals(""))
                    l_listbooks.add(Integer.valueOf(buffer.toString()));
                buffer = new StringBuilder();
            }

            for(int j = i; j<linea.length(); j++){
                buffer.append(linea.charAt(j));
            }
            if(!buffer.toString().equals(""))
                l_listbooks.add(Integer.valueOf(buffer.toString()));
            buffer = new StringBuilder();

            System.out.println(l_listbooks);

            libraries_list.add(new Library(l_books, l_days, l_bpd, l_listbooks));
        }



    }

    public static void salida(String s) throws IOException {
        FileWriter file = new FileWriter(rutaSalida);
        PrintWriter print = new PrintWriter(file);
        print.print(s);
    }

}

class Library{

    public int id;
    public int score;
    public int diasSignUp;
    public int numLibros;
    public int books_per_day;
    public ArrayList<Integer> books;

    public Library(int b, int d, int bpd, ArrayList<Integer> books){
        this.numLibros = b;
        this.diasSignUp = d;
        this.books_per_day = bpd;
        this.books = books;
    }

}


