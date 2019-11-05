package ch07.ex02;

/**
 * すべての組み合わせを実施する
 * @author Sato Kodai
 *byteの最小値=-128
*byteの最大値=127
*shortの最小値=-32768
*shortの最大値=32767
*longの最小値=-9223372036854775808
*longの最大値=9223372036854775807
*float:-3.4E38～+3.4E38
*double:-1.7E308～+1.7E308
**
*
 */
public class DataCheck {
	boolean bool;
	char c;
	byte b;
	short s;
	float f;
	double d;
	long l;
	int i;

	public void check() {
		//i = 3.5f;//コンパイルエラー
		//i = 1L;//コンパイルエラー
		//f = 1.7E308F;//範囲外
		b = 127;
		//b = 128;//範囲外
		l = 9223372036854775807L;
		//l = 9223372036854775808L; //範囲外
		//s = 32768;//範囲外

	}

}
