<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="private" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />

<script type="text/javascript" src="jsNuevo/jquery.min.1.9.1.js"></script>
<script type="text/javascript" src="jsNuevo/jquery-ui.min.js"></script>
<script type="text/javascript" src="jsNuevo/jquery.fileDownload.js"></script>

<script type="text/javascript" src="jsNuevo/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="jsNuevo/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="jsNuevo/bootstrap.min.js"></script>
<script type="text/javascript" src="jsNuevo/bootstrapValidator.js"></script>
<script type="text/javascript" src="jsNuevo/global.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="cssNuevo/jquery-ui.css" />
<link rel="stylesheet" href="cssNuevo/bootstrap.css" />
<link rel="stylesheet" href="cssNuevo/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="cssNuevo/bootstrapValidator.css" />

<title>Intranet | ILP </title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />

<div class="container" style="margin-top: 5%">
<h5><b>Consolidado de encuestas</b></h5>
</div>

<div class="container" style="margin-top: 1%">

				
				<form  id="id_frm_mensaje" action="verListadoMensaje">
						<input type="hidden" id="id_sede" name="idSede" >
						<input type="hidden" id="id_negocio" name="idNegocio" >
				</form>
				<form  id="id_form" >
			       		<div class="row" style="height: 70px">
							<div class="col-md-4" >
								<select id="id_lista_negocio" name="negocio" class='form-control'>
									<option value="-1">[ Todos los Negocios ]</option>
								</select>
							</div>
						</div>
					</form>	
					
					<div class="row" > 
						<div class="col-md-12">
								<div class="content" >
						
									<table id="id_table" class="table table-bordered table-hover table-condensed" >
										<thead>
											<tr style='background-color:#337ab7; color:white'>
												<th style="width: 5%">#</th>
												<th style="width: 62%">Sede</th>
												<th style="width: 10%">Negocio</th>
												<th style="width: 15%">Clasificadas / Asignadas</th>
												<th style="width: 8%"></th>
											</tr>
										</thead>
											<tbody></tbody>
										</table>	  
								</div>	
						</div>
					</div>

  
		

 </div>

<script type="text/javascript">

$.getJSON("cargaNegocios",{},function(data){
	$.each(data.listaNegocio,function(i, obj){
		$("#id_lista_negocio").append("<option value='" + obj.idNegocio+ "'>"+obj.nombre+"</option>");
	});
	
});

$.getJSON("consultaConsolidadoMensaje",{"idNegocio":"-1"}, function (data){
	agregarGrilla(data.listaConsolidado);
});

$("#id_lista_negocio").change(function(){
	var idNegocio = $("#id_lista_negocio").val();
	$.getJSON("consultaConsolidadoMensaje",{"idNegocio":idNegocio}, function (data){
		agregarGrilla(data.listaConsolidado);
	});
});

function mostrarMensajeDeSede(idSede, idNegocio){	
	$('#id_sede').val(idSede);
	$('#id_negocio').val(idNegocio);
	$('#id_frm_mensaje').submit();
}


function agregarGrilla(lista){
	 $('#id_table').DataTable().clear();
	 $('#id_table').DataTable().destroy();
	 $('#id_table').DataTable({
			data: lista,
			order: [[ 0, "desc" ]],
			language: IDIOMA,
			searching: false,
			ordering: true,
			processing: true,
			pageLength: 10,
			lengthChange: false,
			info:false,
			scrollY: 290,
	        scroller: {
	            loadingIndicator: true
	        },
			columns:[
				{data: "idSede",className:'text-center'},
				{data: "nombre"},
				{data: "negocio",className:'text-center'},
				{data: "asignadas",className:'text-center'},
				{data: function(row, type, val, meta){
				    var salida='<button type="button"  style="width: 90px" class="btn btn-warning btn-sm" onclick="mostrarMensajeDeSede(\''+row.idSede + '\',\'' + row.idNegocio  + '\')">Mostrar</button>';
					return salida;
				},className:'text-center'},			
			]                                     
	    });
}



</script>   		
</body>
</html>