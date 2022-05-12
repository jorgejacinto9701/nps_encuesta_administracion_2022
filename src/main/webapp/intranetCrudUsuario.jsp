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
<h5><b>Actualiza Usuario</b></h5>
</div>

<div class="container" style="margin-top: 1%">

			   <form id="id_form_elimina" >
			  		<input type="hidden" id="id_elimina" name="id">
			  		<input type="hidden" id="id_eli_estado" name="idEstado">
			   </form>	
				
				<form  id="id_form" action="listadoMensajeClasificadoresExcel" class="fileDownloadForm"  method="post" enctype="multipart/form-data">
			       		<div class="row" style="height: 70px">
							<div class="col-md-2" >
									<input class="form-control" id="id_txt_filtro"  name="filtro" placeholder="Ingrese el nombre" type="text" maxlength="30"/>
							</div>
							<div class="col-md-4" >
								<div class="col-md-4" >
									<button type="button" class="btn btn-primary btn-sm" id="id_btn_filtrar" style="width: 100px">
										<i style="font-size: 15px;" class="fa fa-search"></i> Filtrar
									</button>
								</div>
								<div class="col-md-4">
									<button type="button" class="btn btn-primary btn-sm" data-toggle='modal'  data-target="#id_div_modal_registra"   style="width: 100px">
										<i style="font-size: 15px;" class="fa fa-save"></i>&nbsp;Registrar
									</button>
								</div>
								<div class="col-md-4">
									<button  type="submit" class='btn btn-primary btn-sm' id="id_ver_reporte" style="width: 100px">
										<i style="font-size: 15px;" class="fa fa-download"></i> Reporte
									</button>
								</div>
							
							</div>
							<div class="col-md-4">
									<input class="form-control" id="id_formato"name="formato" placeholder="Ingrese el formato" type="file"/>
							</div>
							<div class="col-md-2">
									<button  type="button"  id="id_btn_subir" class='btn btn-primary btn-sm' style="width: 150px;">
										<i style="font-size: 15px;" class="fa fa-upload"></i> Subir Usuarios
									</button>
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
												<th style="width: 20%">Nombre</th>
												<th style="width: 7%">Login</th>
												<th style="width: 11%">Negocio</th>
												<th style="width: 11%">Sede</th>
												<th style="width: 6%">Asignados</th>
												<th style="width: 6%">Clasificados</th>
												<th style="width: 6%">Pendientes</th>
												<th style="width: 6%">Avance</th>
												<th style="width: 6%">Estado</th>
												<th style="width: 8%"></th>
												<th style="width: 8%"></th>
											</tr>
										</thead>
											<tbody></tbody>
										</table>	
								</div>	
						</div>
					</div>
  
  	 <div class="modal fade" id="id_div_modal_registra" >
			<div class="modal-dialog" style="width: 60%">
				<div class="modal-content">
				<div class="modal-header" >
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Usuario</h4>
				</div>
				<div class="modal-body" >
						<form id="id_form_registra" accept-charset="UTF-8" class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        <div class="panel panel-default">
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_nombre">Nombre</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_reg_nombre" name="usuario.nombre" placeholder="Ingrese el Nombre" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_login">Login</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_reg_login" name="usuario.login" placeholder="Ingrese el Login" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_clave">Clave</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_reg_clave" name="usuario.clave" placeholder="Ingrese la clave" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_negocio">Negocio</label>
		                                        <div class="col-lg-5">
													 <select id="id_reg_negocio" class='form-control'>
							                            	<option value=" ">[ Seleccione ]</option>
							                         </select>
		                                        </div>
		                                    </div>  
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_sede" >Sede</label>
		                                        <div class="col-lg-5">
													 <select id="id_reg_sede" class='form-control' name="usuario.idSede">
							                            	<option value=" ">[ Seleccione ]</option>
							                         </select>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <div class="col-lg-12" align="center">
		                                        	<button type="button" style="width: 80px" id="id_btn_registra" class="btn btn-primary btn-sm">Registrar</button>
		                                        	<button type="button" style="width: 80px" id="id_btn_reg_cancelar" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
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
  
		 <div class="modal fade" id="id_div_modal_actualiza" >
			<div class="modal-dialog" style="width: 60%">
				<div class="modal-content">
				<div class="modal-header" >
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Usuario</h4>
				</div>
				<div class="modal-body" >
						<form id="id_form_actualiza" accept-charset="UTF-8"   class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        <div class="panel panel-default">
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_ID">ID</label>
		                                        <div class="col-lg-5">
		                                           <input class="form-control" id="id_ID" readonly="readonly" name="usuario.idUsuario" type="text" maxlength="8"/>
		                                        </div>
		                                     </div>
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_nombre">Nombre</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_act_nombre" name="usuario.nombre" placeholder="Ingrese el Nombre" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_login">Login</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_act_login" name="usuario.login" placeholder="Ingrese el Login" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_clave">Clave</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_act_clave" name="usuario.clave" placeholder="Ingrese la clave" type="text" maxlength="100"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_negocio">Negocio</label>
		                                        <div class="col-lg-5">
													 <select id="id_act_negocio" class='form-control'>
							                            	<option value=" ">[ Seleccione ]</option>
							                         </select>
		                                        </div>
		                                    </div>  
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_sede">Sede</label>
		                                        <div class="col-lg-5">
													 <select id="id_reg_sede" class='form-control' name="usuario.idSede">
							                            	<option value=" ">[ Seleccione ]</option>
							                         </select>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_estado">Estado</label>
		                                        <div class="col-lg-5">
													<select id="id_act_estado" name="usuario.estado" class='form-control'>
							                            	<option value=" ">[ Seleccione ]</option>
							                            	<option value="1">Activo</option>  
							                            	<option value="0">Inactivo</option>      
							                         </select>
		                                        </div>
		                                    </div>  
		                                    <div class="form-group">
		                                        <div class="col-lg-12" align="center">
		                                        	<button type="button" style="width: 80px" id="id_btn_actualiza" class="btn btn-primary btn-sm">Actualizar</button>
		                                        	<button type="button" style="width: 80px" id="id_btn_act_cancelar" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
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

