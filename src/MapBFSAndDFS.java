import java.util.LinkedList;

/**
 * 图的深度优先算法和广度优先算法 应用示例
 * 
 * @author cuilb
 * 
 */

public class MapBFSAndDFS {
	public static class Node {
		public int x;
		public int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 10*10小岛分布情况,0为海,数字为岛陆地的相对海高度
	public static int[][] a = { { 1, 2, 1, 0, 0, 0, 0, 0, 2, 3 },
			{ 3, 0, 2, 0, 1, 2, 1, 0, 1, 2 }, { 4, 0, 1, 0, 1, 2, 3, 2, 0, 1 },
			{ 3, 2, 0, 0, 0, 1, 2, 4, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 5, 3, 0 },
			{ 0, 1, 2, 1, 0, 1, 5, 4, 3, 0 }, { 0, 1, 2, 3, 1, 3, 6, 2, 1, 0 },
			{ 0, 0, 3, 4, 8, 9, 7, 5, 0, 0 }, { 0, 0, 0, 3, 7, 8, 6, 0, 1, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } };

	/**
	 * 方向数组
	 */
	public static int[][] next = { { 0, 1 },// 向右
			{ 1, 0 },// 向下
			{ 0, -1 },// 向左
			{ -1, 0 } // 向上
	};
	public static int sum = 0;

	/**
	 * 广度优先算法,计算起始点为6, 8时整个岛屿的面积
	 */
	public static void BFS() {
		int[][] book = new int[10][10];// 记录查询过的点数组
		sum = 0;
		LinkedList<Node> nodeList = new LinkedList();

		int tx, ty;
		int startX = 6, startY = 8;

		nodeList.offer(new Node(startX, startY));
		addMark(startX, startY, book);
		while (nodeList.size() > 0) {
			Node head = nodeList.pop();
			for (int i = 0; i < 4; i++) {
				tx = head.x + next[i][0];
				ty = head.y + next[i][1];

				if (tx < 1 || ty < 1 || tx > 9 || ty > 9) {
					continue;
				}

				if (a[tx][ty] > 0 && book[tx][ty] == 0) {
					nodeList.offer(new Node(tx, ty));
					addMark(tx, ty, book);
				}
			}
		}
		System.out.println("广度优先算法:" + sum);
	}

	/**
	 * 积数加一,同时查询过的节点进行标记
	 * 
	 * @param x
	 * @param y
	 */
	public static void addMark(int x, int y, int[][] book) {
		sum++;
		book[x][y] = 1;
	}

	/**
	 * 深度优先算法
	 */
	public static void DFS() {
		int[][] book = new int[10][10];// 记录查询过的点数组
		sum = 0;
		dfs(book, 6, 8);
		System.out.println("深度优先算法:" + sum);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(book[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void dfs(int[][] book, int x, int y) {
		int tx, ty;
		for (int i = 0; i < 4; i++) {
			tx = x + next[i][0];
			ty = y + next[i][1];

			if (tx < 1 || ty < 1 || tx > 9 || ty > 9) {
				continue;
			}

			if (a[tx][ty] > 0 && book[tx][ty] == 0) {
				addMark(tx, ty, book);
				dfs(book, tx, ty);
			}
		}

		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BFS();
		DFS();
	}
}
