import axios from "axios";

const API_URL = "http://localhost:8080"; // Backend API URL

// Save message
export const saveMessage = async (content) => {
    try {
        const response = await axios.post(`${API_URL}/hello`, { content });
        return response.data; // Return the response data
    } catch (error) {
        console.error("Error saving message:", error);
        throw error; // Throw error if save fails
    }
};

