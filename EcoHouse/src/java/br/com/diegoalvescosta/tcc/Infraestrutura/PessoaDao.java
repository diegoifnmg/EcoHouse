/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diegoalvescosta.tcc.Infraestrutura;

import br.com.diegoalvescosta.tcc.DomainModel.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author diego-dell
 */
@Stateless
public class PessoaDao extends GenericoDAO<Pessoa>{

    
    public PessoaDao() {
        super(Pessoa.class);
    }
    
    
    @Override
    public List<Pessoa> Buscar(Pessoa obj) {
        String Consulta = "select l from Pessoa l";
        if (obj != null) {
            Consulta = Consulta + " where l.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }
    
}
