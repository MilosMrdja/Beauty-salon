package entiteti;

public class TipTretmana {
	private int idTip;
	private String imeTip;
	
	public int getIdTip() {
		return idTip;
	}
	
	public TipTretmana(int idTip, String imeTip) {
		super();
		this.idTip = idTip;
		this.imeTip = imeTip;
	}
	
	public void setIdTip(int idTip) {
		this.idTip = idTip;
	}
	public String getImeTip() {
		return imeTip;
	}
	public void setImeTip(String imeTip) {
		this.imeTip = imeTip;
	}
	
	@Override
	public String toString() {
		return "TipTretmana [idTip=" + idTip + ", imeTip=" + imeTip + "]";
	}
	public String toFileString() {
		return this.idTip + "," + this.imeTip +",";
	}
	
} 
