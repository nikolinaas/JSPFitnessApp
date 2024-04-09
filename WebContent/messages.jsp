<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.unibl.etf.ip.beans.PorukaBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.unibl.etf.ip.beans.SavjetnikBean"%>
<%@ page import="org.unibl.etf.ip.dto.Poruka"%>
<jsp:useBean id="savjetnikBean"
	class="org.unibl.etf.ip.beans.SavjetnikBean" scope="session"></jsp:useBean>

<jsp:useBean id="porukaBean" class="org.unibl.etf.ip.beans.PorukaBean"></jsp:useBean>
<%
ArrayList<Poruka> poruke = new ArrayList<Poruka>();
String action = request.getParameter("action");
if (action == null || "".equals(action)) {
	System.out.print("+++++++++++" + savjetnikBean.getSavjetnik().getId());
	poruke = savjetnikBean.getAllMessages(savjetnikBean.getSavjetnik().getId());
} else if (action.equals("trazi")) {
	if (request.getParameter("searchText") != null && request.getParameter("searchText") != "") {
		String content = request.getParameter("searchText");
		System.out.print("+++++++++++" + content);
		poruke = savjetnikBean.getMessageByContent(content, savjetnikBean.getSavjetnik().getId());
		//session.setAttribute("searchTerm", request.getParameter("searchText"));
		request.setAttribute("searchTerm", "");
	} else {
		poruke = savjetnikBean.getAllMessages(savjetnikBean.getSavjetnik().getId());
	}

} else if (action.equals("procitajPoruku")) {

	if (request.getParameter("idPoruke") != null || !"".equals(request.getParameter("idPoruke"))) {
		int id = Integer.parseInt(request.getParameter("idPoruke"));
		if (porukaBean.MarkMessageAsRead(id)) {
	poruke = savjetnikBean.getAllMessages(savjetnikBean.getSavjetnik().getId());
	session.setAttribute("porukaProcitana", true);
		}
	}

} else if (action.equals("odgovoriNaPoruku")) {
	response.sendRedirect("sendEmail.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<link href="styles/style.css" type="text/css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Poruke</title>

<script type="text/javascript">
	function procitajPoruku(idPoruke) {
		window.location.href = "messages.jsp?action=procitajPoruku&idPoruke="
				+ idPoruke;
		
	}
	
	function odgovoriNaPoruku() {
		window.location.href = "messages.jsp?action=odgovoriNaPoruku";
	}
	
	  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
	  var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	    return new bootstrap.Tooltip(tooltipTriggerEl);
	  });
</script>
</head>

	<div id="logovi" class="col  main">
		<div class="row" style="height: 3%;"></div>
		<div
			class="row horizontalline justify-content-center align-items-center">
			<label class="labelhorizontal justify-content-center"
				style="color: aliceblue; font-size: 20px; align-items: center;">Poruke</label>
		</div>
		<div class="row justify-content-center align-items-center"
			style="height: 15%">
			<div class="cool-1"></div>

			<div class="col-10">

				<form class="input-group" method="post"
					action="messages.jsp?action=trazi">
					<input type="text" class="form-control" placeholder="Tekst poruke"
						name="searchText" id="searchText">
					<button class="btn btn-card-read" type="submit" id="button-addon2">Tra&zcaron;i</button>
				</form>
			</div>
			<div class="col-1"></div>
		</div>
		<div id="poruke"
			class="container justify-content-center align-items-center">
			<div class="col-1"></div>
			<div class="col-10" data-spy="scroll"
				style="justify-content: center; width: 100%; padding: 10px; overflow-y: auto; width: 100%; height: 100%; max-height: 65%; position: absolute; left: 10%;">
				<%
				for (Poruka p : poruke) {
					if (!p.isProcitana()) {
				%>
				<div class="card  mb-1  card-unread" style="width: 100%;">
					<div
						class="card-header d-flex justify-content-between align-items-center">
						<%=p.getKorisnik().getIme() + " " + p.getKorisnik().getPrezimme()%>
						<div>
							<button class="btn btn-card-unread"
								onclick="procitajPoruku(<%=p.getId()%>)">Pregledaj
								poruku</button>
							<button class="btn btn-card-unread" data-bs-toggle="tooltip" data-bs-placement="top" title="Tooltip Text" onclick="odgovoriNaPoruku()">Odgovori</button>
						</div>
					</div>
					
					<div class="card-body">
					<div class="naslov-class"><%=p.getNaslov()%></div>
						<%=p.getSadrzaj()%>
					</div>
				</div>
				<%
				}
				}
				%>
				<%
				for (Poruka p : poruke) {
					if (p.isProcitana()) {
				%>
				<div class="card  mb-1 card-read" style="width: 100%;">
					<div
						class="card-header d-flex justify-content-between align-items-center">
						<%=p.getKorisnik().getIme() + " " + p.getKorisnik().getPrezimme()%>
						<div>
							<button class="btn btn-card-read" disabled="disabled">Pregledana
								poruka</button>
							<button class="btn btn-card-read" onclick="odgovoriNaPoruku()">Odgovori</button>
						</div>

					</div>
					<div class="card-body">
					<div class="naslov-class"><%=p.getNaslov()%></div>
						<%=p.getSadrzaj()%>
					</div>
				</div>
				<%
				}
				}
				%>


			</div>
			<div class="col-1"></div>
		</div>
	</div>

	<script>
    window.addEventListener('load', () => {
    	const toastShownSuccess = <%=session.getAttribute("porukaProcitana")%>
     
        if (toastShownSuccess) {
         alert(Poruka procitana);

            <%session.setAttribute("porukaProcitana", "false");%>
        }
    });
    </script>

</html>