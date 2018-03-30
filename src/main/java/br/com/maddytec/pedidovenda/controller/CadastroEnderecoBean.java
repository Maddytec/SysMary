package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maddytec.pedidovenda.model.Fornecedor;
import br.com.maddytec.pedidovenda.model.Endereco;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Endereco endereco;
	private Endereco enderecoAEditar;
	private Endereco enderecoAExcluir;
	
	private CepServiceBean cepServiceBean;

	@Inject
	@FornecedorEdicao
	private Fornecedor fornecedor;

	public CadastroEnderecoBean() {
		this.endereco = new Endereco();
		this.setEnderecoAEditar(new Endereco());
		this.setEnderecoAExcluir(new Endereco());
	}

	@Inject
	private Event<FornecedorAlteradoEvent> fornecedorAlteradoEvent;

	
	
	public void adicionar() {
		 if (endereco != null && !isEnderecoEmpty(endereco)) {
		 this.fornecedor.getEnderecos().add(endereco);
		 this.endereco.setFornecedor(fornecedor);
		
		 endereco = new Endereco();
		 this.fornecedorAlteradoEvent.fire(new FornecedorAlteradoEvent(fornecedor));
		}
	}
	

	public void excluir() {
		if (enderecoAExcluir != null) {
			this.fornecedor.getEnderecos().remove(this.enderecoAExcluir);

			this.fornecedorAlteradoEvent.fire(new FornecedorAlteradoEvent(fornecedor));

			FacesUtil.addInfoMessage("Endereço do fornecedor "
					+ enderecoAExcluir.getLogradouro()
					+ " excluído com sucesso.");

			this.enderecoAExcluir = new Endereco();
		}
	}

	public void alterar() {
		if (enderecoAEditar != null) {
			this.fornecedorAlteradoEvent.fire(new FornecedorAlteradoEvent(fornecedor));

			FacesUtil.addInfoMessage("Endereço do fornecedor "
					+ enderecoAEditar.getLogradouro()
					+ " alterado com sucesso.");

			this.enderecoAEditar = new Endereco();
		}
	}

	
	public void pesquisarCep() {
		this.cepServiceBean = new CepServiceBean();
		endereco = cepServiceBean.encontraCep(endereco.getCep());
	}
	
	public boolean isEnderecoEmpty(Endereco endereco) {
		boolean b = false;
		
		if( endereco.getLogradouro().equals(""))
		{
			FacesUtil.addErrorMessage("Campo logradouro não preenchido");
			b = true;
		}
			
		if( endereco.getNumero().equals(""))
		{
			FacesUtil.addErrorMessage("Campo número não preenchido");
			b = true;
		}
		
		if( endereco.getBairro().equals(""))
		{
			FacesUtil.addErrorMessage("Campo bairro não preenchido");
			b = true;
		}
			
		if(endereco.getCidade().equals(""))
		{
			FacesUtil.addErrorMessage("Campo cidade não preenchido");
			b = true;
		}
		
		if(endereco.getUf().equals(""))
		{
			FacesUtil.addErrorMessage("Campo Uf não preenchido");
			b = true;
		}
		
		if(b == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getEnderecosIncluidos() {
		return this.fornecedor.getEnderecos();
	}

	public Endereco getEnderecoAEditar() {
		return enderecoAEditar;
	}

	public void setEnderecoAEditar(Endereco enderecoAEditar) {
		this.enderecoAEditar = enderecoAEditar;
	}

	public Endereco getEnderecoAExcluir() {
		return enderecoAExcluir;
	}

	public void setEnderecoAExcluir(Endereco enderecoAExcluir) {
		this.enderecoAExcluir = enderecoAExcluir;
	}


	public CepServiceBean getCepServiceBean() {
		return cepServiceBean;
	}


	public void setCepServiceBean(CepServiceBean cepServiceBean) {
		this.cepServiceBean = cepServiceBean;
	}
	
	
}