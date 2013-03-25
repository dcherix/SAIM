package de.uni_leipzig.simba.saim;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Video;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public class LandingPage extends CustomComponent {
	public static final String manual = "manual.pdf";
//	public static final String videoFile1 = "screencast/saim_screencast.mp4";
//	public static final String videoFile2 = "screencast/saim_screencast.ogg";
//	public static final String videoFile3 = "screencast/saim_screencast.webm";
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private NativeButton startButton;
	@AutoGenerated
	private Label LandingLabelText;
	SAIMApplication app;
	
	
	public LandingPage(SAIMApplication app) {
		this.app = app;
		buildVerticalLayout_1();
		setCompositionRoot(verticalLayout_1);
	}


	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setMargin(true);
		verticalLayout_1.setSpacing(true);
		
		
		// LandingLabelText
		LandingLabelText = new Label();
		LandingLabelText.setImmediate(false);
		LandingLabelText.setWidth("-1px");
		LandingLabelText.setHeight("-1px");
		LandingLabelText.setValue(getText());
		LandingLabelText.setContentMode(3);
		verticalLayout_1.addComponent(LandingLabelText);
		
		// startButton
		startButton = new NativeButton();
		startButton.setCaption("Start SAIM");
		startButton.setImmediate(false);
		startButton.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				app.startSAIM();
			}
		});
		startButton.setWidth("-1px");
		startButton.setHeight("-1px");
		verticalLayout_1.addComponent(startButton);
		verticalLayout_1.addComponent(getManualLabel());
		verticalLayout_1.addComponent(getManualLink());
		verticalLayout_1.addComponent(getVideoLabel());
		
		verticalLayout_1.addComponent(getVideo());
		
		return verticalLayout_1;
	}
	
	private String getText() {
		String s = "<h1>SAIM Instance Matching Application</h1>" +
				"SAIM is an web interface for the <a href='http://aksw.org/Projects/LIMES.html'>LIMES</a> linking framework.</br>" +
				"There will be a manual available soon. Until then please check our screencast instead.<br>" +
				"SAIM is a prototype developed by the <a href='http://aksw.org/'>AKSW Research group at the University of Leipzig</a>." +
//				"<h1>Team</h1>" +
//				"<ul>" +
//				" <li><a href='http://aksw.org/AxelNgonga.html'>Dr. Axel-C. Ngonga Ngomo</a></li>" +
//				" <li><a href='http://aksw.org/KonradHoeffner.html'>Konrad Höffner</a></li>" +
//				" <li>René Speck</li>" +
//				" <li>Klaus Lyko</li>" +
//				"</ul>" +
				"<h1>Start SAIM</h1>" +
				"To go ahead and start SAIM click the button:";		
		return s;
	}

	private Video getVideo() {
//		URL url;
//		url = getClass().getClassLoader().getResource(videoFile1);	
//		url = getClass().getClassLoader().getResource(videoFile2);
		
		ExternalResource resc1 = new ExternalResource("http://139.18.2.164:8080/saim_screencast.mp4");
		resc1.setMIMEType("video/mp4; codecs=avc1.42E01E,mp4a.40.2");

		
		ExternalResource resc2 = new ExternalResource("http://139.18.2.164:8080/saim_screencast.ogg");
		resc2.setMIMEType("video/ogg; codecs=theora,vorbis");
		
		ExternalResource resc3 = new ExternalResource("http://139.18.2.164:8080/saim_screencast.webm");
		resc3.setMIMEType("video/webm; codecs=vp8, vorbis");
		
		final Video v = new Video(  );
		v.setShowControls(true);
        v.setSources( resc1, resc2);
        v.setWidth( "640px" );
        v.setHeight( "360px" );
//        v.setSizeFull();
        return v;
	}
	
	
	private Label getManualLabel() {
		Label l = new Label("Manual", Label.CONTENT_XHTML);
		l.setValue("<h1>Manual</h1>");
		return l;
	}
	private Link getManualLink() {
		URL url;
		url = getClass().getClassLoader().getResource(manual);		
		File f = null;
		try {
			System.out.println("URL.toURI..."+url.toURI());
			f = new File(url.toURI());
			System.out.println("f.absolut"+f.getAbsolutePath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(f!= null)
			return new Link(manual, new FileResource(f, app));
		else
			return null;
	}
	
	private Label getVideoLabel() {
		Label l = new Label("Screencast", Label.CONTENT_XHTML);
		l.setValue("<h1>Screencast</h1>" +
				"This is a Screencast capturing the main features of SAIM.");
		return l;
	}
}
