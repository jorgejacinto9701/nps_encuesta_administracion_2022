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
				
			   <form id="id_form_elimina" action="eliminaCrudTema">
			  		<input type="hidden" id="id_eli_mensaje" name="idMensaje">
			  		<input type="hidden" id="id_eli_aspecto" name="idAspecto">
			  		<input type="hidden" id="id_eli_tema" name="idTema">
			   </form>
			   	
		       		<div class="row" style="height: 70px">
							<div class="col-md-5" >
								<div class="btn-group" data-toggle="buttons">
								  <label class="btn btn-success active">
								    <input type="radio" name="rdtEstado" value="No Clasificado">  Preguntas por Clasificar
								  </label>
								  <label class="btn btn-success">
								    <input type="radio" name="rdtEstado"  value="Clasificado"> Preguntas Clasificadas
								  </label>
								</div>
							</div>
					</div>
					
					<div class="row" > 
						<div class="col-md-12" id="id_div_table">
						</div>
					</div>

  
	 <div class="modal fade" id="id_div_modal_tema" >
			<div class="modal-dialog" style="width: 90%; height: 250px">
				<div class="modal-content">
				<div class="modal-header" >
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Valoración</h4>
				</div>
				<div class="modal-body" >
						<form id="id_form_registra" accept-charset="UTF-8" class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        
		                        
		                        <div class="panel panel-default">
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                	<div class="panel-body">
		                                    			<div class="row" style="margin-top: 1%">
															<div class="col-md-2" align="right"><span style="font-weight: bold">Pregunta NPS : </span></div>
															<div class="col-md-10">
																Basado en la experiencia que has vivido en CIBERTEC, ¿qué tan dispuesto estás a recomendarlo a un familiar o amigo?	
															</div>
														</div>
														
														<div class="row" style="margin-top: 1%">
															<div class="col-md-2"align="right"><span style="font-weight: bold">Pregunta : </span></div>
															<div class="col-md-10">
																<span id="id_text_pregunta"></span>	
															</div>
														</div>
														
														<div class="row" style="margin-top: 1%">
															<div class="col-md-2"align="right"><span style="font-weight: bold">Respuesta : </span></div>
															<div class="col-md-10">
																<span id="id_text_respuesta"></span>
															</div>
														</div>
		                                	</div>
		                            </div>
								</div>

								 <div class="panel panel-default">
									<div id="stepDos" class="panel-collapse collapse in">
		                                	<div class="panel-body">
		                                    			<div class="row" style="margin-top: 1%">
		                                    				<div class="col-md-2" align="right"><span id="id_label_definicion" style="font-weight: bold"></span></div>
															<div class="col-md-7" id="id_definicion"></div>
															<div class="col-md-2" align="right">
																<input id="id_chk_ninguno" type="checkbox"  name="noregistrado"  class="btn btn-primary btn-sm"> No Registrado (N/A)
															</div>
															<div class="col-md-1" align="right">
																<button type="button" style="width: 80px" id="id_btn_salir" class="btn btn-primary btn-sm">Salir</button>
															</div>
														</div>
														
														<div id="id_div_respuesta" class="row" style="margin-top: 3%; display: block;">
															<input type="hidden" name="idMensaje" id="id_mensaje">
															<div class="col-md-4">
																<select id="id_aspecto" name="idAspecto" class='form-control' >
																	<option value="-1">[ Aspecto ]</option>
																</select>
															</div>
															<div class="col-md-5">
																<select id="id_tema" name="idTema" class='form-control'>
																	<option value="-1">[ Tema ]</option>
																</select>
															</div>
															<div class="col-md-2">
																<select id="id_valoracion" name="valoracion" class='form-control'>
																	<option value="-1">[ Valoración ]</option>
																	<option value="Positivo">Positivo</option>  
																	<option value="Neutro">Neutro</option>
																	<option value="Negativo">Negativo</option>
																</select>
															</div>
															<div class="col-md-1" align="right">
																<button type="button" style="width: 80px" id="id_btn_registra" class="btn btn-primary btn-sm">Agregar</button>
															</div>
														</div>
														<div id="id_div_valoracion" class="row" style="margin-top: 1%; display: block;"> 
																<div class="col-md-12">
																		<table id='id_table_mensaje' class="table table-bordered table-hover table-condensed" style="width: 100%">
																			<thead>
																				<tr style='background-color:#337ab7; color:white'>
																					<th style='width: 5%'>#</th>
																					<th style='width: 22%'>Aspecto</th>
																					<th style='width: 55%'>Tema</th>
																					<th style='width: 10%'>Valoración</th>
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

