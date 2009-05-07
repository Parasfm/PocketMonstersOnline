package org.pokenet.client.ui.frames;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.newdawn.slick.Color;
import org.pokenet.client.GameClient;
import org.pokenet.client.backend.Translator;

import mdes.slick.sui.Button;
import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import mdes.slick.sui.TextField;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

/**
 * Handles server selection
 * @author shadowkanji
 *
 */
public class ServerDialog extends Frame {
	private Button [] m_servers;
	private String [] m_host;
	private Label m_info;
	private Color m_black;
	private Button privateServer;
	private TextField privateIP;
	/**
	 * Default constructor
	 */
	public ServerDialog() {
		m_black = new Color(0, 0, 0);
		List<String> translate = Translator.translate("_LOGIN");
		
		this.setSize(316, 280);
		this.setLocation(400 - 160, 280);
		this.setTitle(translate.get(0));
		this.setBackground(new Color(0, 0, 0, 70));
		this.getTitleBar().setForeground(m_black);
		this.setDraggable(false);
		this.setResizable(false);
		this.getTitleBar().getCloseButton().setVisible(false);
		
		/*
		 * Create the info label
		 */
		m_info = new Label(translate.get(1));
		m_info.pack();
		m_info.setLocation(24, 8);
		m_info.setForeground(new Color(255, 255, 255));
		this.add(m_info);
		
		/*
		 * Create all the server buttons
		 */
		try {
			m_servers = new Button[5];
			m_host = new String[5];
			URL url = new URL("http://pokenetonline.googlecode.com/svn/trunk/Client/res/servers.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			
			m_servers[0] = new Button(in.readLine());
			m_servers[0].setSize(280, 24);
			m_servers[0].setLocation(16, 32);
			m_servers[0].setVisible(true);
			m_servers[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[0]);
				}
			});
			this.add(m_servers[0]);
			m_host[0] = in.readLine();
			
			m_servers[1] = new Button(in.readLine());
			m_servers[1].setSize(280, 24);
			m_servers[1].setLocation(16, 64);
			m_servers[1].setVisible(true);
			m_servers[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[1]);
				}
			});
			this.add(m_servers[1]);
			m_host[1] = in.readLine();
			
			m_servers[2] = new Button(in.readLine());
			m_servers[2].setSize(280, 24);
			m_servers[2].setLocation(16, 96);
			m_servers[2].setVisible(true);
			m_servers[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[2]);
				}
			});
			this.add(m_servers[2]);
			m_host[2] = in.readLine();
			
			m_servers[3] = new Button(in.readLine());
			m_servers[3].setSize(280, 24);
			m_servers[3].setLocation(16, 128);
			m_servers[3].setVisible(true);
			m_servers[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[3]);
				}
			});
			this.add(m_servers[3]);
			m_host[3] = in.readLine();
			
			m_servers[4] = new Button(in.readLine());
			m_servers[4].setSize(280, 24);
			m_servers[4].setLocation(16, 160);
			m_servers[4].setVisible(true);
			m_servers[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[0]);
				}
			});
			this.add(m_servers[4]);
			m_host[4] = in.readLine();
			
			/*
			 * Finally, check which servers don't exist and disable their buttons
			 */
			for(int i = 0; i < 5; i++) {
				if(m_host[i].equalsIgnoreCase("-")) {
					m_servers[i].setEnabled(false);
					m_host[i] = "";
				}
				m_servers[i].setForeground(m_black);
			}
			privateIP = new TextField();
			privateIP.setLocation(16, 204);
			privateIP.setSize(128, 24);
			this.add(privateIP);
			
			privateServer = new Button();
			privateServer.setText(translate.get(2));
			privateServer.setSize(128, 24);
			privateServer.setLocation(168, 204);
			privateServer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					GameClient.setHost(getPrivateServer());
				}
			});
			this.add(privateServer);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		this.setVisible(false);
	}
	
	public String getPrivateServer() {
		if(privateIP.getText().length() > 0)
			return privateIP.getText();
		else
			return "localhost";
	}
	public void goServer(){
		GameClient.setHost(getPrivateServer());
	}
}
