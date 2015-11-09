package one_MIS_P;

import one_MIS_P.one_MIS_P_main;
import java.util.Random;

public class Agent {
	//メンバ
	public boolean MIS;					//id
	public int timer;			//独立ノードを増やすためのタイマ
	
	public Agent () {	//状態を初期化
		Random random = new Random();
		
		this.MIS = random.nextBoolean();
		//Inを増やすためのタイマを初期化
		this.timer = random.nextInt(one_MIS_P_main.t_max);
	}
	
	//メソッド
	public boolean IsIndependentNode () {		//自身が独立ノードであるかを表すメソッド
		return MIS ? true : false;
	}
}
