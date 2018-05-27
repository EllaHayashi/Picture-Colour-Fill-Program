/* A5Algorithms.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Image Algorithms


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


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

public class A3Algorithms{

	/* FloodFillDFS(v, viewer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
		
	
	public static void FloodFillDFS(PixelVertex v, ImageViewer225 viewer, Color fillColour)
	{			
		Stack<PixelVertex> stack = new Stack<PixelVertex>();
	
		stack.push(v);
		while(!stack.isEmpty())
		{
			PixelVertex s = stack.pop();
			
			int x_value = s.getX();	
			int y_value = s.getY();
			viewer.setPixel(x_value, y_value, fillColour);
				
			PixelVertex[] neighArray = s.getNeighbours(s);
			int value = -1;
				
			for(int i=0; i<4; i++)
			{
				if(neighArray[i]!=null && neighArray[i]!=s)
				{
					value = i;		
					stack.push(neighArray[i]);
					neighArray[i].removeNeighbour(s);
					s.removeNeighbour(neighArray[i]);						
				}
			}
			if(value != -1)
			{
				PixelVertex r = stack.pop();		
				int newX = r.getX();
				int newY = r.getY();
				viewer.setPixel(newX, newY, fillColour);

				stack.push(r);
			}		
		}		
	}
	
	
	/* FloodFillBFS(v, viewer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){

		Queue<PixelVertex> queue = new LinkedList<PixelVertex>();
		int x_value = v.getX();	
		int y_value = v.getY();
		viewer.setPixel(x_value, y_value, fillColour);

		PixelVertex[] neighbours = v.getNeighbours(v);
	
		for(int i=0; i<4; i++)
		{
			if(neighbours[i]!=null)
			
			{
				neighbours[i].removeNeighbour(v);
			}
		}

		queue.add(v);
		while(!queue.isEmpty())
		{
			PixelVertex s = queue.remove();
			PixelVertex[] neigh = s.getNeighbours(s);
			for(int i=0; i<4; i++)
			{
				if(neigh[i]!=null)
				{				
					int newX = neigh[i].getX();
					int newY = neigh[i].getY();

					viewer.setPixel(newX, newY, fillColour);
					queue.add(neigh[i]);	
					neigh[i].removeNeighbour(s);
				
					s.removeNeighbour(neigh[i]);						
				}
			}	
		}
	}
	
	/* OutlineRegionDFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionDFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){
		/* Your code here */
		Stack<PixelVertex> stack = new Stack<PixelVertex>();

		int degree = v.getDegree();
	
		if(degree < 4)
		{		
			int x_value = v.getX();	
			int y_value = v.getY();
			viewer.setPixel(x_value, y_value, fillColour);	
		}
	
		stack.push(v);
				
		while(!stack.isEmpty())
		{
			PixelVertex s = stack.pop();
			int degree4 = s.getDegree();
			if(degree4 < 4)
			{		
				int xnow = s.getX();	
				int ynow= s.getY();
				viewer.setPixel(xnow, ynow, fillColour);	
			}
					
			PixelVertex[] getNeigh	= s.getNeighbours(s);
			int foundNeighbour = -1;
			for(int i = 0; i<4; i++)
			{
				if(getNeigh[i]!=null && getNeigh[i]!=s)
				{
					foundNeighbour = i;
					stack.push(getNeigh[i]);
					getNeigh[i].removeNeighbour(s);
					s.removeNeighbour(getNeigh[i]);								
				}
			}
			
			if(foundNeighbour != -1)
			{				
				PixelVertex r = stack.pop();		
				int degree2 = r.getDegree();
				if(degree2<4)
				{
					int newX = r.getX();
					int newY = r.getY();
					viewer.setPixel(newX, newY, fillColour);
				}
				stack.push(r);		
			}
		}
	}
	
	/* OutlineRegionBFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionBFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){

		Queue<PixelVertex> queue = new LinkedList<PixelVertex>();
	
		int degree = v.getDegree();
		if(degree<4)
		{		
			int x_value = v.getX();	
			int y_value = v.getY();
			viewer.setPixel(x_value, y_value, fillColour);
		}
		
		PixelVertex[] neighbours = v.getNeighbours(v);
	
		for(int i=0; i<4; i++)
		{
			if(neighbours[i]!=null)	
			{
				neighbours[i].removeNeighbour(v);
			}
		}

		queue.add(v);
		while(!queue.isEmpty())
		{
			PixelVertex s = queue.remove();

			PixelVertex[] neigh = s.getNeighbours(s);
			for(int i=0; i<4; i++)
			{
				if(neigh[i]!=null && neigh[i]!=s)
				{			
					int degree2 = neigh[i].getDegree();
					if(degree2<4)
					{				
						int newX = neigh[i].getX();
						int newY = neigh[i].getY();

						viewer.setPixel(newX, newY, fillColour);				
					}
						queue.add(neigh[i]);	
						neigh[i].removeNeighbour(s);
				
						s.removeNeighbour(neigh[i]);				
				}
			}	
		}
	}


	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/
	public static int CountComponents(PixelGraph G){
		int[][] graphArray = new int[G.getHeight()][G.getWidth()];	
		int count = 0;
		int stop = 0;
			
		do{			
			int height = -1;
			int width = -1;
			for (int i = 0; i<G.getHeight(); i++)
			{
				for(int k=0; k<G.getWidth(); k++)
				{
					if(graphArray[i][k]==0)
					{
						height = i;
						width = k;
					}
				}		
			}
		
			if(height!=-1 && width!=-1)
			{		
				PixelVertex v = G.getPixelVertex(height, width);
				graphArray[height][width] = 1;
		
				Stack<PixelVertex> stack = new Stack<PixelVertex>();
		
				stack.push(v);
				count++;
		
				while(!stack.isEmpty())
				{
					PixelVertex s = stack.pop();
					PixelVertex[] neighArray = s.getNeighbours(s);
	
					for(int i=0; i<4; i++)
					{
						if(neighArray[i]!=null && neighArray[i]!=s)
						{
							int xval = neighArray[i].getX();
							int yval = neighArray[i].getY();
							graphArray[xval][yval] = 1;
						
							stack.push(neighArray[i]);
							neighArray[i].removeNeighbour(s);
							s.removeNeighbour(neighArray[i]);					
						}
					}	
				}
			}
			else
			{
				stop = 1;
			}	
		}while(stop==0);
			
		return count;
	}
	
}
	
	