$.getJSON("cargaNegocios",{},function(data){
	$.each(data.listaNegocio,function(i, obj){
		$("#id_reg_negocio").append("<option value='" + obj.idNegocio+ "'>"+obj.nombre+"</option>");
		$("#id_act_negocio").append("<option value='" + obj.idNegocio+ "'>"+obj.nombre+"</option>");
	});
});

$("#id_reg_negocio").change(function(){
	var idNegocio = $("#id_reg_negocio").val();
	$("#id_reg_sede").empty();
	$("#id_reg_sede").append("<option value=' '>[ Seleccione ]</option>");
	$.getJSON("consultaSedePorNegocio",{"idNegocio":idNegocio}, function (data){
		$.each(data.listaSede,function(i, obj){
			$("#id_reg_sede").append("<option value='" + obj.idSede+ "'>"+obj.nombre+"</option>");
		});
	});
});

$("#id_act_negocio").change(function(){
	var idNegocio = $("#id_act_negocio").val();
	$("#id_act_sede").empty();
	$("#id_act_sede").append("<option value=' '>[ Seleccione ]</option>");
	$.getJSON("consultaSedePorNegocio",{"idNegocio":idNegocio}, function (data){
		$.each(data.listaSede,function(i, obj){
			$("#id_act_sede").append("<option value='" + obj.idSede+ "'>"+obj.nombre+"</option>");
		});
	});
});

$.getJSON("cargaUsuarios",{}, function (lista){
	agregarGrilla(lista.listaUsuario);
});

$("#id_btn_filtrar").click(function(){
	var fil=$("#id_txt_filtro").val();
	$.getJSON("consultaUsuarioLike",{filtro:fil}, function (data){
		agregarGrilla(data.lista);
	});
});

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
				{data: "idUsuario",className:'text-center'},
				{data: "nombre"},
				{data: "login"},
				{data: "negocio"},
				{data: "sede"},
				{data: "asignados",className:'text-center'},
				{data: "clasificados",className:'text-center'},
				{data: "pendientes",className:'text-center'},
				{data: "avance",className:'text-center'},
				{data: function(row, type, val, meta){
					return getEstado(row.estado);
				},className:'text-center'},
				{data: function(row, type, val, meta){
					var salida='<button type="button" style="width: 90px" class="btn btn-info btn-sm" onclick="editar(\''+row.idUsuario + '\',\'' + row.nombre + '\',\'' + row.login + '\',\'' + row.clave + '\',\'' + row.correo + '\',\'' + row.estado + '\')">Editar</button>';
					return salida;
				},className:'text-center'},	
				{data: function(row, type, val, meta){
				    var salida='<button type="button" style="width: 90px" class="btn btn-warning btn-sm" onclick="eliminar(\''+row.idUsuario + '\',\'' + row.estado +'\')">'+ getTextoBotonEstado(row.estado) +'</button>';
					return salida;
				},className:'text-center'},													
			]                                     
	    });
}


