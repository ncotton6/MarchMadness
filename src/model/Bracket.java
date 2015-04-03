package model;

/**
 * The {@link Bracket} class will be used to hold the structure of the games,
 * and teams progression throughout the tournament.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class Bracket {

	private Node winner = null;

	/**
	 * This method will build up the bracket from the base level.
	 * 
	 * @param midwest
	 *            should be of length 16 of the teams in the midwest
	 * @param west
	 *            should be of length 16 of the teams in the west
	 * @param east
	 *            should be of length 16 of the teams in the east
	 * @param south
	 *            should be of length 16 of the teams in the south
	 */
	public Bracket(int[] midwest, int[] west, int[] east, int[] south) {
		if (midwest.length != 16 && west.length != 16 && east.length != 16
				&& south.length != 16)
			throw new RuntimeException(
					"All sides of the bracket need to be filled out");
		Node[] midwestNodes = generateBase(midwest);
		Node[] westNodes = generateBase(west);
		Node[] eastNodes = generateBase(east);
		Node[] southNodes = generateBase(south);
		Node[] heads = new Node[4];
		heads[0] = build(midwestNodes);
		heads[3] = build(westNodes);
		heads[1] = build(eastNodes);
		heads[2] = build(southNodes);
		winner = build(heads);

	}

	/**
	 * Connects nodes together, and builds up the bracket from the bottom.
	 * 
	 * @param nodes
	 * @return
	 */
	private Node build(Node[] nodes) {
		while (nodes.length > 1) {
			Node[] uplevel = new Node[nodes.length / 2];
			for (int i = 0; i < uplevel.length; ++i) {
				uplevel[i] = new Node(new Game());
				uplevel[i].getChildren()[0] = nodes[i];
				uplevel[i].getChildren()[1] = nodes[nodes.length - i];
			}
			nodes = uplevel;
			if (nodes.length == 1)
				return nodes[0];
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	/**
	 * @return the winner
	 */
	public Node getWinner() {
		return winner;
	}

	/**
	 * @param winner
	 *            the winner to set
	 */
	public void setWinner(Node winner) {
		this.winner = winner;
	}

	/**
	 * This method will generate the base game level of the bracket from the
	 * teams in the region. The order of teams in this array is important,
	 * because that will determine what seed they are.
	 * 
	 * @param teams
	 * @return
	 */
	private Node[] generateBase(int[] teams) {
		Node[] games = new Node[8];
		for (int i = 0; i < games.length; ++i) {
			Game game = new Game();
			game.setA(Link.lookupTeam(teams[i]));
			game.setB(Link.lookupTeam(teams[16 - i]));
			Node n = new Node(game);
			games[i] = n;
		}
		return games;
	}

	/**
	 * This simply class will be used to hold data for progression through the
	 * tournament.
	 * 
	 * @author Nathaniel Cotton
	 * 
	 */
	public class Node {

		private Game game;
		private Node[] children = new Node[2];

		public Node(Game game) {
			this.game = game;
		}

		/**
		 * @return the game
		 */
		public Game getGame() {
			return game;
		}

		/**
		 * @param game
		 *            the game to set
		 */
		public void setGame(Game game) {
			this.game = game;
		}

		/**
		 * @return the children
		 */
		public Node[] getChildren() {
			return children;
		}

		/**
		 * @param children
		 *            the children to set
		 */
		public void setChildren(Node[] children) {
			this.children = children;
		}

	}
}
