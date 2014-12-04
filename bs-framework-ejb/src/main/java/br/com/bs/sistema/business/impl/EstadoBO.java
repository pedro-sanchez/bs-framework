package br.com.bs.sistema.business.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bs.fw.business.impl.GenericBO;
import br.com.bs.sistema.business.iface.IEstadoBO;
import br.com.bs.sistema.entity.Estado;
import br.com.bs.sistema.repository.EstadoDAO;

@Local
@Stateless
public class EstadoBO extends GenericBO<Estado, EstadoDAO> implements IEstadoBO {
	
}
