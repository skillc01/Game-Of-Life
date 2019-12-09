package newPackage;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.*;
import java.awt.image.*;

public class GameOfLife extends Canvas implements Runnable
{
	
	public static void main(String[] args)
	{  
		//creates and shows the window 
		JFrame f = new JFrame();
		f.setResizable(false);  //window cannot be resized
		f.setTitle("Game of Life");  //title of window
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closing ends program		
		GameOfLife life = new GameOfLife(); //initialise
		f.add(life);  //adds to the frame
		f.pack();  //sizes frame so content at preferred size
		f.setVisible(true);	//allows us to see the frame + contents
		life.start();
	}
	
	public static int size = 10;    //size of grid (10*10)
	public int[] cells;             //int array for cells (cells.length = no. of cells on the grid)
	public Random rand = new Random();
	
	public BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_BGR); //10*10 cells in size //8bitRGBcolor
	// the BufferedImage variable 'image' is the variable drawn to the screen through the render method
	
	
	public boolean[] gridC;
	public boolean[] gridcellive;  
	
	public GameOfLife()
	{   //2
		//sets the size of the window (dimensions)
		Dimension dim = new Dimension(600, 600); //window size 600*600
		setPreferredSize(dim);
		//getRaster returns the original pixel array/data source allowing to actually write pixels to it.
		cells = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
	}
	
	public void start()
	{ 
		gridC = new boolean[cells.length]; //(on 10*10 grid) cells.length is 100    
		gridcellive = new boolean[cells.length];    //contains no. of cells on the grid           
		
		//loop through cells
		for(int i = 0; i < gridC.length; i++)
			if (rand.nextInt(100) > 70) {//random alive and dead cells //30% chance of cells spawning alive
				     			//if randomly generated no. (between 0-100) is less than 70 , cell is alive (upon start)  
				gridC[i] = true;
				}
				else {         // else cell is dead upon start
				  gridC[i] = false;
				}
		//^ starts a random lot of alive and dead cells upon program start 
		// then the rules of GoL are applied to the random starting gen
	
		new Thread(this).start();  //start
	}
	
	public void run()
	{
		long count = System.currentTimeMillis(); 
				
		while(true)  //updates and renders 
		{
			if(System.currentTimeMillis() - count >= 1000) //each second is a generation
			{   update();                                //updates/runs update method each second/generation
				count = System.currentTimeMillis();      
				System.out.println("Next Generation");  //prints each second/generation
			}
			render();
		}
	}
	
	public void render() //render method //array of pixels contains every pixel in the bufferedimage
	{
		BufferStrategy b = getBufferStrategy();
		if(b == null) { createBufferStrategy(3); return; }
		Graphics g = b.getDrawGraphics();
		g.drawImage(image, 0, 0, 600, 600, null);

		for (int j = 0;cells.length > j; j++)
			cells[j] = 0;
		
		for (int j = 0;cells.length > j; j++)
			if (gridC[j]) {
				  cells[j] = 0xbb0000;  //live cells are blue(bb0000) 
				}
				else {
				  cells[j] = 0xffffff;  //dead cells are white
				}
		
		g.dispose();
		b.show();
	}
	
	public void update()  //update live neighbours
	{
		for(int i = 0; i < cells.length; i++)
			gridcellive[i] = gridC[i];
		
		for(int x = 0; x < size; x++)//loop through cells x
		{
			for(int y = 0; y < size; y++)//loop through cells y
			{
				int xx0 = x - 1;
				int xx1 = x + 1;
				int yy0 = y - 1;
				int yy1 = y + 1;
				int nebs = 0; 
				
				if(x != 0)
					if (gridcellive[xx0 + size * y])
						nebs = nebs + 1;         //cell alive
						
						else 
							nebs = nebs + 0;
				if(y != 0)
					if (gridcellive[x + size * yy0])
						nebs = nebs + 1;        
						
						else 
							nebs = nebs + 0;
				if(x != size - 1)
					if (gridcellive[xx1 + size * y])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				if(y != size - 1)
					if (gridcellive[x + size * yy1])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				
				if(x != 0 && y != 0)
					if (gridcellive[xx0 + size * yy0])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				if(x != 0 && y != size - 1)
					if (gridcellive[xx0 + size * yy1])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				if(x != size - 1 && y != 0)
					if (gridcellive[xx1 + size * yy0])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				if(x != size - 1 && y != size - 1)
					if (gridcellive[xx1 + size * yy1])
						nebs = nebs + 1;
						
						else 
							nebs = nebs + 0;
				
				//GoL rules
				//
				if(!(gridcellive[x + size * y] && (nebs == 2 || nebs == 3))) 
					gridC[x + size * y] = false; 
				
				if(!gridcellive[x + size * y] && nebs == 3) 
					gridC[x + size * y] = true;
			}
			
		}
	}
		
}