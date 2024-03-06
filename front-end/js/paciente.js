// URL de la API para pacientes
let url = "http://localhost:8080/api/v1/paciente/";

// Función para listar pacientes
function listarPaciente() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            let cuerpoTabla = document.getElementById("userTable").getElementsByTagName("tbody")[0];
            cuerpoTabla.innerHTML = ""; // Limpiar el cuerpo de la tabla
            result.forEach(paciente => {
                let trPaciente = document.createElement("tr");
                Object.values(paciente).forEach(valor => {
                    let celda = document.createElement("td");
                    celda.textContent = valor;
                    trPaciente.appendChild(celda);
                });
                cuerpoTabla.appendChild(trPaciente);
            });
        },
        error: function (error) {
            alert("Error en la petición: " + error);
        }
    });
}

// Función para registrar un nuevo paciente
function registrarPaciente() {
    let formData = {
        "documento_identidad": document.getElementById("documento_identidad").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("celular").value,
        "correo": document.getElementById("correo").value,
        "nombre_persona_contacto": document.getElementById("nombre_persona_contacto").value,
        "telefono_persona_contacto": document.getElementById("telefono_persona_contacto").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url,
            type: "POST",
            data: formData,
            success: function (result) {
                alert("Paciente registrado correctamente");
                listarPaciente(); // Actualizar la lista de pacientes después de registrar uno nuevo
                limpiar();
            },
            error: function (error) {
                alert("Error al registrar el paciente: " + error);
            }
        });
    }
}

// Función para validar los campos del formulario de registro de paciente
function validarCampos() {
    let documentoIdentidad = document.getElementById("documento_identidad").value;
    // Agrega aquí las validaciones necesarias para los otros campos si es necesario

    // Validación simple para el documento de identidad
    if (documentoIdentidad.length < 1) {
        alert("El campo Documento de Identidad es obligatorio");
        return false;
    }

    return true;
}

// Función para limpiar los campos del formulario de registro de paciente
function limpiar() {
    document.getElementById("documento_identidad").value = "";
    document.getElementById("primer_nombre").value = "";
    document.getElementById("segundo_nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("celular").value = "";
    document.getElementById("correo").value = "";
    document.getElementById("nombre_persona_contacto").value = "";
    document.getElementById("telefono_persona_contacto").value = "";
}
