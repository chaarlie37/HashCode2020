import java.io.*;
import java.util.*;

public class Main {

    public static String rutaSalida;
    public static String rutaEntrada;
    public static ArrayList<Library> scannedLibraries = new ArrayList<>();

    public static ArrayList<Book> book_list = new ArrayList<>();

    public static void main(String[] args) throws Exception{

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
        TreeSet<Library> libraries_tree = new TreeSet<>();


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

        // RECORRER ARBOL =====================================================================================================

        int contador = 0;
        Library aux;

        while(contador <= days){

            for(Library l: libraries_list){
                l.calcularScore();
            }

            Collections.sort(libraries_list);

            aux = libraries_list.get(0);

            aux.setDia_inicio(contador);

            contador = aux.dia_inicio + contador;

            libraries_list.remove(0);

            libraries_tree.add(aux);

        }

        ArrayList<StringBuilder> Out = new ArrayList<>();

        Iterator<Library> it = libraries_tree.iterator();
        while(it.hasNext() ){
            Library next = it.next();
            StringBuilder buf1 = new StringBuilder();
            StringBuilder buf2 = new StringBuilder();
            contador = next.dia_inicio + next.diasSignUp;
            int j = 0;

            while(j < next.books.size() && contador < days){
                int librosPorDia = 0;
                while(j<next.books.size() && librosPorDia < next.books_per_day){
                    buf2.append(next.books.get(j) + " ");
                    j++;
                    librosPorDia++;
                }
                contador = contador + next.diasSignUp;
            }
            buf1.append(next.id + " " + j + "\n" + buf2);

            Out.add(buf1);

        }

        StringBuilder salida = new StringBuilder();
        salida.append(Out.size() + " \n");
        for(StringBuilder s: Out){
            salida.append(s + "\n");
        }

        salida(salida.toString());





        // SALIDA =====================================================================================================

        StringBuilder buffLibreria = new StringBuilder("");
        int scanned_libraries = scannedLibraries.size();
        for(Library l : scannedLibraries){
            buffLibreria.append(l.getId() + " ");
            buffLibreria.append(l.getNumLibros() + '\n');
            buffLibreria.append(l.escribirLibros());
        }

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
    public int dia_inicio;

    public Library(int b, int d, int bpd, ArrayList<Integer> books, ArrayList<Book> book_list){
        this.numLibros = b;
        this.diasSignUp = d;
        this.books_per_day = bpd;
        this.books = books;
        this.score = 0;
        this.book_list = book_list;
        this.dia_inicio = 0;
    }

    public int getDia_inicio() {
        return dia_inicio;
    }

    public void setDia_inicio(int dia_inicio) {
        this.dia_inicio = dia_inicio;
    }

    @Override
    public int compareTo(Object o) {

        Library l2 = (Library) o;

        if(this.score == l2.score){
            return 0;
        }else if(this.score < l2.score){
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

    public String escribirLibros(){
        StringBuilder s = new StringBuilder("");
        for(Integer i : books){
            s.append(i + " ");
        }
        return s.toString();
    }

    /*
    public ordenarLibros(){
        for(Integer b : books){

        }
    }
    */


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

    public int getId() {
        return id;
    }

    public int getNumLibros() {
        return numLibros;
    }


}

class Book implements Comparable{
    public boolean registrado;
    public int score;
    public int id;

    public Book(int score, int id){
        this.score = score;
        this.id = id;
        this.registrado = false;
    }



    @Override
    public int compareTo(Object o) {

        Book l2 = (Book) o;

        if(this.score == l2.score){
            return 0;
        }else if(this.score > l2.score){
            return 1;
        }else{
            return -1;
        }
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

