package org.vaadin.cytographer.widgetset.client.ui.shap;


import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

/**
 * 
 * @author rspeck
 *
 */
public class VDiamond extends Path {
	
	public VDiamond(int x, int y, int size,String color) {
		super(x,y);
		moveRelativelyTo(-size,0);
		lineRelativelyTo(size, size);
		lineRelativelyTo(size, -size);
		lineRelativelyTo(-size, -size);
		close();
		this.setFillColor(color); 
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Path.class;
	}
}