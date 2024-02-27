package com.example.hospitalNuevo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ingreso")
public class ingreso {

  

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_ingreso", nullable = false, length = 36)
    private String id_ingreso;

    @Column(name = "habitacion", nullable = false, length = 36)
    private String habitacion;

    @Column(name = "cama", nullable = false, length = 36)
    private String cama;

    @ManyToOne
    @JoinColumn(name="id_paciente")
    private paciente paciente;

    @ManyToOne
    @JoinColumn(name="id_medico")
    private medico medico;

    @Column(name = "fecha_ingreso", nullable = false, length = 36)
    private Date  fecha_ingreso;

    @Column(name = "fecha_salida", nullable = false, length = 36)
    private Date fecha_salida;

    @Column(name = "Estado", nullable = false, length = 1)
    private String Estado;

	public ingreso() {
		super();
	}

	public ingreso(String id_ingreso, String habitacion, String cama, com.example.hospitalNuevo.model.paciente paciente,
			com.example.hospitalNuevo.model.medico medico, Date fecha_ingreso, Date fecha_salida, String estado) {
		super();
		this.id_ingreso = id_ingreso;
		this.habitacion = habitacion;
		this.cama = cama;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_salida = fecha_salida;
		Estado = estado;
	}

	public String getId_ingreso() {
		return id_ingreso;
	}

	public void setId_ingreso(String id_ingreso) {
		this.id_ingreso = id_ingreso;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public String getCama() {
		return cama;
	}

	public void setCama(String cama) {
		this.cama = cama;
	}

	public paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(paciente paciente) {
		this.paciente = paciente;
	}

	public medico getMedico() {
		return medico;
	}

	public void setMedico(medico medico) {
		this.medico = medico;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

    
}