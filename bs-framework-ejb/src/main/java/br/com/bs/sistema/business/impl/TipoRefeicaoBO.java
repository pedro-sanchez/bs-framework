package br.com.bs.sistema.business.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bs.fw.business.impl.GenericBO;
import br.com.bs.sistema.business.iface.ITipoRefeicaoBO;
import br.com.bs.sistema.entity.TipoRefeicao;
import br.com.bs.sistema.repository.TipoRefeicaoDAO;

@Local
@Stateless
public class TipoRefeicaoBO extends GenericBO<TipoRefeicao, TipoRefeicaoDAO> implements ITipoRefeicaoBO {

}