$("#id_btn_subir").click(function(){
    var fileUrl = $("#id_formato").val();

    if(fileUrl == ''){
        mostrarMensaje(MSG_VALIDA_FILE_EXISTENCIA);
        return false;		
    }

    var extension = fileUrl.split('.').pop().toLowerCase();
    if(extension.toLowerCase() != 'xlsx'){
      mostrarMensaje(MSG_VALIDA_FILE_XLXS);
      return false;		
    }

    var formData = new FormData();
    var file = $('#id_formato')[0].files[0];
    formData.append("file", file);

    $('#id_btn_subir').attr('disabled','disabled');
    mostrarMensajeUpload(MSG_FILE_UPLOAD);
    
    $.ajax({
          type: "POST",
          url: "subirPlantillaUsuario", 
          data: formData,
          enctype : 'multipart/form-data',
          contentType : false,
          processData : false,
          cache:false,
          success: function(data){
       		  $('#id_my_modal_upload').modal("hide");
       		  $('#id_btn_subir').removeAttr('disabled');
       		  agregarGrilla(data.salida.lista);
       		  mostrarMensaje(data.salida.mensaje);
       	  },
          error: function(){
        	  $('#id_btn_subir').removeAttr('disabled');
        	  $('#id_my_modal_upload').modal("hide");
        	  mostrarMensaje(MSG_ERROR);
          }
    });
});


function eliminar(id,idEstado){	
	var array =[id,idEstado];
	mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminar,null,array);
}

function accionEliminar(array){	
	$('#id_elimina').val(array[0]);
	$('#id_eli_estado').val(getCambioEstado(array[1]));
    $.ajax({
          type: "POST",
          url: "eliminaCrudUsuario", 
          data: $('#id_form_elimina').serialize(),
          success: function(data){
        	  agregarGrilla(data.lista);
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
     });
}

function editar(id,nombre,login, clave, correo,estado){	
	$('#id_ID').val(id);
	$('#id_act_nombre').val(nombre);
	$('#id_act_login').val(login);
	$('#id_act_clave').val(clave);
	$('#id_act_correo').val(correo);
	$('#id_act_estado').val(estado);
	$('#id_div_modal_actualiza').modal("show");
}

function limpiarFormulario(){	
	$('#id_reg_nombre').val("");
}

$("#id_btn_registra").click(function(){
	var validator = $('#id_form_registra').data('bootstrapValidator');
    validator.validate();
	
    if (validator.isValid()) {
        $.ajax({
          type: "POST",
          url: "registraCrudUsuario", 
          data: $('#id_form_registra').serialize(),
          success: function(data){
        	  agregarGrilla(data.lista);
        	  $('#id_div_modal_registra').modal("hide");
        	  limpiarFormulario();
        	  validator.resetForm();
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
        });
        
    }
});


$("#id_btn_actualiza").click(function(){
	var validator = $('#id_form_actualiza').data('bootstrapValidator');
    validator.validate();
	
    if (validator.isValid()) {
        $.ajax({
          type: "POST",
          url: "actualizaCrudUsuario", 
          data: $('#id_form_actualiza').serialize(),
          success: function(data){
        	  agregarGrilla(data.lista);
        	  $('#id_div_modal_actualiza').modal("hide");
          },
          error: function(){
        	  mostrarMensaje(MSG_ERROR);
          }
        });
        
    }
});

$('#id_form_registra').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	
    }   
});


$(document).on("submit", "form.fileDownloadForm", function (e) {
    $.fileDownload($(this).prop('action'), {
        preparingMessageHtml: "Estamos preparando su informe, por favor espere",
        failMessageHtml: "Se produjo un problema al generar su informe. Vuelva a intentarlo.",
        httpMethod: "POST",
        data: $(this).serialize()
    });
    e.preventDefault(); 	
});



$('#id_form_actualiza').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	
        }   
});




</script>   		
</body>
</html>