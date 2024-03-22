<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.unibl.etf.ip.beans.SavjetnikBean"%>
<%@ page import="org.unibl.etf.ip.beans.PorukaBean"%>
<jsp:useBean id="savjetnikBean"
	class="org.unibl.etf.ip.beans.SavjetnikBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<%
if (request.getParameter("submit") != null) {
	if (request.getParameter("username") != null && request.getParameter("password") != null) {
		if(savjetnikBean.LogIn(request.getParameter("username"), request.getParameter("password"))){
			System.out.println("************" + savjetnikBean.getSavjetnik().getIme());
			PorukaBean porukaBean = new PorukaBean();
			session.setAttribute("savjetnikBean", savjetnikBean);
			session.setAttribute("porukaBean", porukaBean);
			response.sendRedirect("messages.jsp");
		}else{
			session.setAttribute("notification", "Neisparvni korisnicki podaci!");
		}
	}
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LogIn</title>
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link href="styles/style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<section>
		<div
			class="container d-flex align-items-center justify-content-center min-vh-100"
			style="flex-direction: column;">

			<div class="row ">

				<div class="col-sm-12 col-md-12 col-lg-5 col-xl-5 "
					style="width: 100%;">
					<div
						class="card  text-white justify-content-center align-items-center cardLogIn"
						style="border-radius: 5px; width: 100%;">
						<div class="card-header justify-content-center align-items-center">
							<div class="justify-content-center align-items-center">
								<label>FitnessApp-SAVJETNIK</label>
							</div>

						</div>
						<div class="card-header justify-content-center align-items-center">
							<div class="justify-content-center align-items-center">
								<label> Prijava</label>
							</div>

						</div>
						<div class="card-body py-1 text-center justify-content-center">

							<form class="formopacity" method="POST" action="LogIn.jsp">
								<div class="form-group">
									<label class="form-label">Korisni&ccaron;ko ime</label> <input
										type="text" id="username" name="username"
										class="form-control " />
								</div>
								<div class="form-group">
									<label class="form-label">Lozinka</label> <input
										type="password" name="password" id="password"
										class="form-control " />
								</div>
								<div class="py-2">
									<input name="submit" id="submit"
										class="btn btn-outline-light btn-lg" value="Prijavi se"
										type="submit" />
								</div>

							</form>

							<div>
								<%=session.getAttribute("notification") != null
		? "<div style='background-color: aliceblue; color: red; border-radius: 5px;'><span class='popuptext' id='myPopup'>"
				+ session.getAttribute("notification").toString() + "</span></div>"
		: ""%>

							</div>
						</div>
					</div>

				</div>

			</div>


		</div>


	</section>
</body>
</html>