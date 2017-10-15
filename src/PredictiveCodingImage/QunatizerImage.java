package PredictiveCodingImage;

public class QunatizerImage {
	public int level;
	public int start;
	public int end;
	public int average=0;
	
	public QunatizerImage(int level ,int start,int end) {
		this.level=level;
		this.start=start;
		this.end=end;
		average=(start+end)/2;
		
	}

}
