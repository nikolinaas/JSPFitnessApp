<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="javax.mail.Multipart"%>
<%@page import="javax.mail.AuthenticationFailedException"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.mail.internet.MimeBodyPart"%>
<%@page import="javax.mail.internet.AddressException"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.activation.FileDataSource"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="java.io.File"%>
<jsp:useBean id="savjetnikBean"
	class="org.unibl.etf.ip.beans.SavjetnikBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="styles/style.css" type="text/css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Slanje email-a</title>

<script type="text/javascript">
	
	
	function showFileName() {
		   var fil = document.getElementById('fileInput').value;
		  alert(fil)
		   window.location.href = "sendEmail.jsp?action=dodajFajl&fajl=" + fil; 
		}
</script>

</head>

<body>
<%
String action = request.getParameter("action");
if("dodajFajl".equals(action)){
	String fajl = request.getParameter("fajl");
	System.out.println("======================" + fajl);
}
if (request.getParameter("submit") != null) {
	 System.out.println("submiiiiiiiiiiiit");
	
	session.setAttribute("notification", "");
	String userEmail = (String)session.getAttribute("userEmail");
	 System.out.println(request.getParameter("reply"));
	Session newSession = null;
	Properties props = System.getProperties();
	props.put("mail.smtp.port", "587");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	newSession = Session.getDefaultInstance(props, null);

	String emailSubject = request.getParameter("title");
	String text = request.getParameter("reply");
	
	if(request.getParameter("fileDescription")!=null && !request.getParameter("fileDescription").isEmpty()){
		text = text + "  Opis fajla:" + request.getParameter("fileDescription");
	}

	MimeMessage mimeMessage = new MimeMessage(newSession);

	try {
		mimeMessage.addRecipients(Message.RecipientType.TO, userEmail);
		System.out.println(mimeMessage.getAllRecipients()[0].toString());
		mimeMessage.setSubject(emailSubject);

		if (request.getParameter("fileInput") != null && !request.getParameter("fileInput").isEmpty()) {
	MimeMultipart multiPart = new MimeMultipart();


	MimeBodyPart textPart = new MimeBodyPart();
	textPart.setContent(text, "text/plain");
	multiPart.addBodyPart(textPart);

	MimeBodyPart attachmentPart = new MimeBodyPart();

	DataSource source = new FileDataSource(request.getParameter("fileInput"));
	attachmentPart.setDataHandler(new DataHandler(source));
	attachmentPart.setFileName(request.getParameter("fileInput"));
	multiPart.addBodyPart(attachmentPart);
	
	System.out.println("ovo je putanja:" + source);

	mimeMessage.setContent(multiPart);
		} else {
	mimeMessage.setText(text);
		}

		String from = "fitnessapp2024@gmail.com";
		String pass = "pbvwnuqhnvesxpjv";
		String emailHost = "smtp.gmail.com";

		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, from, pass);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		session.setAttribute("notification", "Uspješno poslan mejl");

	} catch (AddressException e) {
		e.printStackTrace();
	} catch (MessagingException e) {
		e.printStackTrace();
		System.out.println("Exception occurred while sending the email message.");
	}
} 

%>
	<div id="logovi" class="col main">
		<div class="row" style="height: 3%;"></div>
		<div
			class="row horizontalline justify-content-center align-items-center">

			<div>
				<label class="labelhorizontal justify-content-center"
					style="color: aliceblue; font-size: 20px; align-items: center;">Poruke</label>
			</div>

		</div>
		<div class="row justify-content-center align-items-center"
			style="height: 4%"></div>
		<div id="poruke"
			class="container justify-content-center align-items-center">
			<div class="col-3"></div>
			<div class="col-6 container-fluid d-flex align-items-center content"
				style="height: calc(100vh - 100px) !important;">
				<div class="card shadow border border-0 p-1"
					style="background: #002233; color: white; height: calc(100vh - 30%); width: 100%;">

					<div class="card-header d-sm-flex justify-content-center p-0"
						style="height: 10%">Odgovor na poruku</div>
					<div class="card-body d-sm-flex justify-content-center p-0">
						<form class="input-group d-flex justify-content-center"
							 method="post" >
							<div class="input-group mb-0 d-block">
								<label class="form-label p-0 d-block m-0">E-mail korisnika</label> <input id="userEmail" name="userEmail"
									class="form-control p-0 d-block m-0" placeholder="Unesite e-mail korisnika"
									type="email" style="width: 100%; " />
							</div>
							<div class="input-group mb-1 d-block">
								<label class="form-label p-0 d-block m-0">Naslov</label> <input id="title" name="title"
									class="form-control p-0 d-block m-0" placeholder="Unesite naslov poruke"
									type="text" style="width: 100%; " />
							</div>

							<div class="input-group mb-1 d-block">
								<label class="form-label d-block m-0 p-0">Odgovor</label>
								<input class="form-control d-block m-0"
									placeholder="Unesite tekst poruke" style="width: 100%;" id="reply" name="reply"/>
							
							</div>
							<div class="input-group mb-1 d-block">
								<label class="form-label d-block m-0 p-0">Dodatni fajl i opis</label>
								<div class="d-flex  justify-content-between align-items-center">
									<input 	class="form-control p-0  m-0" placeholder="Unesite opis prilozenog fajla" id="fileDescription" name="fileDescription"
									type="text" style="width: 50%;" /> <input class="form-control" type="file" 
									id="fileInput" name="fileInput" ></div>
									
									<button onclick="showFileName()">Dodaj fajl</button>
							
							</div>
							
							<div class="input-group mb-1 d-flex justify-content-end">
								
							<input class="btn d-block m-0 p-0 btn-card-read" type="submit" id="submit" name="submit" value="Po&scaron;alji e-mail"/>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>


</body>
</body>
</html>