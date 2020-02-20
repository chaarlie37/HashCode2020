import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static String rutaSalida;
    public static String rutaEntrada;

    public static ArrayList<Book> book_list = new ArrayList<>();

    public static void main(String[] args) throws Exception{

        TreeSet<Library> libraries_tree = new TreeSet<>();

        String entrada;

        StringBuilder buffer = new StringBuilder();
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
                book_list.add(new Book(Integer.valueOf(buffer.toString()), book_list.size()));
            buffer = new StringBuilder();
        }

        for(int j = i; j<linea.length(); j++){
            buffer.append(linea.charAt(j));
        }
        if(!buffer.toString().equals(""))
            book_list.add(new Book(Integer.valueOf(buffer.toString()), book_list.size()));
        buffer = new StringBuilder();


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
                l_listbooks.add(Integer.valueOf(buffer.toString()), book_list.size());
            buffer = new StringBuilder();

            System.out.println(l_listbooks);

            libraries_list.add(new Library(l_books, l_days, l_bpd, l_listbooks, book_list));
        }

        // =====================================================================================================

        int scanned_libraries;



    }

    public static void salida(String s) throws IOException {
        FileWriter file = new FileWriter(rutaSalida);
        PrintWriter print = new PrintWriter(file);
        print.print(s);
    }

}

class Library implements Comparable{

    public int id;
    public int score;
    public int diasSignUp;
    public int numLibros;
    public int books_per_day;
    public ArrayList<Integer> books;
    public ArrayList<Book> book_list;

    public Library(int b, int d, int bpd, ArrayList<Integer> books, ArrayList<Book> book_list){
        this.numLibros = b;
        this.diasSignUp = d;
        this.books_per_day = bpd;
        this.books = books;
        this.score = 0;
        this.book_list = book_list;
    }

    @Override
    public int compareTo(Object o) {

        Library l2 = (Library) o;

        if(this.score == l2.score){
            return 0;
        }else if(this.score > l2.score){
            return 1;
        }else{
            return -1;
        }
    }



    public String salida(){
        String s = id + " " + numLibros + '\n';
        StringBuilder sb = new StringBuilder(s);
        for (Integer b : books){
            sb.append(b);
        }
        return sb.toString();
    }

    public void calcularScore(){
        for(Integer i : books){
            Book b = book_list.get(i);
            if(b.isRegistrado())
                score += book_list.get(i).getScore();
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setBooks_per_day(int books_per_day) {
        this.books_per_day = books_per_day;
    }

    public void setDiasSignUp(int diasSignUp) {
        this.diasSignUp = diasSignUp;
    }

    public void setNumLibros(int numLibros) {
        this.numLibros = numLibros;
    }


}

class Book{
    public boolean registrado;
    public int score;
    public int id;

    public Book(int score, int id){
        this.score = score;
        this.id = id;
        this.registrado = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public boolean isRegistrado() {
        return registrado;
    }
}

