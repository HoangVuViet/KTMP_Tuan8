import React, { useEffect, useState } from "react";
import { fetchOrders } from "../api/orderApi";
import "./orderHistory.css"; 

const OrderHistory = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrderData = async () => {
      try {
        const orderData = await fetchOrders();
        setOrders(orderData);
      } catch (error) {
        console.error("Error fetching orders", error);
      }
    };
    fetchOrderData();
  }, []);

  return (
    <div>
      <h2>Lịch sử đơn hàng</h2>
      <div className="order-history">
        {orders.map((order) => (
          <div key={order.id} className="order-item">
            <h3>Đơn hàng #{order.id}</h3>
            <p>Khách hàng: {order.name}</p>
            <p>Địa chỉ: {order.address}</p>
            <ul>
              {order.items.map((item, index) => (
                <li key={index}>
                  {item.productName} - {item.quantity} sản phẩm
                </li>
              ))}
            </ul>
            <p>Tổng tiền: {order.totalPrice} VND</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default OrderHistory;
