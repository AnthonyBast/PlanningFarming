<%@page import="Métier.EffectuerTache"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"  import="java.sql.*" import="Metier.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
Calendar today = Calendar.getInstance(); 
String wantYear =(String) request.getAttribute("year").toString();
String wantMonth =(String) request.getAttribute("moisd").toString();
if(wantYear != null && wantMonth != null){	
	int year = Integer.parseInt(wantYear);
	int month = Integer.parseInt(wantMonth);
	today.set(year,--month,1);
}
else{
	wantMonth = Integer.toString(today.get(Calendar.MONTH));	
}
if(wantMonth.length()==1)	wantMonth="0"+wantMonth;
Calendar firstDay = Calendar.getInstance();	//firstDay
Calendar lastDay = Calendar.getInstance();	//lastDay
Calendar cal = Calendar.getInstance(); // Comparer date
firstDay.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1);
lastDay.set(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, 1);
cal.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1);
int nYear = today.get(Calendar.YEAR);
int nMonth = today.get(Calendar.MONTH);
ArrayList<EffectuerTache> ef = new ArrayList<EffectuerTache>();
ef =(ArrayList<EffectuerTache>) request.getAttribute("ef");

%>
<title>Accueil</title>
</head>
<body>
	<h1>Calendrier Farming</h1>

	<form action="CalendrierAffichage" method="post">
		<input type="submit" name="boutonMoins" id="boutonMoins" value="Mois-1" />
		<span><input type="number" name="year"	id="year" value="${year}" /></span>
		<span><input type="number" name="moisd"	id="moisd" value="${moisd}" /></span>
		<input type="submit" name="boutonPlus" id="boutonPlus" value="Mois+1" />
	</form>

	<div class="container-fluid">
		<div class="row">
			<div class="monagenda">
			<TABLE style="margin:0 auto;display:block;border-spacing:0;background-color:snow;align:center;">
				<th></th>
				
				<TR style="text-align:center;background-color:wheat;">
					<TD width="95"><FONT size="1" color="red"><B>SUN</B></FONT></TD>
					<TD width="95"><FONT size="1"><B>MON</B></FONT></TD>
					<TD width="95"><FONT size="1"><B>TUE</B></FONT></TD>
					<TD width="95"><FONT size="1"><B>WED</B></FONT></TD>
					<TD width="95"><FONT size="1"><B>THU</B></FONT></TD>
					<TD width="95"><FONT size="1"><B>FRI</B></FONT></TD>
					<TD width="95"><FONT size="1" color="blue"><B>SAT</B></FONT></TD>
				</TR>
				<tr>
					<%
					String day = "";
					String span=" colspan=\"+"+firstDay.get(Calendar.DAY_OF_WEEK)+"\"";
					String rsDay = "0";
					String rsTitle = "";
					boolean key=true;
					while(firstDay.before(lastDay)) {
						day = "" + firstDay.get(Calendar.DATE); 
					%>
						<TD align="right" <%=span %> nowrap>
							<TABLE width="95" border=0 style="width:110; height:65; table-layout:fixed;">
							<TR bgcolor="oldlace">
								<Td align="right">
									<p><FONT size="1" color="gray"><%=day %></FONT></p>
								</Td>
							</TR>
							<TR height="50" bgcolor="lavender">
								<td>
								
									<%
										for(EffectuerTache e:ef){											
											cal.setTime(e.getTache().getDateHeureDebut());
											
											int i = cal.get(Calendar.DATE);
											int j = firstDay.get(Calendar.DATE);
											
											if(i == j){
												%>
												<p><%=e.getTache().getLibelle() %></p>
											<%
											}
										}
									%>
								</td>
							</TR>
							<TR height="50" bgcolor="lavender">
								<td>
									<input type="button" value="+ task"/>
								</td>
							</TR>
							</TABLE>
						</TD>
					<%	
						if ( firstDay.get(Calendar.DAY_OF_WEEK)%7==0){	
					%>
					</TR>
					<TR>
					<%
						}
						span="";
						firstDay.add(Calendar.DATE, 1);
					}
					
					%>
				</tr>
			</TABLE>
			</div>
		</div>
	</div>

</body>
</html>