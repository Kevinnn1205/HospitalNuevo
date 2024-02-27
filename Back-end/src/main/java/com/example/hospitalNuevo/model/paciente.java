package com.example.hospitalNuevo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "paciente")
public class paciente {

   

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_paciente", nullable = false, length = 36)
    private String id_paciente;

    @Column(name = "documento_identidad", nullable = false, length = 11)
    private String documento_identidad;

    @Column(name = "primer_nombre", nullable = false, length = 36)
    private String primer_nombre;

    @Column(name = "segundo_nombre", nullable = false, length = 36)
    private String segundo_nombre;

    @Column(name = "primer_apellido", nullable = false, length = 36)
    private String primer_apellido;

    @Column(name = "segundo_apellido", nullable = false, length = 36)
    private String segundo_apellido;

    @Column(name = "Celular", nullable = false, length = 13)
    private String Celular;

    @Column(name = "Correo", nullable = false, length = 100)
    private String Correo;

    @Column(name = "nombre_persona_contacto", nullable = false, length = 36)
    private String nombre_persona_contacto;

    @Column(name = "telefono_persona_contacto", nullable = false, length = 13)
    private String telefono_persona_contacto;

    @Column(name = "Estado", nullable = false, length = 1)
    private String Estado;

	public paciente() {
		super();
	}

	public paciente(String id_paciente, String documento_identidad, String primer_nombre, String segundo_nombre,
			String primer_apellido, String segundo_apellido, String celular, String correo,
			String nombre_persona_contacto, String telefono_persona_contacto, String estado) {
		super();
		this.id_paciente = id_paciente;
		this.documento_identidad = documento_identidad;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		Celular = celular;
		Correo = correo;
		this.nombre_persona_contacto = nombre_persona_contacto;
		this.telefono_persona_contacto = telefono_persona_contacto;
		Estado = estado;
	}

	public String getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(String id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getDocumento_identidad() {
		return documento_identidad;
	}

	public void setDocumento_identidad(String documento_identidad) {
		this.documento_identidad = documento_identidad;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getNombre_persona_contacto() {
		return nombre_persona_contacto;
	}

	public void setNombre_persona_contacto(String nombre_persona_contacto) {
		this.nombre_persona_contacto = nombre_persona_contacto;
	}

	public String getTelefono_persona_contacto() {
		return telefono_persona_contacto;
	}

	public void setTelefono_persona_contacto(String telefono_persona_contacto) {
		this.telefono_persona_contacto = telefono_persona_contacto;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

   
}