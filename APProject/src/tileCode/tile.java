/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tileCode;

import java.awt.image.BufferedImage;

/**
 *
 * @author Acer
 */
//This is the superclass for tiles
public class tile {
    //This is an image
    public BufferedImage image;
    //This is collision for a tile
    public boolean collision = false;
    //if the player stands here their death (i havent touched it)
    public boolean death = false;
    //if the player stands here they win
    public boolean victory = false;
    public boolean zombiePath = false;
}
