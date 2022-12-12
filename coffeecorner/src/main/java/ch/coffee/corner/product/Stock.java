package ch.coffee.corner.product;

import java.util.HashMap;
import java.util.Map;

import ch.coffee.corner.extra.Extra;

public class Stock {
	
	private static Map<Integer,Product> productsMap = new HashMap<>();
	private static Map<Integer,Extra> extrasMap = new HashMap<>();

	static {
		productsMap.put(1,new Product("small coffee", 2.50, ProductType.BEVERAGE));
		productsMap.put(2,new Product("medium coffee", 3.00, ProductType.BEVERAGE));
		productsMap.put(3,new Product("large coffee", 3.50, ProductType.BEVERAGE));
		productsMap.put(4,new Product("orange juice", 3.95, ProductType.BEVERAGE));
		productsMap.put(5,new Product("bacon roll", 4.50, ProductType.SNACK));
	
		extrasMap.put(1,new Extra("extra milk", 0.30));
		extrasMap.put(2,new Extra("foamed milk", 0.50));
		extrasMap.put(3,new Extra("special roast coffee", 0.90));
	   }

	public static Map<Integer, Product> getProductsMap() {
		return productsMap;
	}

	public static Map<Integer, Extra> getExtrasMap() {
		return extrasMap;
	}

}
