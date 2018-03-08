package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.event.DragDropEvent;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.model.Categoria;
import br.com.maddytec.pedidovenda.model.Produto;
import br.com.maddytec.pedidovenda.model.TipoProduto;
import br.com.maddytec.pedidovenda.repository.Atributos;
import br.com.maddytec.pedidovenda.repository.Categorias;
import br.com.maddytec.pedidovenda.service.CadastroProdutoService;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
    
	@Inject
	private Atributos atributos;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	
	
	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	private Atributo atributo;
	private Atributo atributoSelecionado;
	private List<Atributo> listAtributos;
	private List<Atributo> listAtributosSelecionado;
	
	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {

		if (this.produto == null) {
			limpar();
		}

		categoriasRaizes = categorias.raizes();

		if (this.categoriaPai != null) {
			carregarSubcategorias();
		}
		
		this.listAtributos = atributos.lista();
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();

		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public void onAtributoRemove(DragDropEvent removeAtributoEvent) {
        Atributo atributo = ((Atributo) removeAtributoEvent.getData());
  
        listAtributosSelecionado.add(atributo);
        listAtributos.remove(atributo);
    }
	
	
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public TipoProduto[] getTipoProduto() {
		return TipoProduto.values();
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	public CadastroProdutoService getCadastroProdutoService() {
		return cadastroProdutoService;
	}

	public void setCadastroProdutoService(CadastroProdutoService cadastroProdutoService) {
		this.cadastroProdutoService = cadastroProdutoService;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public List<Atributo> getListAtributos() {
		return listAtributos;
	}

	public void setListAtributos(List<Atributo> listAtributos) {
		this.listAtributos = listAtributos;
	}

	public void setCategoriasRaizes(List<Categoria> categoriasRaizes) {
		this.categoriasRaizes = categoriasRaizes;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Atributo getAtributoSelecionado() {
		return atributoSelecionado;
	}

	public void setAtributoSelecionado(Atributo atributoSelecionado) {
		this.atributoSelecionado = atributoSelecionado;
	}

	
	public List<Atributo> getListAtributosSelecionado() {
		return listAtributosSelecionado;
	}

	public void setListAtributosSelecionado(List<Atributo> listAtributosSelecionado) {
		this.listAtributosSelecionado = listAtributosSelecionado;
	}

	
}