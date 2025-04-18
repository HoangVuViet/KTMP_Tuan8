import React, { useEffect, useState } from "react";
import { fetchProducts } from "../api/productApi";
import "./productList.css"; // Import CSS for styling


const ProductList = ({ onAddToCart }) => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProductData = async () => {
      try {
        const productData = await fetchProducts();
        setProducts(productData);
      } catch (error) {
        console.error("Error fetching products", error);
      }
    };
    fetchProductData();
  }, []);

  return (
    <div>
      <h2>Danh sách sản phẩm</h2>
      <div className="product-list">
        {products.map((product) => (
          <div key={product.id} className="product-item">
            <img src={product.image} alt={product.name} />
            <h3>{product.name}</h3>
            <p>{product.description}</p>
            <p>{product.price} VND</p>
            <button onClick={() => onAddToCart(product)}>Thêm vào giỏ</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
