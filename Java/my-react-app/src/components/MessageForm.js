import React, { useState } from "react";
import { saveMessage } from "../services/apiService"; // Import the saveMessage function

const MessageForm = () => {
    const [content, setContent] = useState("");  // State to handle the input
    const [status, setStatus] = useState("");    // State to show the status message after saving

    const handleSaveMessage = async () => {
        if (!content.trim()) {
            setStatus("Message cannot be empty!");
            return;
        }

        try {
            // Save the message to the backend
            await saveMessage(content);
            
            // Show alert popup with a message
            alert("Message saved!"); // The popup you mentioned
            
            setContent("");  // Clear the input field after saving
        } catch (error) {
            setStatus("Error saving message.");  // Show error message if saving fails
        }
    };

    return (
        <div>
            <textarea
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder="Enter your message"
            ></textarea>
            <button onClick={handleSaveMessage}>Save Message</button>

            {status && <p>{status}</p>} {/* Display the status message */}
        </div>
    );
};

export default MessageForm;

