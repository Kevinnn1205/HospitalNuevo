// se almacena la url de la API
let url = "http://localhost:8080/api/v1/medico/";

function ListarMedico() {
    // método para alistar los médicos
    // se crea la petición AJAX
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            // success: función que se ejecuta 
            // cuando la petición tiene éxito
            console.log(result);
            // se crea un objeto que contenga
            // el cuerpo de la tabla
            let cuerpoTabla = document.getElementById("cuerpoTabla");
            // se limpia el cuerpo de la tabla
            cuerpoTabla.innerHTML = "";
            // Se hace un ciclo que recorra 
            // el arreglo con los datos
            for (const element of result) {
                // se crea una etiqueta tr por
                // cada registro
                let trRegistro = document.createElement("tr");
                let celdaId = document.createElement("td");

                // creamos un td por cada campo de registro

                let celdaDocumento_identidad = document.createElement("td");
                let celdaPrimer_nombre = document.createElement("td");
                let celdaSegundo_nombre = document.createElement("td");
                let celdaPrimer_apellido = document.createElement("td");
                let celdaSegundo_apellido = document.createElement("td");
                let celdaCelular = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaEstado = document.createElement("td");

                celdaId.innerText = element["id_medico"];

                // se agrega la celda al registro una linea por cada campo 

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaDocumento_identidad);
                trRegistro.appendChild(celdaPrimer_nombre);
                trRegistro.appendChild(celdaSegundo_nombre);
                trRegistro.appendChild(celdaPrimer_apellido);
                trRegistro.appendChild(celdaSegundo_apellido);
                trRegistro.appendChild(celdaCelular);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaEstado);

                // se agrega el registro en la tabla 

                cuerpoTabla.appendChild(trRegistro);
                celdaDocumento_identidad.innerText = element["documento_identidad"];
                celdaPrimer_nombre.innerText = element["primer_nombre"];
                celdaSegundo_nombre.innerText = element["segundo_nombre"];
                celdaPrimer_apellido.innerText = element["primer_apellido"];
                celdaSegundo_apellido.innerText = element["segundo_apellido"];
                celdaCelular.innerText = element["celular"];
                celdaCorreo.innerText = element["correo"];
                celdaEstado.innerText = element["estado"];
            }
        },
        error: function (error) {
            // error: función que se ejecuta 
            // cuando la petición tiene un error
            alert("Error en la petición " + error);
        }
    });
}

// se almacenan los valores
function registrarMedico() {
    let formData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("celular").value,
        "correo": document.getElementById("correo").value,
        "estado": document.getElementById("estado").value,
    };
    if (validarCampos()) {
        // se ejecuta la petición
        $.ajax({
            url: url,
            type: "POST",
            data: formData,
            success: function (result) {
                alert("Registro exitoso");
                // Limpiar el formulario después de un registro exitoso
                limpiar();
                // Actualizar la lista de médicos después del registro
                ListarMedico();
            },
            error: function (xhr, status, error) {
                // Mostrar mensaje de error detallado
                alert("Error al guardar. Código de estado: " + xhr.status + ". Mensaje: " + xhr.responseText);
            }
        });
    }
}


// Validar campo de documento de identidad paciente
function validarCampos() {
    let documento_identidad = document.getElementById("documento_identidad");
    return validarDocumento_identidad(documento_identidad);
}

function validarDocumento_identidad(cuadroNumero) {
    let valor = cuadroNumero.value;
    let valido = true;
    if (valor.length <= 1 || valor.length > 11) {
        valido = false;
    }

    if (valido) {
        // cuadro de texto cumple
        // se modifica la clase del cuadro de texto
        cuadroNumero.className = "form-control is-valid";
    } else {
        // cuadro de texto no cumple
        cuadroNumero.className = "form-control is-invalid"
    }
    return valido;
}

function limpiar() {
    document.getElementById("documento_identidad").value = "";
    document.getElementById("primer_nombre").value = "";
    document.getElementById("segundo_nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("celular").value = "";
    document.getElementById("correo").value = "";
    document.getElementById("estado").value = "";
}
