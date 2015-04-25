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
 * @author Ryan Conrad
 */
public class AgglomerationSim {

	private String season;
	private Method aggloValue;
    private ArrayList<Double>[] teamStatNums;
    private Prototype[] p;

    @SuppressWarnings("unchecked")
	public AgglomerationSim() {
    
		this.season = season;
		this.aggloValue = aggloValue;
        this.teamStatNums = (ArrayList<Double>[])new ArrayList[68];
        for(int i = 0; i < teamStatNums.length; ++i)
        {
            teamStatNums[i] = new ArrayList<Double>(32);
        }
        this.p = new Prototype[68];
	}
    
    public class Prototype
    {
        ArrayList<Double[]> data;
        ArrayList<Integer> cluster;
        int id;
        
        public Prototype(Double[] data, int id)
        {
            this.data.add(data);
            this.id = id;
        }
        
        public Prototype(double[] data, int id)
        {
            this.data = new ArrayList<Double[]>();
            Double[] dataNew = new Double[data.length];
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
        
        public ArrayList<Double[]> getData()
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
            ArrayList<Double[]> newData = p.getData();
            
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

	public void addData(double i, int row, int col) {
        teamStatNums[row].add(i);
	}
    
    public void initProtos()
    {
        for(int i = 0; i < teamStatNums.length; ++i)
        {
            for(int j = 0; j < teamStatNums[0].size(); ++j)
            {
                System.out.print(teamStatNums[i].get(j) + " ");
            }
            System.out.println(teamStatNums[i].size());
        }
        for(int i = 0; i < p.length; ++i)
        {
            p[i] = new Prototype(
                teamStatNums[i].toArray(
                    new Double[teamStatNums[i].size()]),
                i);
        }
    }

    /**
     * Agglomeration algorithm
     *
     * @param p Prototypes
     * @param numClusters Number of clusters at end of algorithm
     */
    public void agglomerate(int numClusters)
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
