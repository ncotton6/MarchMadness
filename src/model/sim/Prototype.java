package model.sim;

import java.util.ArrayList;

public class Prototype {
	ArrayList<Integer[]> data;
	ArrayList<Integer> cluster;
	int id;

	public Prototype(int[] data, int id) {
		this.data = new ArrayList<Integer[]>();
		Integer[] dataNew = new Integer[data.length];
		for (int i = 0; i < data.length; ++i) {
			dataNew[i] = data[i];
		}
		this.data.add(dataNew);
		cluster = new ArrayList<Integer>();
		this.id = id;
		cluster.add(id);
	}

	public ArrayList<Integer> getCluster() {
		return cluster;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Integer[]> getData() {
		return data;
	}

	public double[] getCenter() {
		return null;// Dist.center(data);
	}

	public String toString() {
		// return Format.formatR(getCenter(),3);
		return null;
	}

	public void merge(Prototype p) {
		ArrayList<Integer[]> newData = p.getData();

		for (int i = 0; i < newData.size(); ++i) {
			data.add(newData.get(i));
		}
		ArrayList<Integer> old = p.getCluster();
		for (int i = 0; i < old.size(); ++i) {
			cluster.add(old.get(i));
		}
	}
}