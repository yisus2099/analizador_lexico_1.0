package analizadortokens;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EQUIPO
 */
public class AnalizadorTokens {

    /**
     * Utilizamos la biblioteca tokenaizer para separar en tokens
     */
    public static void main(String[] args) {
        
        //Contador para saber cuantos tokens tenemos
        int numTokens = 0;
        
         Scanner sc = new Scanner(System.in);
        //Temporal
        String palabra = sc.nextLine();
        
        //Palabras reservadas de inicio
        String[] palabrasRevInicio = {"Alemania", "Mexico", "Argentina",  "Brasil"};

        //Inicianos nuestra variable  StringTokenize donde le damos una cadena
        //Y le damos como parametro que cada que vea un espacio tomo como token lo anterior
        StringTokenizer particiones = new StringTokenizer(palabra, " ");

        //Revisar si es cadena
        Pattern cadenaPat = Pattern.compile("^\"(.*)\"$");
        Matcher cadenaMat;
            
        
        //Revisar si es numero
        Pattern numPat = Pattern.compile("^[0-9]+$");
        Matcher numMat;

        //Revisar si es variable
        Pattern letraPat = Pattern.compile("^[a-zA-Z_-][a-zA-Z0-9_-]*$");
        Matcher letraMat;

        //Revisar si es booleano
        Pattern boolPat = Pattern.compile("^(true|false)$");
        Matcher boolMat;
        
        //Revisar la esrucutra de if
        Pattern conPat = Pattern.compile("^[a-zA-Z0-9<>=! ]+$");
        Matcher conMat;
        
        //Buscamos primero que la palabra pertenezca al alfabeto
        int posicionCaracterInvalido = primerCaracterInvalido(palabra);
        
        if (posicionCaracterInvalido != -5) {
        char caracterInvalido = palabra.charAt(posicionCaracterInvalido);
        System.out.println("En la posicion " + posicionCaracterInvalido  + " el caracter " + caracterInvalido + " no se encuentra en el alfabeto");
         } else {
            
            //Se verifica si esta dentro del alfabeto
            System.out.println("La palabra cumple con el alfabeto");
            
        //Creamos un ciclo para saber cuantos tokens tenemos y lo guardamos en un contador
        while (particiones.hasMoreElements()) {
            particiones.nextElement();
            numTokens++;
        }
        //Creamos el arreglo con los tokens dependiendo cuantos tokens tengamos
        String[] elementos = new String[numTokens];

        // Vuelve a inicializar el StringTokenizer para recorrer desde el principio
        particiones = new StringTokenizer(palabra, " ");

        //Llenamos el arreglo con los tokens 
        for (int i = 0; i < numTokens; i++) {
            elementos[i] = particiones.nextElement().toString();
        }

        //si el token es una de las palabras reservadas entonces sigue 
        if ((elementos[0].equals(palabrasRevInicio[0]) || elementos[0].equals(palabrasRevInicio[1]) || elementos[0].equals(palabrasRevInicio[2])) && elementos[numTokens - 1].equals("Francia")) {

            //Imprimir
          if (elementos[0].equals(palabrasRevInicio[0])) {

                //Checa si inicia con comillas
                cadenaMat = cadenaPat.matcher(elementos[1]);

                //Checa si es numero
                numMat = numPat.matcher(elementos[1]);

                //Checa si es booleano
                boolMat = boolPat.matcher(elementos[1]);

                //Checa si es variable
                letraMat = letraPat.matcher(elementos[1]);

                //Si es cadena
                if (elementos[1].equals("\"")) {
                    System.out.println("Token " + " Lexema");
                    System.out.println("instruccion: " + elementos[0]);
                    System.out.println("abre comilla: " + elementos[1]);
                    System.out.println("texto: " + elementos[2]);
                    System.out.println("cierra comilla: " + elementos[3]);
                    System.out.println("cierreLinea: " + elementos[4]);
                } 
                //Si es numero
                else if (numMat.find()) {
                    System.out.println("Token " + " Lexema");
                    System.out.println("instruccion: " + elementos[0]);
                    System.out.println("digito: " + elementos[1]);
                    System.out.println("cierreLinea: " + elementos[2]);
                } //Si es booleano
                else if (boolMat.find()) {
                    System.out.println("Token " + " Lexema");
                    System.out.println("instruccion: " + elementos[0]);
                    System.out.println("booleano: " + elementos[1]);
                    System.out.println("cierreLinea: " + elementos[2]);
                } //Si es variable
                else if (letraMat.find()) {
                    System.out.println("Token " + " Lexema");
                    System.out.println("instruccion: " + elementos[0]);
                    System.out.println("variable : " + elementos[1]);
                    System.out.println("cierreLinea: " + elementos[2]);
                } else {
                    System.out.println("Error");
                }
            } 
          
          //Para declarar o iniciciar variables
          else if (elementos[0].equals(palabrasRevInicio[1])) {
                System.out.println("Token " + " Lexema");
                System.out.println("instruccion: " + elementos[0]);

                //Checa si es variable
                letraMat = letraPat.matcher(elementos[1]);

                if (letraMat.find()) {
                    System.out.println("variable: " + elementos[1]);
                    
                      //Si tiene signo igual
                        if (elementos[2].equals("=")) {
                     //Checa si es booleano
                     boolMat = boolPat.matcher(elementos[3]);

                      //Checa si es numero
                      numMat = numPat.matcher(elementos[3]);

                        System.out.println("igual a: " + elementos[2]);

                        //Si es Booleano
                        if (boolMat.find()) {
                            System.out.println("booleano: " + elementos[3]);
                            System.out.println("cierreLinea: " + elementos[4]);
                        } 
                        //Si es numero
                        else if (numMat.find()) {
                            System.out.println("digito: " + elementos[3]);
                            System.out.println("cierreLinea: " + elementos[4]);
                        } 
                        //Si es cadena
                        else if (elementos[3].equals("\"")) {
                            System.out.println("abre comilla: " + elementos[3]);
                            System.out.println("texto: " + elementos[4]);
                            System.out.println("cierra comilla: " + elementos[5]);
                            System.out.println("cierreLinea : " + elementos[6]);
                        }
                        else{
                            System.out.println("Error");
                        }
                    } 
                   //Si solo declara sin valor
                  else {
                        System.out.println("cierreLinea: " + elementos[2]);
                    }
                } else {
                    System.out.println("Error");
                }
            } 
          
          //Estructura para if
          else if (elementos[0].equals(palabrasRevInicio[2])) {
          int i=1;
          StringBuilder antesBrasil = new StringBuilder();

        
            while (i < elementos.length && !elementos[i].equals("Brasil")) {
                 antesBrasil.append(elementos[i]);
                 antesBrasil.append(" ");
                  i++;
            }
                if (i < elementos.length) {
                System.out.println("Token " + " Lexema");
                System.out.println("instruccion: " + elementos[0]);
                System.out.println("condicion: " + antesBrasil.toString().trim());
                System.out.println("instruccion: " + elementos[i]);
                
                StringBuilder estructuraIf = new StringBuilder();
                i = i+1;
                
                while (i < elementos.length && !elementos[i].equals("Francia")) {
                    estructuraIf.append(elementos[i]);
                    estructuraIf.append(" ");
                    i++;
                }

                if (i < elementos.length) {
                    System.out.println("bloque de codigo: " + estructuraIf);
                    System.out.println("cierre de linea: " + elementos[i]);
                } else {
                    System.out.println("Error");
                }
                }
            }
        }else{
            System.out.println("Error");
        }
    }
    }

    //Metodo de verificación para saber si esta en el alfabeto
    public static int primerCaracterInvalido(String palabra) {
    String[] alfabeto = {
        " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
        "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "+", "-", "*", "/", "=", ">", "<", "==", "!=",  "_",  "0", "1",
        "2", "3", "4", "5", "6", "7", "8", "9", "\""
    };
    
    // Ciclo para recorrer caracter por caracter la palabra 
    for (int i = 0; i < palabra.length(); i++) {
        char caracter = palabra.charAt(i);
        String caracterComoString = String.valueOf(caracter);
        
        //Si da true entonces no es valido
        if (!escaner(alfabeto, caracterComoString)) {
            return i;  // Devuelve la posición del primer carácter inválido
        }
    }

    return -5; // Devuelve -5 si no se encuentra ningún carácter inválido
}

//Metodo que recorre el arreglo comparando caracter por caracter
    public static boolean escaner(String[] arreglo, String elemento) {
        for (String item : arreglo) {
            if (item.equals(elemento)) {
                return true;
            }
        }
        return false;
    }
}