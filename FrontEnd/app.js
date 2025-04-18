import React, { useState } from "react";
import ProductList from "./components/ProductList";
import OrderForm from "./components/OrderForm";
import OrderHistory from "./components/OrderHistory";
import "./app.css";

const App = () => {
  const [cart, setCart] = useState([]);

  const handleAddToCart = (product) => {
    setCart((prevCart) => [...prevCart, product]);
  };

  const handleClearCart = () => {
    setCart([]);
  };

  return (
    <div className="app">
      <h1>Hệ thống quản lý bán hàng</h1>
      <ProductList onAddToCart={handleAddToCart} />
      <OrderForm cartItems={cart} onClearCart={handleClearCart} />
      <OrderHistory />
    </div>
  );
};

export default App;
