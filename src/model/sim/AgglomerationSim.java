package model.sim;

import java.lang.reflect.Method;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.Tuple;
import model.data.Team;
import model.data.TeamStat;

import java.util.ArrayList;
import util.*;

/**
 * This implementation of the {@link GameSimulator} will use agglomeration of
 * data to determine who will win a particular game.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class AgglomerationSim implements GameSimulator {

	private String season;
	private Method aggloValue;

	public AgglomerationSim() {
	}

	public AgglomerationSim(String season, Method aggloValue) {
		this.season = season;
		this.aggloValue = aggloValue;
	}
    
    
    
    public class Prototype
    {
        ArrayList<Integer[]> data;
        ArrayList<Integer> cluster;
        int id;
        
        public Prototype(int[] data, int id)
        {
            this.data = new ArrayList<Integer[]>();
            Integer[] dataNew = new Integer[data.length];
            for(int i = 0; i < data.length; ++i)
            {
                dataNew[i] = data[i];
            }
            this.data.add(dataNew);
            cluster = new ArrayList<Integer>();
            this.id = id;
            cluster.add(id);
        }
        
        public ArrayList<Integer> getCluster()
        {
            return cluster;
        }
        
        public int getId()
        {
            return id;
        }
        
        public ArrayList<Integer[]> getData()
        {
            return data;
        }
        
        public double[] getCenter()
        {
            double[] center = new double[data.get(0).length];
            for(int i = 0; i < data.get(0).length; ++i)
            {
                double sum = 0.0;
                for(int j = 0; j < data.size(); ++j)
                {
                    sum += data.get(j)[i];
                }
                center[i] = sum/data.size();
            }
            return center;
        }
        
        public String toString()
        {
            //return Format.formatR(getCenter(),3);
            return null;
        }
        
        public void merge(Prototype p)
        {
            ArrayList<Integer[]> newData = p.getData();
            
            for(int i = 0; i < newData.size(); ++i)
            {
                data.add(newData.get(i));
            }
            ArrayList<Integer> old = p.getCluster();
            for(int i = 0; i < old.size(); ++i)
            {
                cluster.add(old.get(i));
            }
        }
    }
    
    public static Prototype[] removeItem(Prototype[] a, int n)
    {
        Prototype[] newArr = new Prototype[a.length-1];
        for(int i = 0; i < n; ++i)
        {
            newArr[i] = a[i];
        }
        for(int i = n; i < newArr.length; ++i)
        {
            newArr[i] = a[i+1];
        }
        return newArr;
    }

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
        double aTvalue = 0;
        double bTvalue = 0;
        try {
			Team a = game.getA();
			Team b = game.getB();
			TeamStat tsa = Link.getTeamStat(a, season);
			TeamStat tsb = Link.getTeamStat(b, season);
			// TODO possible make this more generic, not have double be the
			// return type.
			aTvalue = 0;
			bTvalue = 0;
			Object aValue = aggloValue.invoke(tsa, new Object[] {});
			Object bValue = aggloValue.invoke(tsb, new Object[] {});
			if (aValue instanceof Integer) {
				aTvalue = ((Integer) aValue).doubleValue();
				bTvalue = ((Integer) bValue).doubleValue();
			} else if (aValue instanceof Double) {
				aTvalue = ((Double) aValue).doubleValue();
				bTvalue = ((Double) bValue).doubleValue();
			}
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        return new Tuple<Double, Double>(aTvalue, bTvalue);
	}

    /**
     * Agglomeration algorithm
     *
     * @param p Prototypes
     * @param numClusters Number of clusters at end of algorithm
     */
    public void agglomerate(Prototype[] p, int numClusters)
    {
        // Distances betwen clusters
        double[][] dists = new double[p.length][p.length];
        for(int i = 0; i < dists.length; ++i)
        {
            dists[i][i] = Double.MAX_VALUE;
        }
        
        // Minimum found on each iteration
        double min = Double.MAX_VALUE;
        int iMin = -1;
        int jMin = -1;
        
        // core algorithm
        for(int k = dists.length; k > 3; k--)
        {
            for(int i = 1; i < dists.length; ++i)
            {
                for(int j = 0; j < i; ++j)
                {
                    // Get centers and find distances
                    double[] c1 = p[i].getCenter();
                    double[] c2 = p[j].getCenter();
                    dists[i][j] = euclidean(c1, c2);
                    dists[j][i] = dists[i][j];
                    
                    // Add minimum distance
                    if(dists[i][j] < min)
                    {
                        min = dists[i][j];
                        iMin = i;
                        jMin = j;
                    }
                }
            }
            
            // Merge two closest clusters and remove the second one
            merge(p[iMin], p[jMin]);
            p = removeItem(p,jMin);
            dists = ArrayUtils.removeRowCol(dists,jMin);
            
            // Reset min for next iteration
            min = Double.MAX_VALUE;
        }
        
        // Print out clusters
        for(int i = 0; i < p.length; ++i)
        {
            if(p[i] != null)
            {
                ArrayList<Integer> cluster = p[i].getCluster();
                java.util.Collections.sort(cluster);
                ListUtils.printList(cluster,"Cluster " + (i+1));
                //ArrayUtils.printArrayF(p[i].getCenter(),2);
                System.out.println();
            }
        }
    }
    
    /**
     * Merges one prototype into another.
     *
     * @param p1 The prototype to hold the merge
     * @param p2 The merged prototype (is set to null)
     */
    public void merge(Prototype p1, Prototype p2)
    {
        p1.merge(p2);
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