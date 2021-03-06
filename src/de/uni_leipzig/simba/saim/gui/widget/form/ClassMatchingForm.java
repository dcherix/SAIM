package de.uni_leipzig.simba.saim.gui.widget.form;

import com.hp.hpl.jena.rdf.model.Model;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import de.konrad.commons.sparql.PrefixHelper;
import de.uni_leipzig.simba.io.KBInfo;
import de.uni_leipzig.simba.saim.gui.widget.form.ClassChooser.ClassNode;

/** The class matching consists of two sets of classes from both knowledge bases and a match between them.
 * In RAVEN, the mapping is injective and uniquely defined (there is at most one match for a class).
 * The user gets shown some classes and can add some manually or create the matching manually.*/
public class ClassMatchingForm extends Form
{
	/**
	 */
	private static final long serialVersionUID = -7897392568006310314L;
	protected ComboBox field;
//	protected final TextField target;
	protected final ClassChooser chooser;
	KBInfo info;

	public ClassMatchingForm(String caption, final KBInfo info, Model model) {
		this.info = info;
		Layout layout = new VerticalLayout();
		layout.setWidth("90%"); //$NON-NLS-1$
		setLayout(layout);
		field = new ComboBox(caption);
		field.setWidth("100%"); //$NON-NLS-1$
		field.setTextInputAllowed(true);
		field.setInvalidAllowed(true);
		field.setNewItemsAllowed(true);
		field.setImmediate(true);
		setDefault(info);
		chooser = new ClassChooser(info.endpoint, info.id, info.graph, model);

		chooser.tree.addListener(new ItemClickListener() {
			/**
			 */
			private static final long serialVersionUID = -4408783618595964107L;

			@Override
			public void itemClick(ItemClickEvent event) {
				field.setValue(event.getItemId());
				ClassNode node = (ClassNode) event.getItemId();
				field.addItem(node.url);
				field.setValue(node.url);
				String pref = PrefixHelper.getURI(PrefixHelper.getBase(PrefixHelper.abbreviate(node.url)));
				String rest = info.var +" rdf:type "+ PrefixHelper.abbreviate(node.url); //$NON-NLS-1$
				info.restrictions.clear();
				if(node.url != null) {
					info.restrictions.add(rest);
					if(pref != null)
						info.prefixes.put(PrefixHelper.getBase(PrefixHelper.abbreviate(node.url)), PrefixHelper.getURI(PrefixHelper.getBase(PrefixHelper.abbreviate(node.url))));
				}
				else {
					System.out.println("Class Matching Form:: Cannot set class restiction, due to  url=null aborting");
				}
			}
		});
		addField("textfield", field); //$NON-NLS-1$
		this.getLayout().addComponent(chooser);
	}

	/**
	 * Method to add and select a value in the field.
	 * @param uri
	 */
	public void addItem(Object uri, boolean select)
	{
		if(!field.containsId(uri)) {
			field.addItem(uri);
		}
		if(select) {field.select(uri);}
	}

	/**
	 * Tries to set default values by reading the KBInfo.
	 * @param info
	 */
	private void setDefault(KBInfo info) {
		if(info.restrictions.size()>0) {
			for(String rest : info.restrictions) {
				if(rest.indexOf("rdf:type")>0) {
					String className = rest.substring(rest.indexOf("rdf:type")+9).trim();
					addItem(className, true);
				}
			}
		}
	}
}
