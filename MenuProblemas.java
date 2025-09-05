package Problemas;

import java.util.Scanner;

/**
 * Clase principal que contiene un menú para resolver tres problemas:
 * 1. Serie de Fibonacci recursiva
 * 2. Subset Sum (suma de subconjuntos)
 * 3. Resolución de Sudoku usando backtracking
 */
public class MenuProblemas {

    /**
     * Calcula el enésimo número de la serie de Fibonacci de forma recursiva.
     *
     * @param n posición en la serie
     * @return el valor de Fibonacci en la posición n
     */
    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Determina si existe un subconjunto dentro de un conjunto de enteros
     * que sume un valor objetivo usando recursividad.
     *
     * @param conjunto arreglo de enteros
     * @param n número de elementos considerados
     * @param objetivo valor objetivo de la suma
     * @return true si existe un subconjunto que suma al objetivo, false en caso contrario
     */
    public static boolean subsetSum(int[] conjunto, int n, int objetivo) {
        if (objetivo == 0) return true;
        if (n == 0) return false;

        if (conjunto[n - 1] > objetivo) {
            return subsetSum(conjunto, n - 1, objetivo);
        }

        return subsetSum(conjunto, n - 1, objetivo) ||
               subsetSum(conjunto, n - 1, objetivo - conjunto[n - 1]);
    }

    /** Tamaño del tablero de Sudoku (9x9). */
    static final int N = 9;

    /**
     * Verifica si es seguro colocar un número en una posición del Sudoku.
     *
     * @param tablero tablero de Sudoku
     * @param fila fila en la que se intenta colocar el número
     * @param col columna en la que se intenta colocar el número
     * @param num número a colocar
     * @return true si es seguro, false en caso contrario
     */
    public static boolean esSeguroSudoku(int[][] tablero, int fila, int col, int num) {
        for (int x = 0; x < N; x++) {
            if (tablero[fila][x] == num) return false;
        }

        for (int x = 0; x < N; x++) {
            if (tablero[x][col] == num) return false;
        }

        int startRow = fila - fila % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i + startRow][j + startCol] == num) return false;
            }
        }

        return true;
    }

    /**
     * Resuelve el Sudoku usando el algoritmo de backtracking.
     *
     * @param tablero tablero de Sudoku
     * @return true si se resolvió correctamente, false en caso contrario
     */
    public static boolean resolverSudoku(int[][] tablero) {
        for (int fila = 0; fila < N; fila++) {
            for (int col = 0; col < N; col++) {
                if (tablero[fila][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (esSeguroSudoku(tablero, fila, col, num)) {
                            tablero[fila][col] = num;

                            if (resolverSudoku(tablero)) return true;

                            tablero[fila][col] = 0; // backtracking
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Imprime el tablero de Sudoku en consola.
     *
     * @param tablero tablero de Sudoku
     */
    public static void imprimirSudoku(int[][] tablero) {
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(tablero[r][d] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Método principal con menú para elegir entre los tres problemas.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE PROBLEMAS ===");
            System.out.println("1. Fibonacci (recursivo)");
            System.out.println("2. Subset Sum (suma de subconjuntos)");
            System.out.println("3. Backtracking (Sudoku)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número n para Fibonacci: ");
                    int n = sc.nextInt();
                    System.out.println("Fibonacci de " + n + " es: " + fibonacci(n));
                    break;

                case 2:
                    int[] conjunto = {3, 34, 4, 12, 5, 2};
                    System.out.print("Ingrese el valor objetivo: ");
                    int objetivo = sc.nextInt();
                    if (subsetSum(conjunto, conjunto.length, objetivo)) {
                        System.out.println("Sí existe un subconjunto que suma " + objetivo);
                    } else {
                        System.out.println("No existe un subconjunto que sume " + objetivo);
                    }
                    break;

                case 3:
                    int[][] tablero = {
                        {5, 3, 0, 0, 7, 0, 0, 0, 0},
                        {6, 0, 0, 1, 9, 5, 0, 0, 0},
                        {0, 9, 8, 0, 0, 0, 0, 6, 0},
                        {8, 0, 0, 0, 6, 0, 0, 0, 3},
                        {4, 0, 0, 8, 0, 3, 0, 0, 1},
                        {7, 0, 0, 0, 2, 0, 0, 0, 6},
                        {0, 6, 0, 0, 0, 0, 2, 8, 0},
                        {0, 0, 0, 4, 1, 9, 0, 0, 5},
                        {0, 0, 0, 0, 8, 0, 0, 7, 9}
                    };

                    System.out.println("Sudoku inicial:");
                    imprimirSudoku(tablero);

                    if (resolverSudoku(tablero)) {
                        System.out.println("\nSudoku resuelto:");
                        imprimirSudoku(tablero);
                    } else {
                        System.out.println("No existe solución para este Sudoku.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
