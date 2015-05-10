package model.sim;

import java.lang.reflect.Method;

import model.Attribute;
import model.Game;
import model.GameSimulator;
import model.Link;
import model.Tuple;
import model.data.Team;
import model.data.TeamStat;

import java.util.ArrayList;
import java.util.Arrays;
import util.*;

/**
 * This implementation of the {@link GameSimulator} will use agglomeration of
 * data to determine who will win a particular game.
 * 
 * @author Nathaniel Cotton
 * @author Ryan Conrad
 */
public class AgglomerationSim implements GameSimulator{

    /*
     * Method names of the attributes you want to cluster by. Edit if you want.
     *
     * Put the base stat that's assumed to be good first. (e.g. the higher the number the better)
     * This is what will be the base case for determining the orientation of all other stats.
     */
    String[] baseStats =
    {
        "getNumWins",
        "getReverseSeed"
    };
    
	private String season;
    private ArrayList<Double>[] teamStatNums;
    private String[] statNames;
    private Prototype[] p;
    private int[] baseGoodStats;
    private boolean isGood;
    private double[] statVals;

    @SuppressWarnings("unchecked")
	public AgglomerationSim(Method[] methods, String season) {
        ArrayList<String> TMPStatNames = new ArrayList<String>();
        for(int i = 0; i < methods.length; ++i)
        {
            if(methods[i].getAnnotation(Attribute.class) != null)
            {
                TMPStatNames.add(methods[i].getName());
            }
        }
        statNames = new String[TMPStatNames.size()];
        for(int i = 0; i < statNames.length; ++i)
        {
            statNames[i] = TMPStatNames.get(i);
        }
        
        baseGoodStats = new int[baseStats.length];
        for(int i = 0; i < baseGoodStats.length; ++i)
        {
            baseGoodStats[i] = TMPStatNames.indexOf(baseStats[i]);
        }
        
		this.season = season;
        
        this.teamStatNums = (ArrayList<Double>[])new ArrayList[64];
        for(int i = 0; i < teamStatNums.length; ++i)
        {
            teamStatNums[i] = new ArrayList<Double>(32);
        }
        
        this.p = new Prototype[64];
        
        this.statVals = new double[statNames.length];
	}
        
    public static Double[] normalize(Double[] data)
    {
        Double[] d = new Double[data.length];
        for(int i = 0; i < data.length; ++i)
        {
            d[i] = data[i];
        }
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for(int i = 0; i < d.length; ++i)
        {
            if(d[i] < min)
            {
                min = d[i];
            }
            if(d[i] > max)
            {
                max = d[i];
            }
        }
        max += min;
        for(int i = 0; i < d.length; ++i)
        {
            d[i] += min;
            d[i] /= max;
            d[i] -= .5;
            d[i] *= 2;
        }
        return d;
    }
    
    public class Prototype
    {
        ArrayList<Double[]> data;
        ArrayList<Double[]> normedData;
        ArrayList<Integer> cluster;
        Double[] statVals;
        int id;
        
        /*
         * Indices of base stats that are trivially good and will always be included
         * in the program (e.g. seed, number of wins).
         *
         * The computer needs a very small base set of stats that are declared as good
         * (or bad) so that the bad cluster and the good cluster can be determined.
         * From there, the rest is found out automatically.
         *
         * This may end up just being the number of wins a team has, the 
         * seed, both, or some other combination that results in decent
         * clustering. The computer can't differentiate between
         * higher being better or higher being worse until it's given
         * a base case.
         *
         * The value of agglomerative clustering here is that the chosen base set of stats
         * will never change, so any number of stats could be added to the program
         * and the program will be able to figure out if they're good or bad.
         */
        int[] baseGoodStats;
        
        public Prototype(double[] data, int id, int[] baseGoodStats)
        {
            Double[] dataNew = new Double[data.length];
            for(int i = 0; i < data.length; ++i)
            {
                dataNew[i] = data[i];
            }
            init(dataNew, id, baseGoodStats);
        }
        
        public Prototype(Double[] data, int id, int[] baseGoodStats)
        {
            init(data, id, baseGoodStats);
        }
        
