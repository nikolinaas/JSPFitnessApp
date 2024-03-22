<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
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
							method="post">
							<div class="input-group mb-0 d-block">
								<label class="form-label p-0 d-block m-0">E-mail korisnika</label> <input
									class="form-control p-0 d-block m-0" placeholder="Unesite e-mail korisnika"
									type="text" style="width: 100%; " />
							</div>
							<div class="input-group mb-1 d-block">
								<label class="form-label p-0 d-block m-0">Naslov</label> <input
									class="form-control p-0 d-block m-0" placeholder="Unesite naslov poruke"
									type="text" style="width: 100%; " />
							</div>

							<div class="input-group mb-1 d-block">
								<label class="form-label d-block m-0 p-0">Odgovor</label>
								<textarea class="form-control d-block m-0"
									placeholder="Unesite tekst poruke" style="width: 100%;">
							</textarea>
							</div>
							<div class="input-group mb-1 d-block">
								<label class="form-label d-block m-0 p-0">Dodatni fajl i opis</label>
								<div class="d-flex  justify-content-between align-items-center">
									<input 	class="form-control p-0  m-0" placeholder="Unesite opis prilozenog fajla"
									type="text" style="width: 70%;" /><button class="btn  p-0  m-0 btn-card-read" style="width: 30%">Dodaj fajl</button></div>
							
							</div>
							
							<div class="input-group mb-1 d-flex justify-content-end">
								
							<button class="btn d-block m-0 p-0 btn-card-read">Posalji e-mail</button>
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