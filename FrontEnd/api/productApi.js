import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

export const fetchProducts = async () => {
    try {
        const response = await axios.get(`${API_URL}/products`);
        return response.data;
    } catch (error) {
        console.error("Error fetching products", error);
        throw error;
    }
};

export const updateProductStock = async (productId, quantity) => {
    try {
        const response = await axios.patch(`${API_URL}/products/${productId}/updateStock`, {
            quantity
        });
        return response.data;
    } catch (error) {
        console.error("Error updating product stock", error);
        throw error;
    }
};
