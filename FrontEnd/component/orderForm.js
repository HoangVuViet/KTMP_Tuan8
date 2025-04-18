import React, { useState } from "react";
import { createOrder } from "../api/orderApi";
import "./orderForm.css"; // Import CSS for styling

const OrderForm = ({ cartItems, onClearCart }) => {
  const [orderDetails, setOrderDetails] = useState({ name: "", address: "" });

  const handleChange = (e) => {
    setOrderDetails({
      ...orderDetails,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (orderDetails.name && orderDetails.address && cartItems.length > 0) {
      const orderData = {
        ...orderDetails,
        items: cartItems,
      };
      try {
        await createOrder(orderData);
        alert("Đơn hàng đã được tạo!");
        onClearCart();
      } catch (error) {
        alert("Lỗi khi tạo đơn hàng!");
      }
    } else {
      alert("Vui lòng nhập đầy đủ thông tin.");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Tạo đơn hàng</h2>
      <input
        type="text"
        name="name"
        placeholder="Tên khách hàng"
        value={orderDetails.name}
        onChange={handleChange}
      />
      <input
        type="text"
        name="address"
        placeholder="Địa chỉ giao hàng"
        value={orderDetails.address}
        onChange={handleChange}
      />
      <button type="submit">Tạo đơn hàng</button>
    </form>
  );
};

export default OrderForm;
