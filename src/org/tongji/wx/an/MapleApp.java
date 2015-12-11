package org.tongji.wx.an;

public class MapleApp {
	
	private String getAttribute(Packet p, String field){
		return null;
	}
	
	private String switchTable(String mac){
		return null;
	}
	
	@Multitable(Match="SrcMac", Output="SrcSw", Table = "Table1")
	public String switchTableForSrc(String mac){
		return switchTable(mac);
	}
	
	@Multitable(Match="DstMac", Output="DstSw", Table = "Table2")
	public String switchTableForDst(String mac){
		return switchTable(mac);
	}
	
	@Multitable(Match={"SrcSw", "DstSw"}, Output="Route", Table = "Table3")
	private Route computeRoute(String srcSw, String dstSw){
		return null;
	}
	
	private void setRoute(Route r){
		
	}
	
	public void onPacketIn(Packet p){
		String srcMac = getAttribute(p, "SrcMac");
		String dstMac = getAttribute(p, "DstMac");
		
		String srcSw = switchTableForSrc(srcMac);
		String dstSw = switchTableForDst(dstMac);
		
		Route route = computeRoute(srcSw, dstSw);
		
		// return route;
		setRoute(route);
	}

}
