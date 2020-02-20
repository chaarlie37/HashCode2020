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

        switch (entrada){
            case "a": rutaEntrada = "a_example.in"; rutaSalida = "A.txt"; break;
            case "b": rutaEntrada = "b_example.in"; rutaSalida = "B.txt"; break;
            case "c": rutaEntrada = "c_example.in"; rutaSalida = "C.txt"; break;
            case "d": rutaEntrada = "d_example.in"; rutaSalida = "D.txt"; break;
            case "e": rutaEntrada = "e_example.in"; rutaSalida = "E.txt"; break;
            default : rutaEntrada = "a_example.in"; rutaSalida = "A.txt"; break;
        }

        FileReader file = new FileReader(rutaEntrada);
        BufferedReader buff = new BufferedReader(file);

        // ----------------------------

        linea = buff.readLine();
        for(i = 0; linea.charAt(i) != ' '; i++){
            buffer.append(linea.charAt(i));
        }
        slices = Integer.valueOf(buffer.toString());
        buffer = new StringBuilder();
        for(int j = i+1; j<linea.length(); j++){
            buffer.append(linea.charAt(j));
        }
        types = Integer.valueOf(buffer.toString());
        buffer = new StringBuilder();

        // ----------------------------

        linea = buff.readLine();

        i = 0;
        for(int j = 0; j<types-1; j++){
            while(linea.charAt(i) != ' '){
                buffer.append(linea.charAt(i));
                i++;
            }
            if(linea.charAt(i) == ' ')
                i++;

            if(!buffer.toString().equals(""))
                slices_types.add(Integer.valueOf(buffer.toString()));
            buffer = new StringBuilder();
        }

        for(int j = i; j<linea.length(); j++){
            buffer.append(linea.charAt(j));
        }
        if(!buffer.toString().equals(""))
            slices_types.add(Integer.valueOf(buffer.toString()));
        buffer = new StringBuilder();

    }

    public static void salida(String s) throws IOException {
        FileWriter file = new FileWriter(rutaSalida);
        PrintWriter print = new PrintWriter(file);
        print.print(s);
    }

}


