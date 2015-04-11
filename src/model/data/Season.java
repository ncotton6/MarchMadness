package model.data;

import datareader.CSVValue;

public class Season {

	private String season, wloc;
	private int daynum, wteam, wscore, lteam, lscore, numot, wfgm, wfga, wfgm3,
			wfga3, wftm, wfta, wor, wdr, wast, wto, wstl, wblk, wpf, lfgm,
			lfga, lfgm3, lfga3, lftm, lfta, lor, ldr, last, lto, lstl, lblk,
			lpf;;

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	@CSVValue(ColumnName = "SEASON", ColumnType = String.class)
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the wloc
	 */
	public String getWloc() {
		return wloc;
	}

	/**
	 * @param wloc
	 *            the wloc to set
	 */
	@CSVValue(ColumnName = "WLOC", ColumnType = String.class)
	public void setWloc(String wloc) {
		this.wloc = wloc;
	}

	/**
	 * @return the daynum
	 */
	public int getDaynum() {
		return daynum;
	}

	/**
	 * @param daynum
	 *            the daynum to set
	 */
	@CSVValue(ColumnName = "DAYNUM", ColumnType = Integer.class)
	public void setDaynum(int daynum) {
		this.daynum = daynum;
	}

	/**
	 * @return the wteam
	 */
	public int getWteam() {
		return wteam;
	}

	/**
	 * @param wteam
	 *            the wteam to set
	 */
	@CSVValue(ColumnName = "WTEAM", ColumnType = Integer.class)
	public void setWteam(int wteam) {
		this.wteam = wteam;
	}

	/**
	 * @return the wscore
	 */
	public int getWscore() {
		return wscore;
	}

	/**
	 * @param wscore
	 *            the wscore to set
	 */
	@CSVValue(ColumnName = "WSCORE", ColumnType = Integer.class)
	public void setWscore(int wscore) {
		this.wscore = wscore;
	}

	/**
	 * @return the lteam
	 */
	public int getLteam() {
		return lteam;
	}

	/**
	 * @param lteam
	 *            the lteam to set
	 */
	@CSVValue(ColumnName = "LTEAM", ColumnType = Integer.class)
	public void setLteam(int lteam) {
		this.lteam = lteam;
	}

	/**
	 * @return the lscore
	 */
	public int getLscore() {
		return lscore;
	}

	/**
	 * @param lscore
	 *            the lscore to set
	 */
	@CSVValue(ColumnName = "LSCORE", ColumnType = Integer.class)
	public void setLscore(int lscore) {
		this.lscore = lscore;
	}

	/**
	 * @return the numot
	 */
	public int getNumot() {
		return numot;
	}

	/**
	 * @param numot
	 *            the numot to set
	 */
	public void setNumot(int numot) {
		this.numot = numot;
	}

	@CSVValue(ColumnName = "NUMOT", ColumnType = String.class)
	public void setNumot(String numot) {
		this.numot = numot.equalsIgnoreCase("NA") ? 0 : new Integer(numot);
	}

	/**
	 * @return the wfgm
	 */
	public int getWfgm() {
		return wfgm;
	}

	/**
	 * @param wfgm
	 *            the wfgm to set
	 */
	@CSVValue(ColumnName = "WFGM", ColumnType = Integer.class)
	public void setWfgm(int wfgm) {
		this.wfgm = wfgm;
	}

	/**
	 * @return the wfga
	 */
	public int getWfga() {
		return wfga;
	}

	/**
	 * @param wfga
	 *            the wfga to set
	 */
	@CSVValue(ColumnName = "WFGA", ColumnType = Integer.class)
	public void setWfga(int wfga) {
		this.wfga = wfga;
	}

	/**
	 * @return the wfgm3
	 */
	public int getWfgm3() {
		return wfgm3;
	}

	/**
	 * @param wfgm3
	 *            the wfgm3 to set
	 */
	@CSVValue(ColumnName = "WFGM3", ColumnType = Integer.class)
	public void setWfgm3(int wfgm3) {
		this.wfgm3 = wfgm3;
	}

	/**
	 * @return the wfga3
	 */
	public int getWfga3() {
		return wfga3;
	}

	/**
	 * @param wfga3
	 *            the wfga3 to set
	 */
	@CSVValue(ColumnName = "WFGA3", ColumnType = Integer.class)
	public void setWfga3(int wfga3) {
		this.wfga3 = wfga3;
	}

	/**
	 * @return the wftm
	 */
	public int getWftm() {
		return wftm;
	}

	/**
	 * @param wftm
	 *            the wftm to set
	 */
	@CSVValue(ColumnName = "WFTM", ColumnType = Integer.class)
	public void setWftm(int wftm) {
		this.wftm = wftm;
	}

	/**
	 * @return the wfta
	 */
	public int getWfta() {
		return wfta;
	}

	/**
	 * @param wfta
	 *            the wfta to set
	 */
	@CSVValue(ColumnName = "WFTA", ColumnType = Integer.class)
	public void setWfta(int wfta) {
		this.wfta = wfta;
	}

	/**
	 * @return the wor
	 */
	public int getWor() {
		return wor;
	}

	/**
	 * @param wor
	 *            the wor to set
	 */
	@CSVValue(ColumnName = "WOR", ColumnType = Integer.class)
	public void setWor(int wor) {
		this.wor = wor;
	}

	/**
	 * @return the wdr
	 */
	public int getWdr() {
		return wdr;
	}

	/**
	 * @param wdr
	 *            the wdr to set
	 */
	@CSVValue(ColumnName = "WDR", ColumnType = Integer.class)
	public void setWdr(int wdr) {
		this.wdr = wdr;
	}

