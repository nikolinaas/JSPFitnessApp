package org.unibl.etf.ip.beans;

import org.unibl.etf.ip.dao.PorukaDAO;

public class PorukaBean {
 
	public boolean MarkMessageAsRead(int id) {
		return PorukaDAO.markMessageAsRead(id);
	}
	
}