var dataGridMensaje = [];

$.getJSON("listadoMensajePorEstado",{"idEstado":"No Clasificado"}, function (data){
	agregarGrilla(data.listaMensaje, TIPO_NO_CLASIFICADOS);
});

$('input:radio[name="rdtEstado"]').change(
    function(){
       if ($(this).is(':checked')) {
    	   var idEstado = $(this).val();
    	   $.getJSON("listadoMensajePorEstado",{"idEstado":idEstado}, function (data){
    			agregarGrilla(data.listaMensaje, getTipoMensaje(idEstado));
    	   });
      }
});
	    
function agregarGrilla(lista, tipo){
	 if (tipo == TIPO_NO_CLASIFICADOS){
		 $('#id_div_table').html("<table id='id_table' class='table table-bordered table-hover table-condensed' ><thead><tr style='background-color:#337ab7; color:white'><th style='width: 5%'>#</th><th style='width: 82%'>Respuesta</th><th style='width: 5%'>NPS</th><th style='width: 8%'></th></tr></thead><tbody></tbody></table>");
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				order: [[ 0, "desc" ]],
				language: IDIOMA,
				searching: false,
				ordering: false,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:false,
				scrollY: 290,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
					{data: "idMensaje",className:'text-center'},
					{data: "mensaje",className:'text-left'},
					{data: "nps",className:'text-center'},
					{data: function(row, type, val, meta){
					    var salida='<button type="button" style="width: 90px" class="btn btn-warning btn-sm" onclick="mostrar(\''+row.idMensaje + '\',\''+ row.nps + '\',\''+ row.mensaje + '\',\''+ row.strTipo + '\')">Clasificar</button>';
						return salida;
					},className:'text-center'},			
				]                                     
		    });
		 
	 }else{
		 $('#id_div_table').html("<table id='id_table' class='table table-bordered table-hover table-condensed' ><thead><tr style='background-color:#337ab7; color:white'><th style='width: 5%'>#</th><th style='width: 28%'>Respuesta</th><th style='width: 5%'>NPS</th><th style='width: 5%'>N/A</th><th style='width: 18%'>Aspectos</th><th style='width: 19%'>Temas</th><th style='width: 12%'>Valoración</th><th style='width: 8%'></th></tr></thead><tbody></tbody></table>");
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				order: [[ 0, "desc" ]],
				language: IDIOMA,
				searching: false,
				ordering: false,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:false,
				scrollY: 290,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
					{data: "idMensaje",className:'text-center'},
					{data: "mensaje",className:'text-left'},
					{data: "nps",className:'text-center'},
					{data: "strTipo",className:'text-center'},
					{data: function(row, type, val, meta){
						var mensaje = "<span><ul>"
						$.each(row.valoracion,function(i, obj){
							mensaje += "<li>"+ obj.strAspecto +"</li>";
						});
						mensaje += "</ul></span>"
						return mensaje;
					},className:'text-left'},	
					{data: function(row, type, val, meta){
						var mensaje = "<span><ul>"
						$.each(row.valoracion,function(i, obj){
							mensaje += "<li>"+ obj.strTema +"</li>";
						});
						mensaje += "</ul></span>"
						return mensaje;
					},className:'text-left'},	
					{data: function(row, type, val, meta){
						var mensaje = "<span><ul>"
						$.each(row.valoracion,function(i, obj){
							mensaje += "<li>"+ obj.strValoracion +"</li>";
						});
						mensaje += "</ul></span>"
						return mensaje;
					},className:'text-center'},	
					{data: function(row, type, val, meta){
					    var salida='<button type="button" style="width: 90px" class="btn btn-warning btn-sm" onclick="mostrar(\''+row.idMensaje + '\',\''+ row.nps + '\',\''+ row.mensaje + '\',\''+ row.strTipo + '\')">Clasificar</button>';
						return salida;
					},className:'text-center'},			
				]                                     
		    });
	 }
	
	 
}


