/* PixelVertex.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Vertex Data Structure


    Ella Hayashi
   based on a template made by bill bird
*/


public class PixelVertex{

	private int x;
	private int y;
	private PixelVertex[] neighbour = new PixelVertex[4];	
	private boolean visited = false;
	private int degree;
	
	public PixelVertex(int x, int y){
		this.x = x;
		this.y = y;
		degree = 0;	
	}
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){	
		return x;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(PixelVertex v)
	{
		return neighbour;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour)
	{
		int x = degree;
		neighbour[x]=newNeighbour;
		degree = degree + 1;
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neigh){
		
		for(int i = 0; i<4; i++)
		{
			if (neighbour[i] == neigh)
			{
				neighbour[i]=null;
			}	
		}	
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
	//	return neighbour[0];
		return degree;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
		for(int i=0; i<4; i++)
		{
			if(neighbour[i] == otherVertex)
			{
				return true;
			}
		}	
		return false;		
	}	
}