        private void init(Double[] data, int id, int[] baseGoodStats)
        {
            this.baseGoodStats = baseGoodStats;
            this.data = new ArrayList<Double[]>();
            this.data.add(data);
            this.normedData = new ArrayList<Double[]>();
            Double[] normedData = normalize(data);
            this.normedData.add(normedData);
            cluster = new ArrayList<Integer>();
            this.id = id;
            cluster.add(id);
            statVals = new Double[data.length];
            
            // Stats set as good so that the rest can be found out after clustering
            for(int i = 0; i < baseGoodStats.length; ++i)
            {
                statVals[baseGoodStats[i]] = 1.0;
            }
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
        
        /**
         *
         * Illustration:
         *
         * [1 3 2 4 3 5]
         * [2 4 1 5 7 2]
         * [2 4 4 4 4 1]
         *
         * Sum a column, divide by number of values in column, add to array, repeat for all columns, return array.
         */
        public double[] getGoodCenter()
        {
            double[] center = new double[baseGoodStats.length];
            for(int i = 0; i < baseGoodStats.length; ++i)
            {
                double sum = 0.0;
                for(int j = 0; j < data.size(); ++j)
                {
                    sum += data.get(j)[baseGoodStats[i]];
                }
                center[i] = sum/data.size();
            }
            return center;
        }
        
        public Double[] getNormedCenter()
        {
            Double[] center = new Double[data.get(0).length];
            for(int i = 0; i < data.get(0).length; ++i)
            {
                double sum = 0.0;
                for(int j = 0; j < normedData.size(); ++j)
                {
                    sum += normedData.get(j)[i];
                }
                center[i] = sum/data.size();
            }
            return center;
        }
        
        public void isGood(boolean good)
        {
            isGood = good;
        }
        
        public void setStatVal(int i, double val)
        {
            statVals[i] = val;
            //System.out.println(statNames[i] + ": " + val);
        }
        
        public String toString()
        {
            //return Format.formatR(getCenter(),3);
            return null;
        }
        
        public void merge(Prototype p)
        {
            ArrayList<Double[]> newData = p.getData();
            Double[] normedData;
            
            for(int i = 0; i < newData.size(); ++i)
            {
                data.add(newData.get(i));
                normedData = normalize(newData.get(i));
                this.normedData.add(normedData);
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
    
    public void initProtos()
    {
        for(int i = 0; i < p.length; ++i)
        {
            p[i] = new Prototype(
                teamStatNums[i].toArray(
                    new Double[teamStatNums[i].size()]),
                i, baseGoodStats);
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
                    double[] c1 = p[i].getGoodCenter();
                    double[] c2 = p[j].getGoodCenter();
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
        /*for(int i = 0; i < p.length; ++i)
        {
            if(p[i] != null)
            {
                ArrayList<Integer> cluster = p[i].getCluster();
                java.util.Collections.sort(cluster);
                ListUtils.printList(cluster,"Cluster " + (i+1));
                //ArrayUtils.printArrayF(p[i].getCenter(),2);
                System.out.println();
            }
        }*/
        
        
        
        
        // TODO move this to its own function
        // Get how much value each stat has
        // For 2 clusters: The "good" stats will have 1 and
        // the "bad" stats will have -1.
        
        double[] c1 = p[0].getGoodCenter();
        double[] c2 = p[1].getGoodCenter();
        
        //ArrayUtils.printArray(c1, "cluster 1");
        //ArrayUtils.printArray(c2, "cluster 2");
        
        if(c1[0] < c2[0])
        {
            for(int i = 0; i < c1.length; ++i)
            {
                double tmp = c1[i];
                c1[i] = c2[i];
                c2[i] = tmp;
            }
        }
        
        c1 = p[0].getCenter();
        c2 = p[1].getCenter();
        
        for(int i = 0; i < c1.length; ++i)
        {
            if(c1[i] > c2[i])
            {
                p[0].setStatVal(i,1);
                p[1].setStatVal(i,1);
                statVals[i] = 1.0;
            }
            else
            {
                p[0].setStatVal(i,-1);
                p[1].setStatVal(i,-1);
                statVals[i] = -1.0;
            }
        }
    }
    
    public Tuple<Double, Double> simulate(Game game, int round) {
		try {
			Team a = game.getA();
			Team b = game.getB();
			TeamStat tsa = Link.getTeamStat(a, season);
			TeamStat tsb = Link.getTeamStat(b, season);
            
            Double[] c1 = new Double[statNames.length];
            Double[] c2 = new Double[statNames.length];
            
            int stat = 0;
            double aTvalue = 0;
            double bTvalue = 0;
            for (Method m : TeamStat.class.getDeclaredMethods()) {
                if (m.getAnnotation(Attribute.class) != null) {
                    Object aValue = m.invoke(tsa, new Object[] {});
                    Object bValue = m.invoke(tsb, new Object[] {});
                    if (aValue instanceof Integer) {
                        aTvalue = ((Integer) aValue).doubleValue();
                        bTvalue = ((Integer) bValue).doubleValue();
                    } else if (aValue instanceof Double) {
                        aTvalue = ((Double) aValue).doubleValue();
                        bTvalue = ((Double) bValue).doubleValue();
                    }
                    c1[stat] = aTvalue;
                    c2[stat] = bTvalue;
                    stat++;
                }
            }
            normalize(c1, c2);
            double sum = 0.0;
            for(int i = 0; i < c1.length; ++i)
            {
                sum += (c1[i]-c2[i])*statVals[i];
            }
            if(sum > 0)
                return new Tuple<Double, Double>(1d, 0d);
            else return new Tuple<Double, Double>(0d, 1d);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Default returned");
		return new Tuple<Double, Double>(1d, 0d);
	}
    
    /**
     * Normalize given two arrays.
     *
     * Gives a common basis for comparison for the two arrays.
     * Extremely important NOT to normalize them separately.
     */
    public void normalize(Double[] c1, Double[] c2)
    {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        
        for(int i = 0; i < c1.length; ++i)
        {
            if(c1[i] > max)
            {
                max = c1[i];
            }
            if(c1[i] < min)
            {
                min = c1[i];
            }
            if(c2[i] > max)
            {
                max = c2[i];
            }
            if(c2[i] < min)
            {
                min = c2[i];
            }
        }
        
        max += min;
        for(int i = 0; i < c1.length; ++i)
        {
            c1[i] += min;
            c1[i] /= max;
            c1[i] -= .5;
            c1[i] *= 2;
            
            c2[i] += min;
            c2[i] /= max;
            c2[i] -= .5;
            c2[i] *= 2;
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