$.getJSON("cargaAspectosActivos",{},function(data){
	$.each(data.listaAspecto,function(i, obj){
		$("#id_aspecto").append("<option value='" + obj.idAspecto+ "'>"+obj.nombre+"</option>");
	});
});

$('#id_chk_ninguno').change(function() {
	var idMensaje = $("#id_mensaje").val();
	var esNinguno = $(this).is(":checked");

    if($(this).is(":checked")) {
    	$('#id_div_respuesta').hide();
    	$('#id_div_valoracion').hide();
    	$('#id_definicion').html("");
		$('#id_label_definicion').html("");
    }else{
    	$('#id_div_respuesta').show();
    	$('#id_div_valoracion').show();
    }

	$.getJSON("estableceMensajeNinguno",{"idMensaje":idMensaje,"esNinguno":esNinguno}, function (data){
			dataGridMensaje = data.listaMensajeValoracion;
			agregarGrillaMensaje(data.listaMensajeValoracion);
		    var idEstado = $("#id_estado").val();
		    $.getJSON("listadoMensajePorEstado",{"idEstado":idEstado}, function (data){
				agregarGrilla(data.listaMensaje, getTipoMensaje(idEstado));
		    });
	});
	 
});



$("#id_btn_salir").click(function(){
	$('#id_div_modal_tema').modal("hide");
});

var dataTemas = [];

$("#id_aspecto").change(function(){
	var idAspecto = $("#id_aspecto").val();
	$('#id_definicion').html("");	
	$('#id_label_definicion').html("");
	$.getJSON("cargaTemaActivosPorAspecto",{"idAspecto":idAspecto},function(data){
		if (data.lista.length >0){
			dataTemas = data.lista;
			$("#id_tema").empty();
			$("#id_tema").append("<option value='-1'>[ Tema ]</option>");
			$.each(data.lista,function(i, obj){
				$("#id_tema").append("<option value='" + obj.idTema+ "'>"+obj.nombre+"</option>");
			});	
		}
	});
});



$("#id_tema").change(function(){
	var idTema = $("#id_tema").val();
	if (idTema == -1){
		$('#id_definicion').html("");
		$('#id_label_definicion').html("");
	}else{
		$.each(dataTemas,function(i, obj){
			if (obj.idTema == idTema){
				$('#id_definicion').html(obj.definicion);
				$('#id_label_definicion').html("Definición :");
			}
		});	
	}
});

function mostrar(idMensaje, nps, mensaje, tipo){
	console.log(idMensaje);
	console.log(nps);
	console.log(mensaje);
	console.log(tipo);
	
	$('#id_div_modal_tema').modal("show");
	
	if (nps<6){
		$('#id_text_pregunta').html("¿En qué te hemos fallado?");	
	}else if (nps<9){
		$('#id_text_pregunta').html("¿Qué debemos mejorar?");	
	}else{
		$('#id_text_pregunta').html("¿Qué hemos hecho para merecer tu recomendación?");	
	}
	
	$('#id_text_respuesta').html(mensaje);
	$('#id_mensaje').val(idMensaje);
	
	agregarGrillaMensaje(null);
	
	$.getJSON("consultaMensajeValoracion",{"idMensaje":idMensaje}, function (data){
		dataGridMensaje = data.listaMensajeValoracion;
		agregarGrillaMensaje(data.listaMensajeValoracion);
	});
	
	console.log("tipo == NA " + (tipo == "NA"));
	if (tipo == "NA"){
		console.log("1");
		$('#id_chk_ninguno').prop('checked', true);
		console.log("2");
		$('#id_div_respuesta').hide();
    	$('#id_div_valoracion').hide();
	}else{
		console.log("2");
		$('#id_chk_ninguno').prop('checked', false);
		$('#id_div_respuesta').show();
		$('#id_div_valoracion').show();	
	}
	
	$('#id_aspecto').val("-1");
	$("#id_tema").empty();
	$("#id_tema").append("<option value='-1'>[ Tema ]</option>");
	$('#id_valoracion').val("-1");
	$('#id_definicion').html("");
	$('#id_label_definicion').html("");
	
}


