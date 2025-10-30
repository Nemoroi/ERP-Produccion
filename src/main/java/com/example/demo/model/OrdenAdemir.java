package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orden_fabricacion")
@Getter @Setter

public class OrdenAdemir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OrdenID")
	private Integer idOrden;
	
	@Column(name="Codigo_Orden")
	private Integer codOrden;
	
	@ManyToOne
	@JoinColumn(name="ProductoID")
	private ProductoAdemir producto;
	
	@Column(name="Cantidad_Planificada")
	private Double cantPlan;
	
	@Column(name="Cantidad_Fabricada")
	private Double cantFab;
	
	@Column(name="Rendimiento")
	private Double rend;

}
