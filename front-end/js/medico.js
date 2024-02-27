//se almacena la url de la API
var url = "http://localhost:8080/api/v1/medico/";
function listarMedico() {
    //metodo para alistar los medicos
    //se crea la peticion AJAX
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            //success: funcion que se ejecuta 
            //cuando la peticion tiene exito
            console.log(result);
            //se crea un objeto que contenga
            //el cuerpo de la tabla
            var cuerpoTabla = document.getElementById("cuerpoTabla");
            //se limpia el cuerpo de la tabla
            cuerpoTabla.innerHTML = "";
            //Se hace un ciclo que recorra 
            //el arreglo con los datos
            for (var i = 0; i < result.length; i++) {
                //se crea una etiqueta tr por
                //cada registro
                var trRegistro = document.createElement("tr");
                let celdaId = document.createElement("td");

                //creamos un td por cada campo de registro

                let celdaDocumentoIdentidad = document.createElement("td");
                let celdaPrimerNombre = document.createElement("td");
                let celdaSegundoNombre = document.createElement("td");
                let celdaPrimerApellido = document.createElement("td");
                let celdaSegundoApellido = document.createElement("td");
                let celdaCelular = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaEstado = document.createElement("td");

                celdaId.innerText = result[i]["id_medico"];
               
                //se agrega la celda al registro una linea por cada campo 

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaDocumentoIdentidad);
                trRegistro.appendChild(celdaPrimerNombre);
                trRegistro.appendChild(celdaSegundoNombre);
                trRegistro.appendChild(celdaPrimerApellido);
                trRegistro.appendChild(celdaSegundoApellido);
                trRegistro.appendChild(celdaTelefono);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaEstado);
                
                //se agrega el registro en la tabla 

                cuerpoTabla.appendChild(trRegistro);
                celdaDocumentoIdentidad.innerText = result[i]["documento_identidad"];
                celdaPrimerNombre.innerText = result[i]["primer_nombre"];
                celdaSegundoNombre.innerText = result[i]["segundo_nombre"];
                celdaPrimerApellido.innerText = result[i][" primer_apellido"];
                celdaSegundoApellido.innerText = result[i]["segundo_apellido"];
                celdaCelular.innerText = result[i]["telefono"];
                celdaCorreo.innerText = result[i]["correo"];
                celdaEstado.innerText = result[i]["estado"];



            }
        },
        error: function (error) {
            //error: funcion que se ejecuta 
            //cuando la peticion tiene un error
            alert("Error en la peticion ${error}");

        }

    });
}

//se almacenan los valores
function registrarMedico() {
    let forData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("telefono").value,
        "correo": document.getElementById("correo").value,
        "estado": document.getElementById("estado").value,
    };
    if (validarCampos()) {
        //se ejecuta la peticion
        $.ajax({

            url: url,
            type: "POST",
            data: forData,

            success: function (result) {
               alert("se guardo con exito". success);
            },
            error: function (error) {
                //error
                alert("Error al guardar", error);
            }
        });
    }
}

//Validar campo de documento de identidad paciente
function validarCampos() {
    var cama = document.getElementById("documento_identidad");
    return validarDocumentoIdentidad(cama);
}

function validarDocumentoIdentidad(cuadroNumero) {



    var valor = cuadroNumero.value;
    var valido = true;
    if (valor.length <= 1 || valor.length > 11) {
        valido = false;
    }

    if (valido) {
        //cuadro de texto cumple
        //se modifica la clase del cuadro de texto
        cuadroNumero.className = "form-control is-valid";
    } else {
        //cuadro de texto no cumple
        cuadroNumero.className = "form-control is-invalid"
    }
    return valido
}

function limpiar() {

    document.getElementById("documento_identidad").value = "";
    document.getElementById("primer_nombre").value = "";
    document.getElementById("segundo_nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("telefono").value = "";
    document.getElementById("correo").value = "";
    document.getElementById("estado").value = "";

}