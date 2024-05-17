/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Tablero {

    private List<Jugador> jugadores;
    private List<Serpiente> serpientes;
    private List<Escalera> escaleras;
    private String modo;

    public Tablero(String modo) {
        jugadores = new ArrayList<>();
        serpientes = new ArrayList<>();
        escaleras = new ArrayList<>();
        this.modo = modo;
        inicializarSerpientes();
        inicializarEscaleras();
    }

    public void crearJugador(int cantidad) {
        Color[] colores = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA}; // Colores definidos

        for (int i = 0; i < cantidad; i++) {
            // Asigna uno de los colores definidos a cada jugador
            Color color = colores[i % colores.length]; // Usa el índice para obtener el color
            jugadores.add(new Jugador(color));
        }
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int tirarDado() {
        return (int) (Math.random() * 6) + 1; // Devuelve un número entre 1 y 6

    }

    private void inicializarSerpientes() {
        // Ejemplo de inicialización de serpientes
        if (modo == "10x10") {
            serpientes.add(new Serpiente(98, 1));
            serpientes.add(new Serpiente(70, 50));
            serpientes.add(new Serpiente(35, 24));
            serpientes.add(new Serpiente(32, 7));
            serpientes.add(new Serpiente(95, 75));
            serpientes.add(new Serpiente(66, 46));
        }
        if (modo == "13x13") {
            serpientes.add(new Serpiente(167, 11));
            serpientes.add(new Serpiente(106, 54));
            serpientes.add(new Serpiente(70, 18));
            serpientes.add(new Serpiente(170, 126));
            serpientes.add(new Serpiente(94, 42));
            serpientes.add(new Serpiente(77, 25));
            serpientes.add(new Serpiente(47, 4));
        }
        if (modo == "15x15") {
            serpientes.add(new Serpiente(223, 13));
            serpientes.add(new Serpiente(161, 108));
            serpientes.add(new Serpiente(99, 39));
            serpientes.add(new Serpiente(211, 178));
            serpientes.add(new Serpiente(87, 27));
            serpientes.add(new Serpiente(51, 8));
            serpientes.add(new Serpiente(218, 201));
        }

    }

    private void inicializarEscaleras() {
        // Ejemplo de inicialización de serpientes
        if (modo == "10x10") {
            escaleras.add(new Escalera(9, 29));
            escaleras.add(new Escalera(82, 97));
            escaleras.add(new Escalera(16, 56));
            escaleras.add(new Escalera(52, 72));
            escaleras.add(new Escalera(59, 79));
            escaleras.add(new Escalera(22, 42));
        }
        if (modo == "13x13") {
            escaleras.add(new Escalera(122, 148));
            escaleras.add(new Escalera(12, 64));
            escaleras.add(new Escalera(73, 99));
            escaleras.add(new Escalera(129, 156));
            escaleras.add(new Escalera(120, 139));
            escaleras.add(new Escalera(2, 28));
            serpientes.add(new Serpiente(71, 110));
        }
        if (modo == "15x15") {
            escaleras.add(new Escalera(11, 48));
            escaleras.add(new Escalera(25, 85));
            escaleras.add(new Escalera(60, 119));
            escaleras.add(new Escalera(128, 158));
            escaleras.add(new Escalera(115, 184));
            escaleras.add(new Escalera(190, 199));
            serpientes.add(new Serpiente(104, 135));
        }

    }

    public void mensaje() {
        System.out.println(modo);
    }

    public int verificarSerpiente(int posicion) {
        for (Serpiente serpiente : serpientes) {
            if (posicion == serpiente.getPosicionAlta()) {
                return serpiente.getPosicionBaja();
            }
        }
        return posicion;
    }

    public int verificarEscalera(int posicion) {
        for (Escalera escalera : escaleras) {
            if (posicion == escalera.getPosicionBaja()) {
                return escalera.getPosicionAlta();
            }
        }
        return posicion;
    }

}
