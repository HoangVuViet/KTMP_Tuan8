import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

export const createOrder = async (orderData) => {
  try {
    const response = await axios.post(`${API_URL}/orders/create`, orderData);
    return response.data;
  } catch (error) {
    console.error("Error creating order", error);
    throw error;
  }
};

export const fetchOrders = async () => {
  try {
    const response = await axios.get(`${API_URL}/orders`);
    return response.data;
  } catch (error) {
    console.error("Error fetching orders", error);
    throw error;
  }
};
