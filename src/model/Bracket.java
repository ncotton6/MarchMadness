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
	 * This
	 * 
	 * @param midwest
	 *            should be of length 16 of the teams in the midwest
	 * @param west
	 *            should be of length 16 of the teams in the west
	 * @param east
	 *            should be of length 16 of the teams in the east
	 * @param south
	 *            should be of lenght 16 of the teams in the south
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
			game.setA(Link.lookupTeam(1));
			game.setB(Link.lookupTeam(1));
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
