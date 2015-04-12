package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This implementation of the {@link GameSimulator} will use agglomeration of
 * data to determine who will win a particular game.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class AgglomerationSim implements GameSimulator {

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
        // Get methods
        
        // More setup
        
        // Use reflection to call the appropriate distance metric method?
        
        // From here, compare the teams based on the clusters and
        // return the tuple with appropriate results.
        
		return new Tuple<Double, Double>(1d, 0d);
	}

    public void agglomerate()
    {
        // Agglomeration algorithm
        
        // ArrayList<double>[] clusters
        // n = number of data points
        // double[] dists = new double[n][n];
        // numClusters = m
        // min = Double.MAX_VALUE;
        // min_1 = -1
        // min_2 = -1
        //
        // -1 either means invalid or already added to cluster
        // 
        //  loop until m clusters left
        //      loop i in dists
        //          loop j in dists[]
        //              if(dists[i][j] != -1)
        //                  if(i or j most recently added to cluster)
        //                      // recalculate the distance with new cluster
        //                      dists[i][j] = lNorm(data1, data2, 2);
        //                  if(dists[i][j] < min)
        //                      min = dists[i][j]
        //                      min_1 = i
        //                      min_2 = j
        //      Combine min_1 and min_2, store in min_1
        //      delete min_1 and min_2 from any of the ArrayLists of clusters
        //      add min_1 and min_2 to a cluster in the ArrayList of clusters
        //          new list if neither min_1 nor min_2 were in a cluster
        //          otherwise, add one to the other's list
        //          if both had a list, merge smaller list into the bigger one
        //      loop i
        //          dists[i][min_2] = -1
        
        // Ex: For win/loss clusters, do the above with appropriate stats
        // for m = 2 clusters
    }
    /**
     * 
     * 
     * @param 
     * @param 
     */
    public double manhattan(double[] s, double[] e)
    {
        double dist = 0.0;
        for(int i = 0; i < s.length; ++i)
        {
            dist += Math.abs(s[i]-e[i]);
        }
        return dist;
    }

    /**
     * 
     * 
     * @param 
     * @param 
     */
    public double euclidean(double[] s, double e[])
    {
        double sqr = 0.0;
        for(int i = 0; i < s.length; ++i)
        {
            double diff = s[i]-e[i];
            sqr += diff*diff;
        }
        return Math.sqrt(sqr);
    }
    
    /**
     * L norm distance
     * 
     * @param 
     * @param 
     * @param 
     */
    public double lNorm(double[] s, double[] e, int l)
    {
        double inner = 0.0;
        double diff = 0.0;
        for(int i = 0; i < s.length; ++i)
        {
            diff = s[i]-e[i];
            for(int j = 1; j < l; ++j)
            {
                diff *= diff;
            }
            inner += diff;
        }
        return Math.pow(inner, (double)1/l);
    }
}