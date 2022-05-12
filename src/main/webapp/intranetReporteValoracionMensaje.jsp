<jsp:include page="intranetValida.jsp" />
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="private" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />


<link rel="shortcut icon" href="images/cibertec1.png" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.css"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.fileDownload.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>


<title>CIBERTEC</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
<div class="container" style="padding-top: 5%">

		 <div class="col-md-12" >
				
		       <form  action="listadoMensajeAcademicoNpsExcel" class="fileDownloadForm"  method="post">
				<div class="row">
						<div class="col-md-6">
							<h5><b>Reporte de valoración en académico</b></h5><br>
						</div>
				</div>
				<div class="row">

						<div class="form-group">
							<button  type="submit" style="width: 220px" class='btn btn-primary' id="id_ver_reporte">
									<i style="font-size: 20px;" class="fa fa-download"></i> Descarga Excel
							</button>
							<br>
						</div>
				</div>
		 		</form>
		  </div>
  
  			<div class="col-md-12" >
				
		       <form  action="listadoMensajeExtensionNpsExcel" class="fileDownloadForm"  method="post">
				<div class="row">
						<div class="col-md-6">
							<h5><b>Reporte de valoración en extensión</b></h5><br>
						</div>
				</div>
				<div class="row">

						<div class="form-group">
							<button  type="submit" style="width: 220px" class='btn btn-primary' id="id_ver_reporte">
									<i style="font-size: 20px;" class="fa fa-download"></i> Descarga Excel
							</button>
							<br>
						</div>
				</div>
		 		</form>
		  </div>
</div>
<script type="text/javascript">

$(document).on("submit", "form.fileDownloadForm", function (e) {
    $.fileDownload($(this).prop('action'), {
        preparingMessageHtml: "Estamos preparando su informe, por favor espere",
        failMessageHtml: "Se produjo un problema al generar su informe. Vuelva a intentarlo.",
        httpMethod: "POST",
        data: $(this).serialize()
    });
    e.preventDefault(); 	
});

</script>


</body>
</html> 