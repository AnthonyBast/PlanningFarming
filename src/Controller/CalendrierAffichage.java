package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.scene.layout.region.Margins.Converter;
import com.sun.jmx.snmp.Timestamp;

import Exception.Exception;
import Métier.*;

public class CalendrierAffichage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		
		int i = c.get(Calendar.MONTH);
		int j= c.get(Calendar.YEAR);
		int nbJours = c.getActualMaximum(Calendar.MONTH);
		String dd = "";
		String df = "";
		dd = Integer.toString(j) + "-" + Integer.toString(i+1) + "-" + Integer.toString(1);
		df = Integer.toString(j) + "-" + Integer.toString(i+2) + "-" + Integer.toString(1);
		
		ArrayList<EffectuerTache> tacheDuMois = new ArrayList<EffectuerTache>();
		tacheDuMois = mesTaches(dd,df);
		
		request.setAttribute("moisd", i+1);
		request.setAttribute("year", j);
		request.setAttribute("nbjours", nbJours);
		request.setAttribute("ef", tacheDuMois);
		request.getRequestDispatcher("/accueil.jsp")
			.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//On récupére le mois + ou -
		Calendar c = Calendar.getInstance();
        String dd = "";
        String df = "";
        String i = "";
        String year = "";
        int a=0;
        int b=0;        
        int monMois=0;
        int nbJours = 0;
		
		Enumeration <String> parametres = request.getParameterNames();
        while(parametres.hasMoreElements())
        {
            String param = parametres.nextElement();
            if(param.equals("boutonMoins")){
            	i = (String) request.getParameter("moisd");
            	year = (String) request.getParameter("year");
            	c.set(Integer.parseInt(year), Integer.parseInt(i), 1);
        		monMois = Integer.parseInt(i);
        		monMois--;
            	monMois--;
        		// Avoir un mois entre 1 et 12
            	if(monMois < 0){
            		c.set(Calendar.MONTH, 11);
            		nbJours = c.getActualMaximum(Calendar.MONTH);
            		b = c.get(Calendar.YEAR)-1;
            		dd = Integer.toString(c.get(Calendar.YEAR)-1) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            		a = 11;
            		monMois = 0;
            		c.set(Calendar.MONTH, monMois);
            		df = Integer.toString(c.get(Calendar.YEAR)) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            	}else{
            		c.set(Calendar.MONTH, monMois);
            		nbJours = c.getActualMaximum(Calendar.MONTH);
            		dd = Integer.toString(c.get(Calendar.YEAR)) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            		a = monMois;
            		b = c.get(Calendar.YEAR);
            		monMois++;
            		c.set(Calendar.MONTH, monMois);
            		df = Integer.toString(c.get(Calendar.YEAR)) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            	}
        		
            }
            if(param.equals("boutonPlus")){
            	i = (String) request.getParameter("moisd");
            	year = (String) request.getParameter("year");
            	c.set(Integer.parseInt(year), Integer.parseInt(i), 1);
        		monMois = Integer.parseInt(i);
            	if(monMois > 11){
            		c.set(Calendar.MONTH, 0);
            		nbJours = c.getActualMaximum(Calendar.MONTH);
            		dd = Integer.toString(c.get(Calendar.YEAR)+1) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            		monMois = 0;
            		a = monMois;
            		b = c.get(Calendar.YEAR)+1;
            		monMois++;
            		c.set(Calendar.MONTH, monMois);
            		df = Integer.toString(c.get(Calendar.YEAR)+1) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            	}else{
            		c.set(Calendar.MONTH, monMois);
            		nbJours = c.getActualMaximum(Calendar.MONTH);
            		dd = Integer.toString(c.get(Calendar.YEAR)) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            		a = monMois;
            		b = c.get(Calendar.YEAR);
            		monMois++;
            		c.set(Calendar.MONTH, monMois);
            		df = Integer.toString(c.get(Calendar.YEAR)) + "-" + 
            				Integer.toString(c.get(Calendar.MONTH)+1) +"-" + Integer.toString(1);
            	}
            }
        }
						
		
        ArrayList<EffectuerTache> tacheDuMois = new ArrayList<EffectuerTache>();
		tacheDuMois = mesTaches(dd,df);
		request.setAttribute("moisd", a+1);
		request.setAttribute("year", b);
		request.setAttribute("nbjours", nbJours);
        
		request.setAttribute("ef", tacheDuMois);
		request.getRequestDispatcher("/accueil.jsp")
		.forward(request, response);
	}
		
	@SuppressWarnings("finally")
	private ArrayList<EffectuerTache> mesTaches(String dDebut, String dFin){
		// Date format AAAA-MM-JJ hh:mm:ss		
		ArrayList<EffectuerTache> maListe = new ArrayList<EffectuerTache>();
		
		System.out.println(dDebut);
		System.out.println(dFin);
				
		String maReq = "select * from calendrierfarm.effectuertache e ,calendrierfarm.tache t "
				+ "where t.idTache = e.idTache and e.dateHeureDebut >= '" + dDebut + "' and e.dateHeureDebut < '"+
				dFin +"' order by e.dateHeureDebut;";
		Connexion connect = new Connexion();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(maReq);
			while (myRs.next()){
				EffectuerTache et = new EffectuerTache();
				Tache t = new Tache();
				t.setIdTache(myRs.getInt("idTache"));
				t.setLibelle(myRs.getString("libelle"));
				t.setDescription(myRs.getString("description"));
				t.setDureeMin(myRs.getInt("dureeMin"));
				et.setTache(t);
				et.setDateHeureDebut(myRs.getDate("dateHeureDebut"));
				et.setDateHeureDebut(myRs.getDate("dateHeureFin"));
				maListe.add(et);
			}
		}catch(Exception e){
			connect.getLogger().severe(e.toString());
		}finally {
			try {
				myRs.close();
				myStmt.close();
			}
			catch (Exception | SQLException exc) {
				connect.getLogger().severe(exc.toString());
			}
			if(maListe == null){
				return null;
			}else{
				return maListe;
			}
		}
	}
}
