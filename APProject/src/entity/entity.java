/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Acer
 */
//This is the super class where for the zombie you will def need these shit
public class entity {
    public int x,y;
    public int speed;
    
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
