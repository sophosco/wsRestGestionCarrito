package com.sophos.poc.wsrestgestioncarrito.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class CarritoDetalleDTO {

	@JsonProperty("totalPrice")
	private Long totalPrice = null;
	
	@JsonProperty("totalCartCount")
	private Long totalCartCount = null;
	
	@JsonProperty("compareList")
	private Long compareList = null;
	
	@JsonProperty("wishList")
	private Long wishList = null;
	
	@JsonProperty("products")
	private List<ProductoDTO> productoList = null;

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getTotalCartCount() {
		return totalCartCount;
	}

	public void setTotalCartCount(Long totalCartCount) {
		this.totalCartCount = totalCartCount;
	}

	public Long getCompareList() {
		return compareList;
	}

	public void setCompareList(Long compareList) {
		this.compareList = compareList;
	}

	public Long getWishList() {
		return wishList;
	}

	public void setWishList(Long wishList) {
		this.wishList = wishList;
	}

	public List<ProductoDTO> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<ProductoDTO> productoList) {
		this.productoList = productoList;
	}

	
}
