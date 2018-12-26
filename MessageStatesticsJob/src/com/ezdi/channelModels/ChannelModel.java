package com.ezdi.channelModels;

import java.io.Serializable;

public class ChannelModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ChannelName;
	public String channelstatastiscsString;
	public String[] channeltraficstatatics;

	public String getChannelName() {
		return ChannelName;
	}

	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}

	public String[] getChanneltraficstatatics() {
		return channeltraficstatatics;
	}

	public void setChanneltraficstatatics(String[] channeltraficstatatics) {
		this.channeltraficstatatics = channeltraficstatatics;
	}

	public String getChannelstatastiscsString() {
		return channelstatastiscsString;
	}

	public void setChannelstatastiscsString(String channelstatastiscsString) {
		this.channelstatastiscsString = channelstatastiscsString;
	}

}
