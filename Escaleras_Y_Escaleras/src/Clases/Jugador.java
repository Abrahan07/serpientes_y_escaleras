/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.Color;

public class Jugador {
    private Color color;
    private int posicion;

    public Jugador(Color color) {
        this.color = color;
        this.posicion = 0;
    }

    public Color getColor() {
        return color;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}

    
    

