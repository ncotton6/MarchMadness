package model.sim;

import java.lang.reflect.Method;

import java.io.*;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.Tuple;
import model.data.Team;
import model.data.TeamStat;
import model.data.Loader;

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
	private int aggloValue;
    private ArrayList<Double>[] teamStatNums;
    private Prototype[] p;
    private ArrayList<String> statNames;
    private String[] teamNames;
    
    private PrintWriter pw;

    @SuppressWarnings("unchecked")
	public AgglomerationSim(String season, int aggloValue) {
        statNames = new ArrayList<String>(6);
        try
        {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("AgglomerationResults.txt", true)));
            pw.println("");
            pw.println(season + ":");
            pw.println("");
        }
        catch(Exception e)
        {
            System.out.println("The compiler shouldn't assert that I need to catch these exceptions.");
            System.exit(99999);
        }
    
		this.season = season;
		this.aggloValue = aggloValue;
        this.teamStatNums = (ArrayList<Double>[])new ArrayList[64];
        this.teamNames = new String[64];
        for(int i = 0; i < teamStatNums.length; ++i)
        {
            teamStatNums[i] = new ArrayList<Double>(32);
        }
        this.p = new Prototype[64];
	}
    
    public class Prototype
    {
        ArrayList<Double[]> data;
        ArrayList<Integer> cluster;
        int id;
        
        public Prototype(Double[] data, int id)
        {
            this.data = new ArrayList<Double[]>();
            this.data.add(data);
            cluster = new ArrayList<Integer>();
            this.id = id;
            cluster.add(id);
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
        
        public double deviation(int index)
        {
            double mean = getCenter()[index];
            double sum = 0.0;
            for(int i = 0; i < data.size(); ++i)
            {
                sum += Math.pow(data.get(i)[index]-mean,2);
            }
            return sum/data.size();
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
        
        public void printCluster()
        {
            for(int i = 0; i < cluster.size()-1; ++i)
            {
                System.out.print(cluster.get(i) + ", ");
            }
            System.out.println(cluster.get(cluster.size()-1));
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
    
    public void addStat(String name)
    {
        statNames.add(name);
    }
    
    public void addTeam(String teamName, int row)
    {
        teamNames[row] = teamName;
    }
    
    public void initProtos()
    {
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
        for(int k = dists.length; k > numClusters; k--)
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
                
                System.out.println(Loader.teams.size());
                
                pw.print("Cluster " + (i+1) + ": ");
                for(int j = 0; j < cluster.size()-1; ++j)
                {
                    pw.print(teamNames[cluster.get(j)] + ", ");
                }
                pw.println(teamNames[cluster.get(cluster.size()-1)]);
                for(int j = 0; j < statNames.size(); ++j)
                {
                    pw.println("Avg " + statNames.get(j) + ": " + p[i].getCenter()[j]);
                    pw.println("St. Dev " + statNames.get(j) + ": " + p[i].deviation(j));
                }
                
                //ArrayUtils.printArrayF(p[i].getCenter(),2);
                System.out.println();
            }
        }
        pw.close();
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
    
    public void printClusters()
    {
        for(int i = 0; i < p.length; ++i)
        {
            p[i].printCluster();
        }
        System.out.println();
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
