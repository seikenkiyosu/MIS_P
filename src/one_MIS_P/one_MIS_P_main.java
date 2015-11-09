package one_MIS_P;

import java.util.Random;

public class one_MIS_P_main {
	public static final boolean LookData = false;

	//ネットワーク
	public static final int METHOD = Graph.GRID;
	
	//個体数
	public static final int n = 400;
	
	//タイマを決めるための定数
	public static final int N = 420;		//個体数上限
	
	public static final int DELTA = 4;		//最大次数の上限
	public static final int t_max = N*DELTA;
	
	public static void main (String args[]) {
		//初期化
		Graph graph = new Graph(n, METHOD);
		Agent[] agent = new Agent[n];
		for (int i = 0; i < n; i++) agent[i] = new Agent();
		
		//前の状況を保存しておく作業用変数
		boolean lastconfiguration[][] = new boolean[(int )Math.sqrt(n)][(int )Math.sqrt(n)];
		lastconfiguration = new boolean[(int )Math.sqrt(n)][];
		for (int i = 0; i < (int )Math.sqrt(n); i++) { lastconfiguration[i] = new boolean[(int )Math.sqrt(n)]; }
	
		while (true) {
			if (!LookData) {
				if (!LastOneIsSameConfiguration(lastconfiguration, agent)) {
					ShowConfiguration(agent);
				}
			}
			else {
				ShowConfiguration(agent);
			}
			
			//initiatorとresponderを決める
			Random R = new Random();
			int initiator = -1, responder = -1;
			initiator = R.nextInt(n);
			while (true) { 
				responder = R.nextInt(n);
				if (graph.List[initiator][responder]) break;
			}
			//交流
			Interaction.interaction(agent[initiator], agent[responder]);
		}
		
	}
	
	private static void ShowConfiguration (Agent agent[]) {
		for (int i = 0; i < (int )Math.sqrt(n); i++) {
			if (!LookData) {
				for (int j = 0; j < (int )Math.sqrt(n); j++) {
					if (agent[i*(int )Math.sqrt(n)+j].IsIndependentNode()) System.out.print("■ ");
					else System.out.print("□ ");
				}
			}
			else {
				for (int j = 0; j < (int )Math.sqrt(n); j++) {
					if (agent[i*(int )Math.sqrt(n)+j].IsIndependentNode()) System.out.print(" ■");
					else System.out.print(" □");
					System.out.printf("%4d\t", agent[i*(int )Math.sqrt(n)+j].timer);
				}
			}
			System.out.print("\n");
//			if(i!=(int )Math.sqrt(n)-1) { for (int j = 0; j < (int )Math.sqrt(n); j++) System.out.print("｜\t\t"); System.out.print("\n");}
		}
		for (int i = 0; i < (int )Math.sqrt(n); i++) {
			System.out.print("--");
		}
		System.out.print("\n");
	}
	
	private static boolean LastOneIsSameConfiguration (boolean lastone[][], Agent agent[]) {
		boolean lastoneissame = true;
		//前の状況から変化があったかどうか
		for (int i = 0; i < (int )Math.sqrt(n); i++) 
			for (int j = 0; j < (int )Math.sqrt(n); j++) 
				if (lastone[i][j] != agent[i*(int )Math.sqrt(n)+j].IsIndependentNode()) {
					lastoneissame = false;			
				}
		//last one 更新
		for (int i = 0; i < (int )Math.sqrt(n); i++)
			for (int j = 0; j < (int )Math.sqrt(n); j++) {
				lastone[i][j] = agent[i*(int )Math.sqrt(n)+j].IsIndependentNode();
			}
		return lastoneissame ? true : false;
	}
}
