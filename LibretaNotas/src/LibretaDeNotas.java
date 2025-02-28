import java.util.*;

// Clase que almacena y evalúa calificaciones de estudiantes
public class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> calificaciones; // Almacena las calificaciones de los estudiantes
    private Scanner scanner; // Para leer la entrada del usuario

    public LibretaDeNotas() {
        this.calificaciones = new HashMap<>(); // Inicializa el HashMap
        this.scanner = new Scanner(System.in); // Inicializa el Scanner para entrada de datos
    }

    // Método para ingresar los datos de los estudiantes y sus calificaciones
    public void ingresarDatos() {
        System.out.print("Ingrese la cantidad de alumnos: ");
        int numAlumnos = scanner.nextInt(); // Lee la cantidad de alumnos
        scanner.nextLine(); // Limpia el buffer del scanner

        for (int i = 0; i < numAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno: ");
            String nombre = scanner.nextLine(); // Lee el nombre del estudiante
            ArrayList<Double> notas = new ArrayList<>(); // Lista para almacenar las notas del estudiante

            for (int j = 0; j < 3; j++) { // Solo se solicitan 3 notas
                System.out.print("Ingrese la nota " + (j + 1) + " para " + nombre + ": ");
                notas.add(scanner.nextDouble()); // Agrega cada nota a la lista
            }
            scanner.nextLine(); // Limpia el buffer del scanner
            calificaciones.put(nombre, notas); // Almacena las notas en el HashMap
        }
    }

    // Método para calcular el promedio de un estudiante
    private double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota; // Suma todas las calificaciones
        }
        return suma / notas.size(); // Calcula el promedio
    }

    // Método para obtener la nota más alta de un estudiante
    private double obtenerNotaMaxima(ArrayList<Double> notas) {
        return Collections.max(notas); // Encuentra la calificación más alta
    }

    // Método para obtener la nota más baja de un estudiante
    private double obtenerNotaMinima(ArrayList<Double> notas) {
        return Collections.min(notas); // Encuentra la calificación más baja
    }

    // Método para calcular el promedio del curso
    private double calcularPromedioCurso() {
        double sumaTotal = 0;
        int totalNotas = 0;
        for (ArrayList<Double> notas : calificaciones.values()) {
            sumaTotal += notas.stream().mapToDouble(Double::doubleValue).sum(); // Suma todas las notas del curso
            totalNotas += notas.size(); // Cuenta el total de notas
        }
        return sumaTotal / totalNotas; // Calcula el promedio del curso
    }

    // Método para mostrar el menú de opciones
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); // Lee la opción seleccionada
            scanner.nextLine(); // Limpia el buffer del scanner

            switch (opcion) {
                case 1:
                    for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
                        double promedio = calcularPromedio(entry.getValue()); // Calcula el promedio
                        System.out.println(entry.getKey() + " - Promedio: " + promedio);
                    }
                    break;
                case 2:
                    for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
                        double promedio = calcularPromedio(entry.getValue()); // Calcula el promedio
                        String estado = promedio >= 60 ? "Aprobado" : "Reprobado"; // Determina si aprueba o reprueba
                        System.out.println(entry.getKey() + " - " + estado);
                    }
                    break;
                case 3:
                    double promedioCurso = calcularPromedioCurso(); // Calcula el promedio del curso
                    for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
                        double promedio = calcularPromedio(entry.getValue()); // Calcula el promedio del estudiante
                        String comparacion = promedio >= promedioCurso ? "Sobre" : "Debajo"; // Compara con el promedio del curso
                        System.out.println(entry.getKey() + " - " + comparacion + " del promedio del curso");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo."); // Mensaje de error si la opción es inválida
            }
        } while (opcion != 0); // Repite hasta que el usuario elija salir
    }

    // Método principal que ejecuta el programa
    public static void main(String[] args) {
        LibretaDeNotas libreta = new LibretaDeNotas(); // Crea una instancia de la clase
        libreta.ingresarDatos(); // Llama al método para ingresar datos
        libreta.mostrarMenu(); // Llama al método para mostrar el menú
    }
}