	/**
	 * @return the wast
	 */
	public int getWast() {
		return wast;
	}

	/**
	 * @param wast
	 *            the wast to set
	 */
	@CSVValue(ColumnName = "WAST", ColumnType = Integer.class)
	public void setWast(int wast) {
		this.wast = wast;
	}

	/**
	 * @return the wto
	 */
	public int getWto() {
		return wto;
	}

	/**
	 * @param wto
	 *            the wto to set
	 */
	@CSVValue(ColumnName = "WTO", ColumnType = Integer.class)
	public void setWto(int wto) {
		this.wto = wto;
	}

	/**
	 * @return the wstl
	 */
	public int getWstl() {
		return wstl;
	}

	/**
	 * @param wstl
	 *            the wstl to set
	 */
	@CSVValue(ColumnName = "WSTL", ColumnType = Integer.class)
	public void setWstl(int wstl) {
		this.wstl = wstl;
	}

	/**
	 * @return the wblk
	 */
	public int getWblk() {
		return wblk;
	}

	/**
	 * @param wblk
	 *            the wblk to set
	 */
	@CSVValue(ColumnName = "WBLK", ColumnType = Integer.class)
	public void setWblk(int wblk) {
		this.wblk = wblk;
	}

	/**
	 * @return the wpf
	 */
	public int getWpf() {
		return wpf;
	}

	/**
	 * @param wpf
	 *            the wpf to set
	 */
	@CSVValue(ColumnName = "WPF", ColumnType = Integer.class)
	public void setWpf(int wpf) {
		this.wpf = wpf;
	}

	/**
	 * @return the lfgm
	 */
	public int getLfgm() {
		return lfgm;
	}

	/**
	 * @param lfgm
	 *            the lfgm to set
	 */
	@CSVValue(ColumnName = "LFGM", ColumnType = Integer.class)
	public void setLfgm(int lfgm) {
		this.lfgm = lfgm;
	}

	/**
	 * @return the lfga
	 */
	public int getLfga() {
		return lfga;
	}

	/**
	 * @param lfga
	 *            the lfga to set
	 */
	@CSVValue(ColumnName = "LFGA", ColumnType = Integer.class)
	public void setLfga(int lfga) {
		this.lfga = lfga;
	}

	/**
	 * @return the lfgm3
	 */
	public int getLfgm3() {
		return lfgm3;
	}

	/**
	 * @param lfgm3
	 *            the lfgm3 to set
	 */
	@CSVValue(ColumnName = "LFGM3", ColumnType = Integer.class)
	public void setLfgm3(int lfgm3) {
		this.lfgm3 = lfgm3;
	}

	/**
	 * @return the lfga3
	 */
	public int getLfga3() {
		return lfga3;
	}

	/**
	 * @param lfga3
	 *            the lfga3 to set
	 */
	@CSVValue(ColumnName = "LFGA3", ColumnType = Integer.class)
	public void setLfga3(int lfga3) {
		this.lfga3 = lfga3;
	}

	/**
	 * @return the lftm
	 */
	public int getLftm() {
		return lftm;
	}

	/**
	 * @param lftm
	 *            the lftm to set
	 */
	@CSVValue(ColumnName = "LFTM", ColumnType = Integer.class)
	public void setLftm(int lftm) {
		this.lftm = lftm;
	}

	/**
	 * @return the lfta
	 */
	public int getLfta() {
		return lfta;
	}

	/**
	 * @param lfta
	 *            the lfta to set
	 */
	@CSVValue(ColumnName = "LFTA", ColumnType = Integer.class)
	public void setLfta(int lfta) {
		this.lfta = lfta;
	}

	/**
	 * @return the lor
	 */
	public int getLor() {
		return lor;
	}

	/**
	 * @param lor
	 *            the lor to set
	 */
	@CSVValue(ColumnName = "LOR", ColumnType = Integer.class)
	public void setLor(int lor) {
		this.lor = lor;
	}

	/**
	 * @return the ldr
	 */
	public int getLdr() {
		return ldr;
	}

	/**
	 * @param ldr
	 *            the ldr to set
	 */
	@CSVValue(ColumnName = "LDR", ColumnType = Integer.class)
	public void setLdr(int ldr) {
		this.ldr = ldr;
	}

	/**
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	@CSVValue(ColumnName = "LAST", ColumnType = Integer.class)
	public void setLast(int last) {
		this.last = last;
	}

	/**
	 * @return the lto
	 */
	public int getLto() {
		return lto;
	}

	/**
	 * @param lto
	 *            the lto to set
	 */
	@CSVValue(ColumnName = "LTO", ColumnType = Integer.class)
	public void setLto(int lto) {
		this.lto = lto;
	}

	/**
	 * @return the lstl
	 */
	public int getLstl() {
		return lstl;
	}

	/**
	 * @param lstl
	 *            the lstl to set
	 */
	@CSVValue(ColumnName = "LSTL", ColumnType = Integer.class)
	public void setLstl(int lstl) {
		this.lstl = lstl;
	}

	/**
	 * @return the lblk
	 */
	public int getLblk() {
		return lblk;
	}

	/**
	 * @param lblk
	 *            the lblk to set
	 */
	@CSVValue(ColumnName = "LBLK", ColumnType = Integer.class)
	public void setLblk(int lblk) {
		this.lblk = lblk;
	}

	/**
	 * @return the lpf
	 */
	public int getLpf() {
		return lpf;
	}

	/**
	 * @param lpf
	 *            the lpf to set
	 */
	@CSVValue(ColumnName = "LPF", ColumnType = Integer.class)
	public void setLpf(int lpf) {
		this.lpf = lpf;
	}

}
