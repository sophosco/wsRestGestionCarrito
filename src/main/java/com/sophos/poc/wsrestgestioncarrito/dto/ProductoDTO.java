package com.sophos.poc.wsrestgestioncarrito.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class ProductoDTO {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("images")
	private List<GaleriaProductoDTO> images;
	
	@JsonProperty("oldPrice")
	private Long oldPrice = null;
	
	@JsonProperty("newPrice")
	private Long newPrice = null;
	
	@JsonProperty("discount")
	private Long discount = null;	
	
	@JsonProperty("ratingsCount")
	private Long ratingsCount = null;	
	
	@JsonProperty("ratingsValue")
	private Long ratingsValue = null;	
	
	@JsonProperty("description")
	private String description = null;
	
	@JsonProperty("detailDescription")
	private String detailDescription = null;
	
	@JsonProperty("additionalInformation")
	private String additionalInformation = null;
	
	@JsonProperty("availibilityCount")
	private Long availibilityCount = null;
	
	@JsonProperty("cartCount")
	private Long cartCount = null;
	
	@JsonProperty("comments")
	private List<ComentarioProductoDTO> comments;

	@JsonProperty("color")
	private List<String> color = null;
	
	@JsonProperty("size")
	private List<String> size = null;
	
	@JsonProperty("weight")
	private Long weight = null;
	
	@JsonProperty("categoryId")
	private Long categoryId = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GaleriaProductoDTO> getImages() {
		return images;
	}

	public void setImages(List<GaleriaProductoDTO> images) {
		this.images = images;
	}

	public Long getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Long oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Long getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Long newPrice) {
		this.newPrice = newPrice;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Long getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Long ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Long getRatingsValue() {
		return ratingsValue;
	}

	public void setRatingsValue(Long ratingsValue) {
		this.ratingsValue = ratingsValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Long getAvailibilityCount() {
		return availibilityCount;
	}

	public void setAvailibilityCount(Long availibilityCount) {
		this.availibilityCount = availibilityCount;
	}

	public Long getCartCount() {
		return cartCount;
	}

	public void setCartCount(Long cartCount) {
		this.cartCount = cartCount;
	}

	public List<ComentarioProductoDTO> getComments() {
		return comments;
	}

	public void setComments(List<ComentarioProductoDTO> comments) {
		this.comments = comments;
	}

	public List<String> getColor() {
		return color;
	}

	public void setColor(List<String> color) {
		this.color = color;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
}
