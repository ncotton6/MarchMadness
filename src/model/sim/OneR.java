package model.sim;

import java.lang.reflect.Method;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.Tuple;
import model.data.Team;
import model.data.TeamStat;

/**
 * This {@link GameSimulator} will use the 1R rule to determine which team
 * should win between to given teams.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class OneR implements GameSimulator {

	private String season;
	private Method oneRValue;

	public OneR() {
	}

	public OneR(String season, Method oneRValue) {
		this.setSeason(season);
		this.setOneRValue(oneRValue);
	}

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		try {
			Team a = game.getA();
			Team b = game.getB();
			TeamStat tsa = Link.getTeamStat(a, season);
			TeamStat tsb = Link.getTeamStat(b, season);
			// TODO possible make this more generic, not have double be the
			// return type.
			double aTvalue = 0;
			double bTvalue = 0;
			Object aValue = oneRValue.invoke(tsa, new Object[] {});
			Object bValue = oneRValue.invoke(tsb, new Object[] {});
			if (aValue instanceof Integer) {
				aTvalue = ((Integer) aValue).doubleValue();
				bTvalue = ((Integer) bValue).doubleValue();
			} else if (aValue instanceof Double) {
				aTvalue = ((Double) aValue).doubleValue();
				bTvalue = ((Double) bValue).doubleValue();
			}
			//System.out.println(aTvalue + " " + bTvalue + " " + oneRValue.getName());
			return new Tuple<Double, Double>(aTvalue, bTvalue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Default returned");
		return new Tuple<Double, Double>(1d, 0d);
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Method getOneRValue() {
		return oneRValue;
	}

	public void setOneRValue(Method oneRValue) {
		this.oneRValue = oneRValue;
	}

}
