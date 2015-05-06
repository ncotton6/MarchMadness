package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

public class KNNSim implements GameSimulator {

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		// TODO Auto-generated method stub
        
        // Associate results of season with tournament placement
        // Idea: associate results of previous tournaments (training set)
        // 
        // Find good values of k (3, 5, 7, etc.)
        // Training set = previous years
        
        /*
        An idea for cluster orientation (n = # seasons):
        32*n - Lost in first round
        16*n - Lost in round of 32
        8*n - Lost in sweet sixteen
        4*n - Lost in elite eight
        2*n - Lost in final four
        1*n - Lost in championship game
        1*n - Won championship game
        
        Run knn on a season based on a training set.
        Classify each team from that season.
        
        Problem: Too many teams may be classified as the same class
        (e.g. predicted that 34 teams will lose in the first round)
        
        Possible (Naive) Solution: If a class is "full", pick the next best class
        
        Better Solution: Knock out team farthest from the center, put the team that
        better fits the classification in that cluster, and reclassify the knocked-out
        team.
        
        Learning: Find what stats and values of k give best result, and tweak parameters.
        */
        
		return null;
	}

    //
    // p1 is essentially the unclassified point
    // p is essentially the training set
    // k is how many neighbors are desired
    //
    /*public void knn(Prototype p1, Prototype[] p, int k)
    {
        Protodist[] pd (contains Prototype p[] and double dist[], implements Comparable)
        for(int i = 0; i < p.length; ++i)
        {
            pd.put(p[i], euclidean distance from p[i] to p)
        }
        sort(pd) (sort based on dist
        Prototype[] nn = get first k elements from pd (the k nearest neighbors)
        
        // Get the count of each classification from the neighbors. The most frequent
        // classification is the one that p1 will fall into.
        int[] counts
        for(n in nn)
        {
            counts[nn.getClassificationAsInt()]++;
        }
        p1.classify(indexOfMax(counts))
    }*/
}
