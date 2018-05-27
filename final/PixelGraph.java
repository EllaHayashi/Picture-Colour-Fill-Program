/* PixelGraph.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Graph Data Structure

   Ella Hayashi
   based on a template made by bill bird
*/ 

import java.awt.Color;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.text.*;
import java.util.Arrays;


public class PixelGraph{

	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/

	private Color[][] imagePixels;
	private PixelVertex[][] pixelArray;


	public PixelGraph(Color[][] imagePixels){
						
		this.imagePixels = imagePixels;	
	
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/

	public PixelVertex getPixelVertex(int x, int y){

		PixelVertex[][] tempArray = new PixelVertex[getHeight()][getWidth()];
		
		for(int i = 0; i<getHeight(); i++)
		{
			for(int j=0; j<getWidth(); j++)
			{
				PixelVertex v = new PixelVertex(i, j);
				tempArray[i][j] = v;			
			}
		}
							
		for(int i = 0; i<getHeight(); i++)
		{
			for(int j=0; j<getWidth(); j++)
			{						
				PixelVertex v = tempArray[i][j];

				if(i != getHeight()-1)
				{ 
					if(imagePixels[i][j].equals(imagePixels[i+1][j]))
					{
						v.addNeighbour(tempArray[i+1][j]);				
					}
				}
				
				if(i != 0)
				{
					if(imagePixels[i][j].equals(imagePixels[i-1][j]))
					{
						v.addNeighbour(tempArray[i-1][j]);		
					}
				}

				if(j != getWidth()-1)
				{
					if(imagePixels[i][j].equals(imagePixels[i][j+1]))
					{
						v.addNeighbour(tempArray[i][j+1]);											
					}
				}

				if(j != 0)
				{
					if(imagePixels[i][j].equals(imagePixels[i][j-1]))		
					{
						v.addNeighbour(tempArray[i][j-1]);							
					}
				}			
				tempArray[i][j] = v;			
			}
		}

		return tempArray[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return imagePixels[0].length;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return imagePixels.length;
	}
}