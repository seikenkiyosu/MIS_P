package one_MIS_P;

import one_MIS_P.one_MIS_P_main;

public class Interaction {
	public static void interaction(Agent x, Agent y){
		//独立ノード同士が交流したら，一方が独立ノードでなくなる
		if (x.IsIndependentNode() && y.IsIndependentNode()) {
			y.MIS = false;
		}
		
		//独立ノードは交流時に「自他の」タイマを最大値に設定
		if (x.IsIndependentNode() || y.IsIndependentNode()) { x.timer = y.timer = one_MIS_P_main.t_max; }
		
		//非独立ノードが交流するとタイマは(大きい方－１)の値となる
		if (!y.IsIndependentNode()) x.timer = max(x.timer-1, 0);
		if (!x.IsIndependentNode()) y.timer = max(y.timer-1, 0);
		
		//タイマが０になると独立ノードになる
		if (x.timer == 0) { x.MIS = true; }
		if (y.timer == 0) { y.MIS = true; }
	}
	
	
	
	
	
	
	//交流に用いるメソッド
	private static int max (int a, int b) {
		return a >= b ? a : b;
	}
	private static int max (int a, int b, int c) {
		return max (a, b) > max(b, c) ? max(a, b) : max(b, c); 
	}
	private static int min (int a, int b) {
		return a < b ? a : b;
	}
}