function agregarGrillaMensaje(lista){
	$('#id_table_mensaje').DataTable().clear();
	$('#id_table_mensaje').DataTable().destroy();
	$('#id_table_mensaje').DataTable({
		    data: lista,
			language: IDIOMA,
			paging: false,
			searching:false,
			ordering:false,
			info:false,
			columns:[
				{data: "idMensaje",className:'text-center'},
				{data: "strAspecto"},
				{data: "strTema"},
				{data: "strValoracion"},
				{data: function(row, type, val, meta){
				    var salida='<button type="button" style="width: 90px" class="btn btn-warning btn-sm" onclick="eliminar(\''+row.idMensaje + '\',\'' + row.idAspecto + '\',\''+ row.idTema + '\')">Eliminar</button>';
					return salida;
				},className:'text-center'},			
			]                                     
	    });
}


function eliminar(idMensaje,idAspecto,idTema){	
	var array =[idMensaje,idAspecto,idTema];
	mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminar,null,array);
}

function accionEliminar(array){	
	$('#id_eli_mensaje').val(array[0]);
	$('#id_eli_aspecto').val(array[1]);
	$('#id_eli_tema').val(array[2]);
    $.ajax({
          type: "POST",
          url: "eliminaMensajeValoracion", 
          data: $('#id_form_elimina').serialize(),
          success: function(data){
        	   dataGridMensaje = data.listaMensajeValoracion;
        	   agregarGrillaMensaje(data.listaMensajeValoracion);
        	   var idEstado = $("#id_estado").val();
        	   $.getJSON("listadoMensajePorEstado",{"idEstado":idEstado}, function (data){
        			agregarGrilla(data.listaMensaje, getTipoMensaje(idEstado));
        	   });
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
     });
}

$("#id_btn_registra").click(function(){

	var id_aspecto = $('#id_aspecto').val();
	var id_tema = $('#id_tema').val();
	var id_valoracion = $('#id_valoracion').val();
	
	if (id_aspecto == -1){
		 mostrarMensaje("Seleccione el Aspecto");
		 return;
	}
	if (id_tema == -1){
		 mostrarMensaje("Seleccione el Tema");
		 return;
	}
	if (id_valoracion == -1){
		 mostrarMensaje("Seleccione la Valoración");
		 return;
	}
	
	var noExisteValoracion = true;
	for (var i=0; i<dataGridMensaje.length; i++){
		if (dataGridMensaje[i].idAspecto == id_aspecto &&  dataGridMensaje[i].idTema == id_tema){
			noExisteValoracion = false;
		}
	}
	
	if (noExisteValoracion){
			$.ajax({
		          type: "POST",
		          url: "registraMensajeValoracion", 
		          data: $('#id_form_registra').serialize(),
		          success: function(data){
		        	   dataGridMensaje = data.listaMensajeValoracion;
			       	   agregarGrillaMensaje(data.listaMensajeValoracion);
			    	   var idEstado = $("#id_estado").val();
			    	   $.getJSON("listadoMensajePorEstado",{"idEstado":idEstado}, function (data){
			    			agregarGrilla(data.listaMensaje, getTipoMensaje(idEstado));
			    	   });
			    	   $('#id_aspecto').val("-1");
			    	   $('#id_tema').val("-1");
			    	   $('#id_valoracion').val("-1");
			    	   $('#id_definicion').html("");
			   		   $('#id_label_definicion').html("");
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
	}else{
		 mostrarMensaje("Ya existe el aspecto y tema seleccionado");
	}
    
});



</script>   		
</body>
</html>