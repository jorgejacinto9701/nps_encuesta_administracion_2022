<jsp:include page="intranetValida.jsp" />
<!DOCTYPE html>
<html lang="esS">
<head>
<link rel="shortcut icon" href="images/cibertec1.png" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="shortcut icon" href="images/cibertec1.png" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />
<title>CIBERTEC</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />

	<div class="container" style="padding-top: 5%">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<h3>Sistema de Encuestas</h3>
					<h5>Bienvenido Sr(a): ${sessionScope.objUsuario.nombre}</h5>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
