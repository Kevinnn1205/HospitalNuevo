//se almacena la url de la API
let url = "http://localhost:8080/api/v1/ingreso/";
function listarIngreso() {
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

                let celdahabitacion = document.createElement("td");
                let celdacama = document.createElement("td");
                let celdapaciente = document.createElement("td");
                let celdamedico= document.createElement("td");
                let celdafecha_ingreso = document.createElement("td");
                let celdafecha_salida = document.createElement("td");
                let celdaEstado = document.createElement("td");
                celdaId.innerText = element["id_ingreso"];
               
                //se agrega la celda al registro una linea por cada campo 

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdahabitacion);
                trRegistro.appendChild(celdacama);
                trRegistro.appendChild(celdapaciente);
                trRegistro.appendChild(celdamedico);
                trRegistro.appendChild(celdafecha_ingreso);
                trRegistro.appendChild(celdafecha_salida);
                trRegistro.appendChild(celdaEstado);
                
                //se agrega el registro en la tabla 

                cuerpoTabla.appendChild(trRegistro);
                celdahabitacion.innerText = element["habitacion"];
                celdacama.innerText = element["cama"];
                celdapaciente.innerText = element["paciente"]["primer_nombre"];
                celdamedico.innerText = element["medico"]["primer_nombre"];
                celdafecha_ingreso.innerText = element["fecha_ingreso"];
                celdafecha_salida.innerText = element["fecha_salida"];
                celdaEstado.innerText = element["estado"];
                



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
function registrarIngreso() {
    let forData = {
        "habitacion": document.getElementById("habitacion").value,
        "cama": document.getElementById("cama").value,
        "paciente": document.getElementById("paciente").value,
        "medico": document.getElementById("medico").value,
        "fecha_ingreso": document.getElementById("fecha_ingreso").value,
        "fecha_salida": document.getElementById("fecha_salida").value,
        "estado": document.getElementById("estado").value
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

    document.getElementById("habitacion").value = "";
    document.getElementById("cama").value = "";
    document.getElementById("paciente").value = "";
    document.getElementById("medico").value = "";
    document.getElementById("fecha_ingreso").value = "";
    document.getElementById("fecha_salida").value = "";
    document.getElementById("estado").value = "";

}

