<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
<link rel="shortcut icon" href="images/cibertec1.png" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrapValidator.js"></script>
<link rel="shortcut icon" href="images/cibertec1.png" />
<link rel="stylesheet" href="css/form-elements.css">
<link rel="stylesheet" href="css/login.intranet.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrapValidator.css">
<title>CIBERTEC</title>
</head>   
<body>    
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">

                	<c:if test="${requestScope.mensaje != null}">
               		<div class="alert alert-danger fade in" id="success-alert">
				        <a href="#" class="close" data-dismiss="alert">&times;</a>
				        <strong>${requestScope.mensaje}</strong>
				    </div>
				    </c:if>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Sistema de encuestas</h3>
                            		<p>Ingrese su usuario y contraseña:</p>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form id="id_form"  action="loginUsuario" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Usuario</label>
			                        	<input type="text" name="usuario.login" placeholder="Usuario" class="form-username form-control" id="id_login">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Contraseña</label>
			                        	<input type="password" name="usuario.clave" placeholder="Contraseña" class="form-password form-control"  id="id_clave">
			                        </div>
			                        <button type="submit" class="btn btn-info btn-md">Ingresar</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>
<script type="text/javascript">
$("#success-alert").fadeTo(1000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
</script>

<script type="text/javascript">
$(document).ready(function() {
    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	"usuario.login": {
        		selector:"#id_login",
                validators: {
                    notEmpty: {
                        message: 'El usuario es un campo obligatorio'
                    }
                }
            },
            "usuario.clave": {
            	selector:"#id_clave",
                validators: {
                    notEmpty: {
                        message: 'La contraseña es un campo obligatorio'
                    }
                }
            }
        }   
    });

    // Validate the form manually
    $('#validateBtn').click(function() {
        $('#id_form').bootstrapValidator('validate');
    });
});
</script>

</body>
</html>