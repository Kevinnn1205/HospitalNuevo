//se almacena la url de la API
let url = "http://localhost:8080/api/v1/paciente/";
function listarPaciente() {
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
            let cuerpoTabla = document.getElementById("cuerpoTabla");
            //se limpia el cuerpo de la tabla
            cuerpoTabla.innerHTML = "";
            //Se hace un ciclo que recorra 
            //el arreglo con los datos
            for (const element of result) {
                //se crea una etiqueta tr por
                //cada registro
                let trRegistro = document.createElement("tr");
                let celdaId = document.createElement("td");

                //creamos un td por cada campo de registro

                let celdaDocumento_identidad = document.createElement("td");
                let celdaPrimer_nombre = document.createElement("td");
                let celdaSegundo_nombre = document.createElement("td");
                let celdaPrimer_apellido= document.createElement("td");
                let celdaSegundo_apellido = document.createElement("td");
                let celdaTelefono = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaNombre_persona_contacto = document.createElement("td");
                let celdaTelefono_persona_contacto = document.createElement("td");
                celdaId.innerText = element["id_paciente"];
               
                //se agrega la celda al registro una linea por cada campo 

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaDocumento_identidad);
                trRegistro.appendChild(celdaPrimer_nombre);
                trRegistro.appendChild(celdaSegundo_nombre);
                trRegistro.appendChild(celdaPrimer_apellido);
                trRegistro.appendChild(celdaSegundo_apellido);
                trRegistro.appendChild(celdaTelefono);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaNombre_persona_contacto);
                trRegistro.appendChild(celdaTelefono_persona_contacto);
                
                //se agrega el registro en la tabla 

                cuerpoTabla.appendChild(trRegistro);
                celdaDocumento_identidad.innerText = element["documento_identidad"];
                celdaPrimer_nombre.innerText = element["primer_nombre"];
                celdaSegundo_nombre.innerText = element["segundo_nombre"];
                celdaPrimer_apellido.innerText = element["primer_apellido"];
                celdaSegundo_apellido.innerText = element["segundo_apellido"];
                celdaTelefono.innerText = element["telefono"];
                celdaCorreo.innerText = element["correo"];
                celdaNombre_persona_contacto.innerText = element["nombre_persona_contacto"];
                celdaTelefono_persona_contacto.innerText = element["telefono_persona_contacto"];



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
function registrarpaciente() {
    let forData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "telefono": document.getElementById("telefono").value,
        "correo": document.getElementById("correo").value,
        "nombre_persona_contacto": document.getElementById("nombre_persona_contacto").value,
        "telefono_persona_contacto": document.getElementById("telefono_persona_contacto").value,
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
    let cama = document.getElementById("cama");
    return validarCama(cama);
}

function validarCama(cuadroNumero) {



    let valor = cuadroNumero.value;
    let valido = true;
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
    document.getElementById("nombre_persona_contacto").value = "";
    document.getElementById("telefono_persona_contacto").value = "";

}