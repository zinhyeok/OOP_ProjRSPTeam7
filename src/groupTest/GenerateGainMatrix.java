package groupTest;

import java.util.Random;

public class GenerateGainMatrix
{

	public static float[][] generate(int dim){
	  Random r=new Random(System.currentTimeMillis());
	  float[][] a=new float[dim][dim];
	  for(int i=0;i<dim;i++)
	  {
		  for(int j=0;j<dim;j++)
		  {
			 a[i][j]=r.nextFloat();			 
		  }
	  }
	  return a;
	}

	public static float[][] generateGainCentered(int dim){
	  float[][] gainmat = generate(dim);
	  docentering(gainmat);
	  return gainmat;
	}

	public static float[][] generateGainCentered(){	  
	  return generateGainCentered(3);
	}

	public static void docentering(float[][] data) {
	  float sum = 0;
	  for(int i=0;i<data.length;i++)
	  {
		  for(int j=0;j<data[0].length;j++)
		  {
			 sum = sum + data[i][j];
		  }	  
	  }
	  float average = sum / (data.length * data[0].length);
	  for(int i=0;i<data.length;i++)
	  {
		  for(int j=0;j<data[0].length;j++)
		  {
			 data[i][j] = data[i][j] - average;
		  }	  
	  }
	}
	
	public static void display(float[][] data) {
	  System.out.println();	  
	  System.out.println();
	  for(int i=0;i<data.length;i++)
	  {
		  for(int j=0;j<data[0].length;j++)
		  {
			 System.out.print(data[i][j]+"\t");
		  }
	  
		 System.out.println();
	  }	  
	}

  
  public static void main(String[] args) {    
	  float[][] gainmat = generate(3);
	  display(gainmat);
	  docentering(gainmat);
	  display(gainmat);

	  display(generateGainCentered());
  }
}