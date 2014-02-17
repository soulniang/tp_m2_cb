package org.paumard.controler;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.paumard.model.Marin;

@SuppressWarnings("serial")
@Named("marinControler")
@SessionScoped
public class MarinControler implements Serializable {
	
	private Marin marin = new Marin() ;

	public void create() {
		System.out.println(marin) ;
	}
	
	public Marin getMarin() {
		return marin;
	}

	public void setMarin(Marin marin) {
		this.marin = marin;
	}
}
