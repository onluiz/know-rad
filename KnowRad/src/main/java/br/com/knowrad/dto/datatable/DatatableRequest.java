package br.com.knowrad.dto.datatable;

import java.io.Serializable;

public class DatatableRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer sEcho;
	
	private Integer iDisplayLength;
	
	private Integer iDisplayStart;
	
	private Integer sColumns;
	
	private Integer iColumns;
	
	private String sSearch; //valor que estah sendo pesquisado
	
	private Boolean bRegex;
	
	private Integer iSortingCols;
	
	private Integer iSortCol_0; //Numero da coluna que esta sendo ordenado
	
	private String sSortDir_0; //Direcao da ordenacao
	
	public DatatableRequest() {
		
	}
	
	public DatatableRequest(Integer sEcho, Integer iDisplayLength, Integer iDisplayStart,
			Integer sColumns, Integer iColumns, String sSearch, Boolean bRegex,
			Integer iSortingCols, Integer iSortCol_0, String sSortDir_0) {
		super();
		this.sEcho = sEcho;
		this.iDisplayLength = iDisplayLength;
		this.iDisplayStart = iDisplayStart;
		this.sColumns = sColumns;
		this.iColumns = iColumns;
		this.sSearch = sSearch;
		this.bRegex = bRegex;
		this.iSortingCols = iSortingCols;
		this.iSortCol_0 = iSortCol_0;
		this.sSortDir_0 = sSortDir_0;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getsColumns() {
		return sColumns;
	}

	public void setsColumns(int sColumns) {
		this.sColumns = sColumns;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public boolean isbRegex() {
		return bRegex;
	}

	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}
	
	
}
