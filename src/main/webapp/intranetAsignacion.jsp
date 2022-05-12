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

				
			  <form id="id_form_elimina" >
			  		<input type="hidden" id="id_asignacion" name="idAsignacion">
			  		<input type="hidden" id="id_eli_sede" name="idSede">
			  		<input type="hidden" id="id_usuario" name="idUsuario">
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
												<th style="width: 15%">Asignados / Total</th>
												<th style="width: 8%"></th>
											</tr>
										</thead>
											<tbody></tbody>
										</table>	  
								</div>	
						</div>
					</div>

  
			<div class="modal fade" id="id_div_modal_asignacion" >
						<div class="modal-dialog" style="width: 90%; height: 250px">
							<div class="modal-content">
							<div class="modal-header" >
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Asignación</h4>
							</div>
							<div class="modal-body" >
									<form id="id_form_registra" accept-charset="UTF-8" class="form-horizontal"     method="post">
					                    <div class="panel-group" id="steps">
					                        
											 <div class="panel panel-default">
												<div id="stepDos" class="panel-collapse collapse in">
					                                	<div class="panel-body">
					                                    			
																	<div id="id_div_respuesta" class="row" style="margin-top: 3%; display: block;">
																		<input type="hidden" name="idSede" id="id_reg_sede">
																		<div class="col-md-5">
																			<select id="id_reg_usuario" name="idUsuario" class='form-control' >
																				<option value="-1">[ Usuario ]</option>
																			</select>
																		</div>
																		<div class="col-md-2">
																			<input class="form-control" id="id_cantidad" onkeypress="return valida_numero();"  name="cantidad" placeholder="Cantidad" type="text" maxlength="4"/>
																		</div>
																		<div class="col-md-1" align="right">
																			<button type="button" style="width: 80px" id="id_btn_registra" class="btn btn-primary btn-sm">Asignar</button>
																		</div>
																	</div>
																	
																	<div class="row" style="margin-top: 3%; display: block;">
																		 <div class="col-md-12">
																						<table id='id_table_asignacion' class="table table-bordered table-hover table-condensed" style="width: 100%">
																							<thead>
																								<tr style='background-color:#337ab7; color:white'>
																									<th style='width: 5%'>#</th>
																									<th style='width: 22%'>Usuario</th>
																									<th style='width: 55%'>Sede</th>
																									<th style='width: 10%'>Cantidad</th>
																									<th style='width: 8%'></th>
																									</tr>
																							</thead>
																							<tbody></tbody>
																						</table>
																		</div>
																	</div>	
															</div>
					                                </div>
					                            </div>
					                        </div>
					                    
					                </form>   
							
							</div>
						</div>
					</div>
				</div>

 </div>

<script type="text/javascript">

var lstAsignacion = [];
$.getJSON("cargaNegocios",{},function(data){
	$.each(data.listaNegocio,function(i, obj){
		$("#id_lista_negocio").append("<option value='" + obj.idNegocio+ "'>"+obj.nombre+"</option>");
	});
	
});

$.getJSON("consultaConsolidadoTotalMensaje",{"idNegocio":"-1"}, function (data){
	agregarGrilla(data.listaConsolidado);
});

$("#id_lista_negocio").change(function(){
	var idNegocio = $("#id_lista_negocio").val();
	$.getJSON("consultaConsolidadoTotalMensaje",{"idNegocio":idNegocio}, function (data){
		agregarGrilla(data.listaConsolidado);
	});
});

function mostrarAsignacion(idSede){	
	$('#id_reg_sede').val(idSede);
	$('#id_eli_sede').val(idSede);
	
	$.getJSON("listaAsignacion",{"idSede":idSede}, function (data){
		lstAsignacion = data.listaAsignacion;
		agregarGrillaAsinacion(data.listaAsignacion);
	});
	
	$("#id_reg_usuario").empty();
	$("#id_reg_usuario").append("<option value='-1'>[ Usuario ]</option>");
	
	$.getJSON("cargaUsuariosPorSede",{"idSede":idSede},function(data){
		$.each(data.listaUsuarioPorSede,function(i, obj){
			$("#id_reg_usuario").append("<option value='" + obj.idUsuario+ "'>"+obj.nombre+"</option>");
		});
	});
	
	$('#id_div_modal_asignacion').modal("show");
}

