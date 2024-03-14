// se almacena la url de la API
let url = "http://localhost:8080/api/v1/medico/";

// Función para listar los médicos
function listarMedico() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            let cuerpoTabla = document.getElementById("cuerpoTabla");
            cuerpoTabla.innerHTML = "";

            for (let i = 0; i < result.length; i++) {
                let trRegistro = document.createElement("tr");
                trRegistro.innerHTML = `
                    <td>${result[i]["id_medico"]}</td>
                    <td class="text-center align-middle">${result[i]["documento_identidad"]}</td>
                    <td class="text-center align-middle">${result[i]["primer_nombre"]}</td>
                    <td class="text-center align-middle">${result[i]["segundo_nombre"]}</td>
                    <td class="text-center align-middle">${result[i]["primer_apellido"]}</td>
                    <td class="text-center align-middle">${result[i]["segundo_apellido"]}</td>
                    <td class="text-center align-middle">${result[i]["celular"]}</td>
                    <td class="text-center align-middle">${result[i]["correo"]}</td>
                    <td class="text-center align-middle">${result[i]["estado"]}</td>
                    <td class="text-center align-middle">
    <button class="btn btn-link" data-toggle="modal" data-target="#userModal" onclick="registrarMedico();" data-id="${result[i]["id_medico"]}">Editar
    </button>
    <button class="btn btn-link" onclick="cambiarEstado(${result[i]["id_medico"]})" data-id="${result[i]["id_medico"]}">Desahabilitar
    </button>
</td>

                `;
                cuerpoTabla.appendChild(trRegistro);
            }
        },
        error: function (error) {
            alert("Error en la petición: " + error);
        }
    });
}

let registrarMedicoBandera = true;

// Función para registrar un médico
function registrarMedico() {
    let forData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("celular").value,
        "correo": document.getElementById("correo").value,
        "estado": document.getElementById("estado").value,
    };
    let metodo = "";
    let urlLocal = "";
    let textoimprimir = "";
    if (registrarMedicoBandera == true) {
        metodo = "POST";
        urlLocal = url;
    } else {
        metodo = "PUT";
        urlLocal = url + id_medico;
    }
    if (validarCampos()) {
        $.ajax({
            url: urlLocal,
            type: metodo,
            data: forData,
            success: function (result) {
                $('#exampleModal').modal('hide');
                listarMedico();
            },
            error: function (error) { // Aquí es donde se espera una variable "error"
                if (error.responseJSON && error.responseJSON.message) {
                } else {
                    alert("Error al guardar: " + error.statusText);
                }
            }
        });
    } else {
    }
}


// Función para validar campos
function validarCampos() {
    let documentoIdentidad = document.getElementById("documento_identidad");
    return validarDocumentoIdentidad(documentoIdentidad);
}

// Función para validar el documento de identidad
function validarDocumentoIdentidad(cuadroNumero) {
    let valor = cuadroNumero.value;
    let valido = true;

    if (valor.length < 5 || valor.length > 11) {
        valido = false;
    }


    return valido;
}



// Función para limpiar campos del formulario
function limpiar() {
    document.getElementById("documento_identidad").value = "";
    document.getElementById("documento_identidad").className="form-control";
    document.getElementById("primer_nombre").value = "";
    document.getElementById("primer_nombre").className="form-control";
    document.getElementById("segundo_nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("primer_apellido").className="form-control";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("celular").value = "";
    document.getElementById("celular").className="form-control";
    document.getElementById("correo").value = "";
    document.getElementById("correo").className="form-control";
    document.getElementById("estado").value = "";
    document.getElementById("estado").className="form-control";
}

let id_medico = "";
// Asociar eventos de clic a los iconos dentro de la tabla
$(document).on("click", ".editar", function () {
    limpiar();
    id_medico = $(this).data("id");

    $.ajax({
        url: url + id_medico,
        type: "GET",
        success: function (medico) {
            document.getElementById("documento_identidad").value = medico.documento_identidad;
            document.getElementById("primer_nombre").value = medico.primer_nombre;
            document.getElementById("segundo_nombre").value = medico.segundo_nombre;
            document.getElementById("primer_apellido").value = medico.primer_apellido;
            document.getElementById("segundo_apellido").value = medico.segundo_apellido;
            document.getElementById("celular").value = medico.celular;
            document.getElementById("correo").value = medico.correo;
            document.getElementById("estado").value = medico.estado;
            registrarMedicoBandera = false; // Cambiar la bandera a false para indicar que se está editando
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del médico: " + error.statusText);
        }
    });
});

// Función para editar un médico
function editarMedico() {
    let forData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("celular").value,
        "correo": document.getElementById("correo").value,
        "estado": document.getElementById("estado").value,
    };

    $.ajax({
        url: url + id_medico,
        type: "PUT",
        data: forData,
        success: function (result) {
            $('#exampleModal').modal('hide');
            listarMedico();
        },
        error: function (error) {
            alert("Error al actualizar el médico: " + error.statusText);
        }
    });
}


$(document).on("click", ".cambiarEstado", function () {
    let id_medico = $(this).data("id");
    $.ajax({
        url: url + id_medico,
        type: "DELETE",
        success: function(){
            
            alert("Error al guardar: " + error.statusText);
            listarMedico(); // Actualiza la lista de pacientes en el front-end
        }
    });
});



// Llamar a la función para listar médicos al cargar la página
$(document).ready(function () {
    listarMedico();
});
function actualizarlistarMedico() {
    listarMedico();
}