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
        Run knn on a season based on a training set (e.g. tournaments from previous seasons).
        Classify each team from that season.
        
        Each round: Take the team that was classified to go farther.
        Set a tiebreaking procedure (most likely pick higher seed).
        Final Four/Championship tiebreakers: pick a stat/set of stats and compare
        (Same seeds can't face eachother until the final 3 games)
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