function agregarGrilla(lista){
	 $('#id_table').DataTable().clear();
	 $('#id_table').DataTable().destroy();
	 $('#id_table').DataTable({
			data: lista,
			order: [[ 1, "asc" ]],
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
				    var salida='<button type="button"  style="width: 90px" class="btn btn-warning btn-sm" onclick="mostrarAsignacion(\''+row.idSede +  '\')">Asignar</button>';
					return salida;
				},className:'text-center'},			
			]                                     
	    });
}


function agregarGrillaAsinacion(lista){
	$('#id_table_asignacion').DataTable().clear();
	$('#id_table_asignacion').DataTable().destroy();
	$('#id_table_asignacion').DataTable({
		    data: lista,
			language: IDIOMA,
			paging: false,
			searching:false,
			ordering:false,
			info:false,
			columns:[
				{data: "idAsignacion",className:'text-center'},
				{data: "usuario"},
				{data: "sede"},
				{data: "cantidad"},
				{data: function(row, type, val, meta){
				    var salida='<button type="button" style="width: 90px" class="btn btn-warning btn-sm" onclick="eliminar(\'' + row.idAsignacion + '\',\'' + row.idSede + '\',\'' + row.idUsuario + '\')">Eliminar</button>';
					return salida;
				},className:'text-center'},			
			]                                     
	    });
}


function eliminar(idAsignacion,idSede, idUsuario){	
	var array =[idAsignacion,idSede, idUsuario];
	mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminar,null,array);
}

function accionEliminar(array){	
	$('#id_asignacion').val(array[0]);
	$('#id_sede').val(array[1]);
	$('#id_usuario').val(array[2]);
	$.ajax({
          type: "POST",
          url: "eliminaAsignacion", 
          data: $('#id_form_elimina').serialize(),
          success: function(data){
        		var idNegocio = $("#id_lista_negocio").val();
        		$.getJSON("consultaConsolidadoTotalMensaje",{"idNegocio":idNegocio}, function (data){
        			agregarGrilla(data.listaConsolidado);
        		});
        		lstAsignacion = data.listaAsignacion;
        		agregarGrillaAsinacion(data.listaAsignacion);
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
     });
}


$("#id_btn_registra").click(function(){
	var id_usuario = $('#id_reg_usuario').val();
	var id_cantidad = $('#id_cantidad').val();

	if (id_usuario == -1){
		mostrarMensaje("Seleccione el Usuario");
		return;
	}
	if (id_cantidad == ''){
		mostrarMensaje("Ingrese la cantidad");
		return;
	}
	
	/*var existeUsuario = false;
	
	$.each(lstAsignacion,function(i, obj){
		if ( obj.idUsuario == id_usuario ){
			existeUsuario= true; 
		}
	});
	
	if (existeUsuario){
		mostrarMensaje("Ya existe usuario");
		return;
	}*/

    $.ajax({
          type: "POST",
          url: "registraAsignacion", 
          data: $('#id_form_registra').serialize(),
          success: function(data){
        	$('#id_reg_usuario').val('-1');
        	$('#id_cantidad').val('');
        	var idNegocio = $("#id_lista_negocio").val();
      		$.getJSON("consultaConsolidadoTotalMensaje",{"idNegocio":idNegocio}, function (data){
      			agregarGrilla(data.listaConsolidado);
      		});
      		lstAsignacion = data.listaAsignacion;
      		agregarGrillaAsinacion(data.listaAsignacion);
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
        });

});


</script>   		
</body>
</html>