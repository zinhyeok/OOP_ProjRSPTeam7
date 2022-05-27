package myutil;

import java.util.Random;

public class Multinomial {
	static Random generator = new Random(System.currentTimeMillis());
	double[] distribution;
	int range;

	//Constructor
	public Multinomial(double[] probabilities){
		range = probabilities.length;
		distribution = new double[range];
		double sumProb = 0;
		for (double value : probabilities){
			sumProb += value;
		}
		double position = 0;
		for (int i = 0; i < range; ++i){
			position += probabilities[i] / sumProb;
			distribution[i] = position;
		}
		distribution[range -1] = 1.0;
	}

	public int sample() {
		double uniform = generator.nextDouble();
		for (int i = 0; i < range; ++i){
			if (uniform < distribution[i]){
				return i;
			}
		}
		return range - 1;
	}

	public static void main(String[] args) {
		int casenum = 2;
		double[] initprob = new double[3];

		if (casenum == 1)
		{
			initprob[0] = 0.3d;
			initprob[1] = 0.5d;
			initprob[2] = 0.7d;			
		} else {
			Random rn = new Random(System.currentTimeMillis());
			initprob[0] = rn.nextDouble();
			initprob[1] = rn.nextDouble();
			initprob[2] = rn.nextDouble();
		}
		

		Multinomial mymn = new Multinomial(initprob);
		for (int k=0;k <100 ; k++ )
		{
			System.out.println("mymn.sample():" + mymn.sample());
		}		
	}
}